package com.myblog8.service;

import com.myblog8.payload.CommentDto;

public interface CommentService {

    CommentDto saveComment(CommentDto dto, Long postId);

    void deleteCommentId(Long id);


    CommentDto updateByComment(CommentDto commentDto, Long id);
}
