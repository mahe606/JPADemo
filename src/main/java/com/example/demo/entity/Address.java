package com.example.demo.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

@Slf4j
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private long addressId;
    private String address1;
    private String address2;
    private String city;
    private String state;
}
