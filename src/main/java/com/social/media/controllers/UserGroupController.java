package com.social.media.controllers;

import com.social.media.payload.UserGroupResponseDTO;
import com.social.media.models.UserGroup;
import com.social.media.service.UserGroupService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-groups")
public class UserGroupController {

    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping
    public ResponseEntity<UserGroup> createUserGroup(@RequestBody UserGroup userGroup) {
        return ResponseEntity.ok(userGroupService.createUserGroup(userGroup));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGroupResponseDTO> getUserGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(userGroupService.getUserGroupById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserGroupResponseDTO>> getAllUserGroups(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        return ResponseEntity.ok(userGroupService.getAllUserGroups(pageNumber, pageSize, sortBy, sortOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGroup> updateUserGroup(
            @PathVariable Long id,
            @RequestBody UserGroup updatedUserGroup) {

        return ResponseEntity.ok(userGroupService.updateUserGroup(id, updatedUserGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserGroup(@PathVariable Long id) {
        userGroupService.deleteUserGroup(id);
        return ResponseEntity.ok("UserGroup deleted successfully");
    }
}
