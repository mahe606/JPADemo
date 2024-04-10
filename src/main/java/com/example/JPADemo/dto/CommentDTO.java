package com.example.JPADemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer UUID;

    private String userId;

    @NotNull(message = "The description field cannot be null")
    private String description;

    private String createdBy;

    private Date createdDate;

    private Date updatedDate;
}
