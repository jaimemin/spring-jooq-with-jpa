package com.tistory.jaimemin.springjooqwithjpa.repository;

import com.tistory.jaimemin.springjooqwithjpa.entity.Member;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.tistory.jaimemin.jooq.code.jooq.tables.Member.MEMBER;

@Repository
public class MemberJooqRepository {

    private final DSLContext dslContext;

    public MemberJooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Member createMember(Member member) {
        dslContext
                .insertInto(MEMBER)
                .set(MEMBER.NAME, member.getName())
                .set(MEMBER.TEAM_ID, member.getTeam().getId()) // Assuming Team has an ID field
                .execute();
        return member;
    }

    public Member getMemberById(Long memberId) {
        return dslContext
                .selectFrom(MEMBER)
                .where(MEMBER.ID.eq(memberId))
                .fetchOneInto(Member.class);
    }

    public void updateMember(Member member) {
        dslContext
                .update(MEMBER)
                .set(MEMBER.NAME, member.getName())
                .set(MEMBER.TEAM_ID, member.getTeam().getId()) // Assuming Team has an ID field
                .where(MEMBER.ID.eq(member.getId()))
                .execute();
    }

    public void deleteMemberById(Long memberId) {
        dslContext
                .deleteFrom(MEMBER)
                .where(MEMBER.ID.eq(memberId))
                .execute();
    }
}
