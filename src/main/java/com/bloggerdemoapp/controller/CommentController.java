package com.bloggerdemoapp.controller;

import com.bloggerdemoapp.entity.Comment;
import com.bloggerdemoapp.entity.Post;
import com.bloggerdemoapp.repository.CommentRepository;
import com.bloggerdemoapp.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // http://localhost:8080/api/v1/comment?postId=1
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestParam long postId,
            @RequestBody Comment comment
            ){
       Post post = postRepository.findById(postId).get();
       comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
}
