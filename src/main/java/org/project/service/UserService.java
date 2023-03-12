package org.project.service;

import org.project.db.dao.UserDao;
import org.project.dto.UserDTO;
import org.project.db.entity.User;

import java.util.ArrayList;
import java.util.List;
/**

 This class provides functionality related to the user management system.
 */
public class UserService {
    /**

     Returns a UserDTO object by its email.
     @param email the email of the user to be retrieved
     @return a UserDTO object representing the user with the given email
     */
    public UserDTO getUserByEmail(String email){
        User user = UserDao.getUserByEmail(email);
        List<User> users = new ArrayList<>();
        users.add(user);
        return parsingUserInUserDTO(users).get(0);
    }
    /**

     Converts a list of User objects to a list of UserDTO objects.
     @param list a list of User objects to be converted
     @return a list of UserDTO objects representing the input list of User objects
     */
    private List<UserDTO> parsingUserInUserDTO(List<User> list) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : list) {
            if (user!=null) {
                userDTOs.add(new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getRole(), user.getPassword(), user.getPhone_number()));
            }else {
                userDTOs.add(null);
            }
        }
        return userDTOs;
    }
    /**

     Creates a new user with the provided information.
     @param userDTO a UserDTO object containing the information of the user to be created
     @return a UserDTO object representing the created user
     @throws Exception if the provided user data is invalid or the user with the given email already exists
     */
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        List<User> usersList = UserDao.getAllUsers();
        System.out.println(usersList);
        if ( userDTO.password == null || userDTO.email == null  || userDTO.password.isEmpty() || userDTO.email.isEmpty()) {
            throw new Exception("emptyData");
        }
        if (!userDTO.phone_number.matches("^\\+380\\d{3}\\d{2}\\d{2}\\d{2}$")){
            throw new Exception("errorPhoneNumber");
//            errorMessage="Phone number wrong";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
        }
        if (!userDTO.email.matches("^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            throw new Exception("errorEmail");
//            errorMessage="Email wrong";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
        }
        assert usersList != null;
        for (User user:usersList) {
            if (user.getEmail().equals(userDTO.getEmail())){
                System.out.println(user.getEmail());
                System.out.println(userDTO.getEmail());
                throw new Exception("errorEmailUser");
            }
        }
        User user = new User();
        user.setId(userDTO.id);
        user.setName(userDTO.name);
        user.setSurname(userDTO.surname);
        user.setEmail(userDTO.email);
        user.setPhone_number(userDTO.phone_number);
        user.setPassword(userDTO.password);
        user.setRole("user");
        User user1 = UserDao.insertUser(user);
        List<User> users = new ArrayList<>();
        users.add(user1);
        return parsingUserInUserDTO(users).get(0);

    }





}
