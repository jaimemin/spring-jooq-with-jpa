package com.tistory.jaimemin.springjooqwithjpa.repository;

import com.tistory.jaimemin.springjooqwithjpa.entity.Team;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.tistory.jaimemin.jooq.code.jooq.tables.Team.TEAM;

@Repository
public class TeamJooqRepository {

    private final DSLContext dslContext;

    public TeamJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Team createTeam(Team team) {
        dslContext
                .insertInto(TEAM)
                .set(TEAM.NAME, team.getName())
                .execute();
        return team;
    }

    public Team getTeamById(Long teamId) {
        return dslContext
                .selectFrom(TEAM)
                .where(TEAM.ID.eq(teamId))
                .fetchOneInto(Team.class);
    }

    public void updateTeam(Team team) {
        dslContext
                .update(TEAM)
                .set(TEAM.NAME, team.getName())
                .where(TEAM.ID.eq(team.getId()))
                .execute();
    }

    public void deleteTeamById(Long teamId) {
        dslContext
                .deleteFrom(TEAM)
                .where(TEAM.ID.eq(teamId))
                .execute();
    }
}
