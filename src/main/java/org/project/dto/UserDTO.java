package org.project.dto;



public class UserDTO {
    public int id;
    public String name;
    public String surname;
    public String email;
    public String role;
    public String phone_number;
    public String password;


    public UserDTO(int id, String name, String surname, String email, String role, String password, String phone_number) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.phone_number = phone_number;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
