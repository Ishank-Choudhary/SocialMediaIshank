package com.social.media.service;


import com.social.media.models.UserGroup;
import com.social.media.payload.UserGroupResponseDTO;
import org.springframework.data.domain.Page;

public interface UserGroupService {

    UserGroup createUserGroup(UserGroup userGroup);

    UserGroupResponseDTO getUserGroupById(Long id);

    Page<UserGroupResponseDTO> getAllUserGroups(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserGroup updateUserGroup(Long id, UserGroup updatedUserGroup);

    void deleteUserGroup(Long id);
}
