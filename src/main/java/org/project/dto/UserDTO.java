package org.project.dto;


/**

 UserDTO represents a user data transfer object.

 It contains information about a user such as their id, name, surname, email, role, password, and phone number.
 */
public class UserDTO {
    public int id;
    public String name;
    public String surname;
    public String email;
    public String role;
    public String phone_number;
    public String password;

/**

 Constructs a new UserDTO object with the given id, name, surname, email, role, password, and phone number.
 @param id The user's id.
 @param name The user's name.
 @param surname The user's surname.
 @param email The user's email.
 @param role The user's role.
 @param password The user's password.
 @param phone_number The user's phone number.
*/
    public UserDTO(int id, String name, String surname, String email, String role, String password, String phone_number) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.phone_number = phone_number;
    }
    /**

     Constructs a new empty UserDTO object.
     */
    public UserDTO() {

    }

    /**

     Gets the user's id.
     @return The user's id.
     */
    public int getId() {
        return id;
    }
    /**

     Gets the user's name.
     @return The user's name.
     */
    public String getName() {
        return name;
    }
    /**

     Gets the user's surname.
     @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }
    /**

     Gets the user's email.
     @return The user's email.
     */
    public String getEmail() {
        return email;
    }
    /**

     Gets the user's role.
     @return The user's role.
     */
    public String getRole() {
        return role;
    }
    /**

     Gets the user's password.
     @return The user's password.
     */
    public String getPassword() {
        return password;
    }
    /**

     Gets the user's phone number.
     @return The user's phone number.
     */
    public String getPhone_number() {
        return phone_number;
    }
    /**

     Sets the user's id to the given value.
     @param l The new id value.
     */
    public void setId(int l) {
        this.id = l;
    }
}
