package com.social.media.payload;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String email;
    private String bio;
    private String profilePhotoUrl;
}