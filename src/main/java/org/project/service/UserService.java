package org.project.service;

import org.project.db.dao.UserDao;
import org.project.dto.UserDTO;
import org.project.db.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public UserDTO getUserByEmail(String email){
        User user = UserDao.getUserByEmail(email);
        List<User> users = new ArrayList<>();
        users.add(user);
        return parsingUserInUserDTO(users).get(0);
    }
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

    public UserDTO createUser(UserDTO userDTO) throws Exception {
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
