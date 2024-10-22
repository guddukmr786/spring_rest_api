package com.restapi.restapi.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.restapi.restapi.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "user_details")
public class User {
    protected User(){}
    @Id
    @GeneratedValue
    //@JsonIgnore //Id will not return to the response
    private Integer id;
    @Size(min = 2, message = "Name should be at least 2 chars") //validation
    //@JsonProperty("user_name") //customize json property field name
    private String name;
    @Past(message = "Date of birth should be in past")
    //@JsonProperty("data_of_birth")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
    public User(Integer id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

}
