package com.social.media.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequestDTO {
    @NotBlank
    private String photoUrl;

    @NotBlank
    private String caption;
}
