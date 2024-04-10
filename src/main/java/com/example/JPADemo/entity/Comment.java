package com.example.JPADemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer UUID;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private Date createdDate;

    private Date updatedDate;

}
