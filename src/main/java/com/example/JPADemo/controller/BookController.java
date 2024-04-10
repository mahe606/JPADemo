package com.example.JPADemo.controller;

import com.example.JPADemo.entity.Book;
import com.example.JPADemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = service.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable int id){
/*        Book book = service.getById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);*/
        final Optional<Book> foundBook = service.getById(id);
        return foundBook.map(book -> new ResponseEntity<Book>(book,HttpStatus.OK)).orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));

    }
    @PostMapping("")
    public ResponseEntity<Book> create(@RequestBody Book book){
        Book newBook = service.save(book);
        return new ResponseEntity<>(newBook,HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        boolean isDeleted = service.delete(id);
        if(isDeleted)
            return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Id not Present in the System", HttpStatus.NOT_FOUND);
    }

}
