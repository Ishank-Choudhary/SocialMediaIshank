package com.social.media.controllers;

import com.social.media.models.Post;
import com.social.media.payload.PostRequestDTO;
import com.social.media.payload.PostUpdateDTO;
import com.social.media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    //create post for a user
    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO dto){
        Post newPost = postService.createPost(userId,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    //get all post of a user
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getUserPost(@PathVariable Long userId){
        List<Post> posts = postService.getAllPostsOfUser(userId);
        return  ResponseEntity.ok(posts);
    }

    //get one post of a user
    @GetMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> getPostOfUser(
            @PathVariable Long userId,
            @PathVariable Long postId) {

        Post post = postService
                .getPostOfUser(userId, postId);

        return ResponseEntity.ok(post);
    }


    //update a post of a user
    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long userId,
            @PathVariable Long postId,
            @RequestBody PostUpdateDTO dto) {

        Post updatedPost = postService
                .updateUserPost(userId, postId, dto);

        return ResponseEntity.ok(updatedPost);
    }


    //delete a post of a user
    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long userId,
            @PathVariable Long postId) {

        postService.deleteUserPost(userId, postId);

        return ResponseEntity
                .ok("Post deleted successfully");
    }
}
