package com.social.media.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDTO {

    @NotBlank
    private String caption;
    @NotBlank
    private String photoUrl;
}
