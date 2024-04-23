package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAll(){
        return repository.findAll();
    }

    public Optional<Book> getById(int id){
        final Optional<Book> foundBook = repository.findById(id);
        return foundBook;
    }

    public Book save(Book book){
        return repository.save(book);
    }

    public boolean delete(int id){
        if(repository.findById(id).isEmpty())
            return false;
        else {
            repository.deleteById(id);
            return true;
        }
    }

}
