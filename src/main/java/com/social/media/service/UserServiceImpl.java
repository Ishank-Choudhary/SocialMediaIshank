package com.social.media.service;

import com.social.media.models.Profile;
import com.social.media.models.User;
import com.social.media.payload.UserRequestDTO;
import com.social.media.payload.UserUpdateDTO;
import com.social.media.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements SocialService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserRequestDTO dto) throws Exception {
        Optional<User> existing = userRepository.findByName(dto.getName());
        if (existing.isPresent()) {
            throw new Exception(
                    "User is already present"
            );
        }
        if(dto.getBio()==null || dto.getProfilePhotoUrl() == null){
            throw new Exception("Profile details are mandatory");
        }
        Profile profile = new Profile();
        profile.setBio(dto.getBio());
        profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setProfile(profile);
        return userRepository.save(user);
    }

    @Override
    public User updateUserDetail(Long id, UserUpdateDTO dto) {
        //find the user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

        //updating email
        if(dto.getEmail()!=null) {
            existingUser.setEmail(dto.getEmail());
        }
        if(dto.getBio()!=null){
            existingUser.getProfile().setBio(dto.getBio());
        }
        if (dto.getProfilePhotoUrl() != null) {
            existingUser.getProfile().setProfilePhotoUrl(dto.getProfilePhotoUrl());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public User deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
        return existingUser;
    }
}
