package com.example.JPADemo.repository;

import com.example.JPADemo.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface   CommentRepository extends JpaRepository<Comment, Integer> {
    //method to find by ID
    Optional<List<Comment>> findAllByUserId(String userId, Pageable page);
}
