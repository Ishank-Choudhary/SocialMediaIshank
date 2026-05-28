package com.social.media.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String bio;
    private String profilePhotoUrl;

    @OneToOne(mappedBy = "profile") // mapped by used at the side which is controlled by some other class
    private User user;
}
