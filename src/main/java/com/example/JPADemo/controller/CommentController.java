package com.example.JPADemo.controller;


import com.example.JPADemo.dto.CommentDTO;
import com.example.JPADemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService service;


    @PostMapping("")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO dto) {
        dto.setCreatedDate(new Date());
        CommentDTO newDTO = service.saveComment(dto);
        return new ResponseEntity<CommentDTO>(newDTO, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<CommentDTO>> getAllComments(@RequestParam(defaultValue = "5", required = false) Integer pageSize
            , @RequestParam(defaultValue = "0", required = false) Integer pageNumber) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<CommentDTO> commentDTOS = service.getAllComments(page);
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsByUserId(@PathVariable String userId, @RequestParam(defaultValue = "5", required = false) Integer pageSize
            , @RequestParam(defaultValue = "0", required = false) Integer pageNumber) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        List<CommentDTO> commentsDTOs = service.getCommentsByUserId(userId, paging);
        return new ResponseEntity<List<CommentDTO>>(commentsDTOs, HttpStatus.ACCEPTED);
    }
}
