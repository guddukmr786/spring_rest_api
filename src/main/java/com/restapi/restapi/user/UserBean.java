package com.restapi.restapi.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class UserBean {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;

    public UserBean(Integer id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
}
