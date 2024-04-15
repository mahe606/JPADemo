package com.example.JPADemo.specification;

import com.example.JPADemo.entity.Comment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CommentSpecification {
    public Specification<Comment> isLongTermUser() {
        return (root, query, cb) ->
                cb.lessThan(
                        root.get("createdDate"),
                        LocalDate.of(2020, 01, 01)
                                .atStartOfDay(
                                        ZoneId.of("Australia/Sydney")
                                )
                                .toInstant()
                );
    }

    public Specification<Comment> createdBetween(
            Instant fromDate,
            Instant toDate) {
        return (root, query, cb) ->
                cb.between(
                        root.get("createdDate"),
                        fromDate, toDate
                );
    }

    public Specification<Comment> commentLike(String searchText) {
        return (root, query, cb) ->
                cb.like(
                        root.get("description"),
                        "%" + searchText + "%"
                );
    }

    public  Specification<Comment> byUser ( String userId) {
        return (root, query, cb) ->
                cb.equal(
                        root.get("userId"),
                        userId
                );
    }
    public Specification<Comment> conditionalSearchForUser(
            String searchText,
            String fromDate,
            String toDate,
            String userId,
            boolean checkIsLongTermUser
    ) {
        Specification spec = null;
        spec = where(byUser(userId));
        if (checkIsLongTermUser == true)
            spec = spec.and(isLongTermUser());

        if (searchText != null && !searchText.isBlank())
            spec = spec.and(commentLike(searchText));
        if (fromDate != null && !fromDate.isBlank()
                && toDate != null && !toDate.isBlank()) {
            spec = spec.and(
                    createdBetween(
                            LocalDate.parse(fromDate.toString()).atStartOfDay(
                                            ZoneId.of("Australia/Sydney")
                                    )
                                    .toInstant(),
                            LocalDate.parse(toDate.toString()).atStartOfDay(
                                            ZoneId.of("Australia/Sydney")
                                    )
                                    .toInstant()
                    )
            );
        }
        return spec;
    }
}
