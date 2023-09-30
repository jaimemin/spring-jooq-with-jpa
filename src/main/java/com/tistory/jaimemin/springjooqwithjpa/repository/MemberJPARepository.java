package com.tistory.jaimemin.springjooqwithjpa.repository;

import com.tistory.jaimemin.springjooqwithjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface MemberJPARepository extends JpaRepository<Member, Long> {
}
