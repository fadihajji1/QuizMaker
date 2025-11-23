package com.example.quizplatformf.entity;

import com.example.quizplatformf.entity.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Date createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Role role ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getUser_id() {
        return userId;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName() {return this.firstName;}

    public String getLastName() {return this.lastName;}

    public Role getRole() {return this.role;}

    public Date getCreatedAt() {return this.createdAt;}

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setRole(Role role) {this.role = role;}
    public void setEmail(String email) {this.email = email;}
}
