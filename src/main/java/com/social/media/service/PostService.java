package com.social.media.service;

import com.social.media.models.Post;
import com.social.media.payload.PostRequestDTO;
import com.social.media.payload.PostUpdateDTO;

import java.util.List;

public interface PostService {

    Post createPost(Long userId, PostRequestDTO dto);
    List<Post> getAllPostsOfUser(Long userId);
    Post getPostOfUser(Long userId, Long postId);
    Post updateUserPost(Long userId, Long postId, PostUpdateDTO dto);
    Post deleteUserPost(Long userId, Long postId);

}
