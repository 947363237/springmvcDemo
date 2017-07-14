package com.lis.model;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class User {
    
    private int id;
    @NotEmpty
    private String name;
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
    private String gender;
    private int age;
    
    public User(){}
    public User(String name, Date birth, String gender, int age) {
        super();
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birth=" + birth + ", gender=" + gender + ", age=" + age + "]";
    }
    
    
}
