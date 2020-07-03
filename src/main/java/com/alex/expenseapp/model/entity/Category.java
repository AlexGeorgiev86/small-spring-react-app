package com.alex.expenseapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {

    private Long id;
    private String name;
    private User user;

    public Category() {
    }
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne(cascade = CascadeType.PERSIST)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
