package com.example.demo.queryMethods;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("api/contest")
public class ContestController {


    @Autowired
    ContestService service;

    @GetMapping("/getName/{contestId}")
    public ResponseEntity<String> getContestName(@PathVariable Integer contestId){
        String contestName = service.getContestName(contestId);
        return new ResponseEntity<>(contestName, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Contest>> getAll(){
        List<Contest>  contestList = service.getAll();
        return new ResponseEntity<>(contestList, HttpStatus.OK);
    }
}
