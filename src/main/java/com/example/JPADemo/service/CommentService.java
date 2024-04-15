package com.example.JPADemo.service;


import com.example.JPADemo.dto.CommentDTO;
import com.example.JPADemo.dto.CommentsDTO;
import com.example.JPADemo.entity.Comment;
import com.example.JPADemo.repository.CommentRepository;
import com.example.JPADemo.specification.CommentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository repository;

    @Autowired
    CommentSpecification commentSpecification;

    ModelMapper mapper = new ModelMapper();

    public CommentDTO saveComment(CommentDTO dto) {
        Comment newComment = repository.save(mapper.map(dto, Comment.class));
        return mapper.map(newComment, CommentDTO.class);
    }


    public List<CommentDTO> getAllComments(Pageable pageable) {
        Iterable<Comment> comments = repository.findAll(pageable);
        List<CommentDTO> commentsDTO = new ArrayList<>();
        comments.forEach(comment -> commentsDTO.add(mapper.map(comment, CommentDTO.class)));
        return commentsDTO;
    }

    public List<CommentDTO> getCommentsByUserId(String userId, Pageable pageable) {
        Optional<List<Comment>> comments = repository.findAllByUserId(userId, pageable);
        if (comments.isPresent())
            return comments.get().stream().map(comment -> mapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
        return null;
    }


    public CommentsDTO searchUserCommentsByFilters(String userId,
                                                   String searchText,
                                                   String fromDate,
                                                   String toDate,
                                                   boolean checkIsLongTermUser,
                                                   Pageable page){
        Page<Comment> filteredComments =
                repository.findAll(
                        commentSpecification.conditionalSearchForUser(
                                searchText,
                                fromDate,
                                toDate,
                                userId,
                                checkIsLongTermUser),
                        page);
        long totalElements = filteredComments.getTotalElements();
        int totalPages = filteredComments.getTotalPages();

        return CommentsDTO.builder()
                .comments(filteredComments.get().map(comment -> mapper.map(comment, CommentDTO.class)).collect(Collectors.toList()))
                        .pages(totalPages).totalCount(totalElements).build();
    }
}
