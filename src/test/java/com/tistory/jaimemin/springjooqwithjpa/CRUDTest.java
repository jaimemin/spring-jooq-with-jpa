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

    @Autowired
    TeamJPARepository teamJPARepository;

    @Test
    void insertTeams() {
        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Team team = new Team("Team" + (i + 1));
            List<Member> members = new ArrayList<>();

            for (int j = 1; j <= 3; j++) {
                Member member = Member.builder()
                        .name("Member " + (i * 10 + j))
                        .build();
                member.setTeam(team);
                members.add(member);
            }

            team.setMembers(members);
            teams.add(team);
        }

        teamJPARepository.saveAll(teams);
    }
}
