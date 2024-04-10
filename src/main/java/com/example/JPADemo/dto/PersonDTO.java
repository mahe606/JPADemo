package com.example.JPADemo.dto;

import com.example.JPADemo.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String fullName;
    private String email;
    private Address address;
}
