package com.example.demo.controller;


import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentsDTO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("UUID").descending());
        List<CommentDTO> commentDTOS = service.getAllComments(page);
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsByUserId(@PathVariable String userId, @RequestParam(defaultValue = "5", required = false) Integer pageSize
            , @RequestParam(defaultValue = "0", required = false) Integer pageNumber) {

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("UUID").descending());
        List<CommentDTO> commentsDTOs = service.getCommentsByUserId(userId, page);
        return new ResponseEntity<List<CommentDTO>>(commentsDTOs, HttpStatus.ACCEPTED);
    }


    @GetMapping("/search")
    public ResponseEntity<CommentsDTO> searchUserCommentsByFilters(
            @RequestParam(defaultValue= "0", required = false)
               Integer page ,
           @RequestParam(defaultValue= "5", required = false)
               Integer pageSize,
           @RequestParam(  required = true)
               String userId,
           @RequestParam( defaultValue= "", required = false)
               String searchText,
           @RequestParam( defaultValue= "", required = false)
               String fromDate,
           @RequestParam( defaultValue= "", required = false)
               String toDate,
           @RequestParam( defaultValue= "false", required = false)
               String checkIsLongTermUser
    ){

        Pageable paging = PageRequest.of(page, pageSize);

        CommentsDTO commentsDTOs =
                service.searchUserCommentsByFilters(
                        userId,
                        searchText,
                        fromDate,
                        toDate,
                        Boolean.valueOf(checkIsLongTermUser),
                        paging);    return new ResponseEntity<>( commentsDTOs, HttpStatus.ACCEPTED);
    }
}
