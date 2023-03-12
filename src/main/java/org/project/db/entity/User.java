package org.project.db.entity;

import java.util.Date;
/**

 User class represents a user entity in the system.

 It contains user's personal information such as name, surname, date of birth, phone number, email, password and role.
 Extends the {@link Entity} class.
 */
public class User extends Entity{
    /**

     The name of the user.
     */
    private String name;
    /**

     The surname of the user.
     */
    private String surname;
    /**

     The date of birth of the user.
     */
    private Date date_birth=null;
    /**

     The phone number of the user.
     */
    private String phone_number="";
    /**

     The email of the user.
     */
    private String email;
    /**

     The password of the user.
     */
    private String password;
    /**

     The role of the user in the system.
     */
    private String role;
    /**

     Gets the name of the user.
     @return the name of the user
     */
    public String getName() {
        return name;
    }
    /**

     Gets the surname of the user.
     @return the surname of the user
     */
    public String getSurname() {
        return surname;
    }
    /**

     Gets the date of birth of the user.
     @return the date of birth of the user
     */
    public Date getDate_birth() {
        return date_birth;
    }
    /**

     Gets the phone number of the user.
     @return the phone number of the user
     */
    public String getPhone_number() {
        return phone_number;
    }
    /**

     Gets the email of the user.
     @return the email of the user
     */
    public String getEmail() {
        return email;
    }
    /**

     Gets the password of the user.
     @return the password of the user
     */
    public String getPassword() {
        return password;
    }
    /**

     Sets the name of the user.
     @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
    /**

     Sets the surname of the user.
     @param surname the surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**

        Sets the date of birth of the user.
        @param date_birth the date of birth of the user
     */

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }
    /**

     Sets the phone number of the user.
     @param phone_number the phone number of the user
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    /**

     Sets the email of the user.
     @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**

     Sets the password of the user.
     @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**

     Sets the role of the user.
     @param role the role of the user
     */
    public void setRole(String role) { this.role = role;}
    /**

     Gets the role of the user.
     @return the role of the user
     */
    public String getRole() { return role; }



    /**

     Returns a string representation of the user.
     @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_birth=" + date_birth +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
