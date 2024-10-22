package com.restapi.restapi.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.restapi.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Post {
    protected Post(){};
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    public Post(String description, Integer id) {
        this.description = description;
        this.id = id;
    }
}
