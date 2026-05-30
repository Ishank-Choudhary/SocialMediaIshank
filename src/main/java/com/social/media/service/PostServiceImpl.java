package com.social.media.service;

import com.social.media.models.Post;
import com.social.media.models.User;
import com.social.media.payload.PostRequestDTO;
import com.social.media.payload.PostUpdateDTO;
import com.social.media.repositories.PostRepository;
import com.social.media.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Long userId, PostRequestDTO dto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = new Post();
        post.setCaption(dto.getCaption());
        post.setPhotoUrl(dto.getPhotoUrl());

        //associating post with user
        post.setUser(user);
        user.getPost().add(post);

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPostsOfUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post getPostOfUser(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post not found"));

        //verify ownership
        if(!post.getUser().getId().equals(userId)){
            throw new RuntimeException("Post does not belongs to this user");
        }
        return post;
    }

    @Override
    public Post updateUserPost(Long userId, Long postId, PostUpdateDTO dto) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post does not belong to this user"));
        //verify ownership
        if(!existingPost.getUser().getId().equals(existingUser.getId())){
            throw new RuntimeException("Post does not belongs to this user");
        }

        if(dto.getCaption()!=null){
            existingPost.setCaption(dto.getCaption());
        }
        if(dto.getPhotoUrl()!=null){
            existingPost.setPhotoUrl(dto.getPhotoUrl());
        }
        return postRepository.save(existingPost);

    }

    @Override
    public Post deleteUserPost(Long userId, Long postId) {

        Post existingPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post Not found"));
        //verify ownership
        if(!existingPost.getUser().getId().equals(userId)){
            throw new RuntimeException("Post does not belong to this user");
        }
        postRepository.delete(existingPost);
        return existingPost;
    }
}
