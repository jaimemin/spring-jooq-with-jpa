package com.tistory.jaimemin.springjooqwithjpa;

import com.tistory.jaimemin.jooq.code.jooq.tables.records.MemberRecord;
import com.tistory.jaimemin.jooq.code.jooq.tables.records.TeamRecord;
import com.tistory.jaimemin.springjooqwithjpa.entity.Member;
import com.tistory.jaimemin.springjooqwithjpa.entity.Team;
import com.tistory.jaimemin.springjooqwithjpa.repository.TeamJPARepository;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.tistory.jaimemin.jooq.code.jooq.tables.Member.MEMBER;
import static com.tistory.jaimemin.jooq.code.jooq.tables.Team.TEAM;

//@Transactional
@SpringBootTest
public class CRUDTest {

    private static int TEAM_CNT = 1000;

    private static int PAGE_SIZE = 1000;

    private static int MEMBER_CNT_PER_TEAM = 3;

    @Autowired
    DSLContext dslContext;

    @Autowired
    TeamJPARepository teamJPARepository;

    @Test
    void insertTeams_with_jpa() {
        List<Team> teams = new ArrayList<>();

        for (int page = 0; page < (int) Math.ceil((double) TEAM_CNT / PAGE_SIZE); page++) {
            for (int i = 0; i < PAGE_SIZE; i++) {
                Team team = new Team("Team" + (i * (page + 1) + 1));

                for (int j = 0; j < MEMBER_CNT_PER_TEAM; j++) {
                    Member member = Member.builder()
                            .name("Member " + (i * (page + 1) * 10 + (j + 1)))
                            .build();
                    member.setTeam(team);
                }

                teams.add(team);
            }

            teamJPARepository.saveAll(teams);
        }
    }

    @Test
    void insertTeams_with_jooq() {
        for (int page = 0; page < (int) Math.ceil((double) TEAM_CNT / PAGE_SIZE); page++) {
            List<InsertSetMoreStep<TeamRecord>> teamInserts = new ArrayList<>();
            List<InsertSetMoreStep<MemberRecord>> memberInserts = new ArrayList<>();

            for (int i = 0; i < PAGE_SIZE; i++) {
                TeamRecord teamRecord = dslContext.newRecord(TEAM);
                teamRecord.setName("Team" + (i * (page + 1) + 1));
                teamInserts.add(dslContext.insertInto(TEAM).set(teamRecord));

                for (int j = 0; j < MEMBER_CNT_PER_TEAM; j++) {
                    MemberRecord memberRecord = dslContext.newRecord(MEMBER);
                    memberRecord.setName("Member " + (i * (page + 1) * 10 + (j + 1)));
                    memberRecord.setTeamId(teamRecord.getId()); // Assuming Team has an ID field
                    memberInserts.add(dslContext.insertInto(MEMBER).set(memberRecord));
                }
            }

            dslContext.batch(teamInserts).execute();
            dslContext.batch(memberInserts).execute();
        }
    }
}
