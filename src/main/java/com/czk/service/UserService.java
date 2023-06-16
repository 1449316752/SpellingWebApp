package com.czk.service;

import com.czk.domain.User;

public interface UserService {
    User selectUser(User user);

    User selectUserByEmail(String email);

    User selectUserByUserName(String username);

    boolean addUser(String username, String password, String email);

    boolean upDataUserHeadPath(User user);

    boolean changePassword(String username, String password);
}
