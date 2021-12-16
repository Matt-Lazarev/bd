package com.lazarev.bd.service;


import com.lazarev.bd.security.ApplicationUser;
import com.lazarev.bd.security.UserRole;

import java.util.List;

public interface UserService {
    ApplicationUser getUserByLogin(String login);
    List<ApplicationUser> getAllUsers();
    void signUpUser(ApplicationUser applicationUser);
    ApplicationUser getUserById(Long id);
    void updateUser(Long id, ApplicationUser user);
    void deleteUser(Long id);
    void blockUser(Long id);
    void unblockUser(Long id);
    List<ApplicationUser> getUsersByRole(UserRole role);
    void enableAppUser(String login);
    boolean checkIsUniqueLogin(String login);
}
