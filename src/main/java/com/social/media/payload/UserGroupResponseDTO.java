package com.social.media.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupResponseDTO {

    private Long id;
    private String groupName;
    private String description;
}
