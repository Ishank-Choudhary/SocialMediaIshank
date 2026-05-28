package com.social.media.service;

import com.social.media.models.User;
import com.social.media.payload.UserRequestDTO;
import com.social.media.payload.UserUpdateDTO;

import java.util.List;

public interface SocialService {

    List<User> getAllUser();
    User createUser(UserRequestDTO dto) throws Exception;
    User updateUserDetail(Long id, UserUpdateDTO dto);
    User deleteUser(Long id);

}
