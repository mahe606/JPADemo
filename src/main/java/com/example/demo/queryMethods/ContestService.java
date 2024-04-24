package com.example.demo.queryMethods;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestService {
    @Autowired
    ContestRepository repository;

    public String getContestName(Integer id){
        String contestName = repository.findContestNameById(id);
        return contestName;
    }

    public List<Contest> getAll() {
        List<Contest> contestList = repository.findAll();
        return contestList;
    }
}
