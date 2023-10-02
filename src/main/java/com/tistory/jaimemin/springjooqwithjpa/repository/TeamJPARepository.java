package com.tistory.jaimemin.springjooqwithjpa.repository;

import com.tistory.jaimemin.springjooqwithjpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface TeamJPARepository extends JpaRepository<Team, Long> {

    List<Team> findAllByNameIn(List<String> names);
}
