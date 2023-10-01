package com.tistory.jaimemin.springjooqwithjpa;

import com.tistory.jaimemin.springjooqwithjpa.entity.Member;
import com.tistory.jaimemin.springjooqwithjpa.entity.Team;
import com.tistory.jaimemin.springjooqwithjpa.repository.TeamJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CRUDTest {

    private static int TEAM_CNT = 1000;

    private static int PAGE_SIZE = 1000;

    private static int MEMBER_CNT_PER_TEAM = 3;

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
}
