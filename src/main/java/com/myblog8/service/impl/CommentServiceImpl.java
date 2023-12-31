package com.myblog8.service.impl;

import com.myblog8.entity.Comment;
import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFound;
import com.myblog8.payload.CommentDto;
import com.myblog8.repository.CommentRepository;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;

    private PostRepository postRepo;

    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo,ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto saveComment(CommentDto dto, Long postId) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post not found with id:" + postId)
        );

//        Comment comment = new Comment();
//        comment.setId(dto.getId());
//        comment.setName(dto.getName());
//        comment.setEmail(dto.getEmail());
//        comment.setBody(dto.getBody());
//        comment.setPost(post);

        Comment comment = mapToComment(dto);
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto commentDto = new CommentDto();
        commentDto.setId(savedComment.getId());
        commentDto.setName(savedComment.getName());
        commentDto.setEmail(savedComment.getEmail());
        commentDto.setBody(savedComment.getBody());


        return commentDto;
    }

    @Override
    public void deleteCommentId(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto updateByComment(CommentDto commentDto, Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(
                ()->new ResourceNotFound("comment not found by id:"+id)
        );
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment savedComment = commentRepo.save(comment);

        CommentDto Dto = new CommentDto();
        Dto.setId(savedComment.getId());
        Dto.setName(savedComment.getName());
        Dto.setEmail(savedComment.getEmail());
        Dto.setBody(savedComment.getBody());

        return Dto;
    }

    public Comment mapToComment(CommentDto dto){
        Comment comment = modelMapper.map(dto, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(dto.getId());
//        comment.setName(dto.getName());
//        comment.setEmail(dto.getEmail());
//        comment.setBody(dto.getBody());
        return comment;

    }

   
}
