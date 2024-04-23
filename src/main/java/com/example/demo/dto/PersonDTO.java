package com.example.demo.dto;

import com.example.demo.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String fullName;
    private String email;
    private Address address;
}
