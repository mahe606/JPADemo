package com.example.demo.queryMethods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ContestRepository extends JpaRepository<Contest,Integer> {

    @Query("select c.contestName from Contest c where c.id = :id")
    String findContestNameById(@Param("id") Integer id);
}
