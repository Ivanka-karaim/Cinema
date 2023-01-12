package org.project.db.entity;

import java.util.Date;

public class User extends Entity{
    private String name;
    private String surname;
    private Date date_birth=null;
    private String phone_number="";
    private String email;
    private String password;
    private String role;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) { this.role = role;}

    public String getRole() { return role; }




    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_birth=" + date_birth +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
