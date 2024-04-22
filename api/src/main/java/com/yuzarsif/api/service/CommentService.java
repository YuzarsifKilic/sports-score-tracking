package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateCommentRequest;
import com.yuzarsif.api.exception.CommentFoundException;
import com.yuzarsif.api.model.Comment;
import com.yuzarsif.api.model.FootballFan;
import com.yuzarsif.api.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FootballFanService footballFanService;

    public CommentService(CommentRepository commentRepository, FootballFanService footballFanService) {
        this.commentRepository = commentRepository;
        this.footballFanService = footballFanService;
    }

    public void createComment(CreateCommentRequest request) {
        FootballFan footballFan = footballFanService.findById(request.footballFanId());

        Comment comment = Comment
                .builder()
                .footballFan(footballFan)
                .content(request.comment())
                .likeCount(0)
                .build();

        commentRepository.save(comment);
    }

    public void likeComment(Long commentId) {
        Comment comment = findById(commentId);
        comment.setLikeCount(comment.getLikeCount() + 1);
        commentRepository.save(comment);
    }

    protected Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentFoundException("Comment not found by id : " + id));
    }

    public void deleteComment(Long commentId) {
        findById(commentId);
        commentRepository.deleteById(commentId);
    }
}
