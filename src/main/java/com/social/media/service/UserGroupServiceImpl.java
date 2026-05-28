package com.social.media.service;

import com.social.media.models.UserGroup;
import com.social.media.payload.UserGroupResponseDTO;
import com.social.media.repositories.UserGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public UserGroup createUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroupResponseDTO getUserGroupById(Long id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserGroup not found with id: " + id));

        return new UserGroupResponseDTO(
                userGroup.getId(),
                userGroup.getGroupName(),
                userGroup.getDescription()
        );
    }

    @Override
    public Page<UserGroupResponseDTO> getAllUserGroups(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<UserGroup> userGroupPage = userGroupRepository.findAll(pageable);

        return userGroupPage.map(userGroup ->
                new UserGroupResponseDTO(
                        userGroup.getId(),
                        userGroup.getGroupName(),
                        userGroup.getDescription()
                )
        );
    }

    @Override
    public UserGroup updateUserGroup(Long id, UserGroup updatedUserGroup) {
        UserGroup existingUserGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserGroup not found with id: " + id));

        if (updatedUserGroup.getGroupName() != null) {
            existingUserGroup.setGroupName(updatedUserGroup.getGroupName());
        }

        if (updatedUserGroup.getDescription() != null) {
            existingUserGroup.setDescription(updatedUserGroup.getDescription());
        }

        return userGroupRepository.save(existingUserGroup);
    }

    @Override
    public void deleteUserGroup(Long id) {
        UserGroup userGroup = userGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserGroup not found with id: " + id));

        userGroupRepository.delete(userGroup);
    }
}
