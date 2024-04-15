package com.example.JPADemo.repository;

import com.example.JPADemo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface   CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
    //method to find by ID
    Optional<List<Comment>> findAllByUserId(String userId, Pageable page);

    @Override
    Page<Comment> findAll(Specification<Comment> spec, Pageable pageable);

    @Override
    Page<Comment> findAll(Pageable pageable);

    @Override
    List<Comment> findAll(Specification<Comment> spec);

    @Override
    List<Comment> findAll();
}
