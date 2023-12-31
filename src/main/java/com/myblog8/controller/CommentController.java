package com.myblog8.controller;

import com.myblog8.payload.CommentDto;
import com.myblog8.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/api/comments/{postId}
    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto, @PathVariable Long postId){

        CommentDto dto = commentService.saveComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        commentService.deleteCommentId(id);
        return new ResponseEntity<>("comment is deleted", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateByComment(@RequestBody CommentDto commentDto, @PathVariable Long id){
        CommentDto dto = commentService.updateByComment(commentDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
