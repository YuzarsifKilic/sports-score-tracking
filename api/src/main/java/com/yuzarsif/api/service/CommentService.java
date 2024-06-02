package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CommentDto;
import com.yuzarsif.api.dto.CreateCommentRequest;
import com.yuzarsif.api.exception.CommentFoundException;
import com.yuzarsif.api.model.Comment;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final SportFanService sportFanService;

    public CommentService(CommentRepository commentRepository, SportFanService sportFanService) {
        this.commentRepository = commentRepository;
        this.sportFanService = sportFanService;
    }

    public List<CommentDto> getCommentsByMatchId(Long matchId, SportType sportType) {
        List<Comment> byMatchIdAndSportType = commentRepository.findByMatchIdAndSportType(matchId, sportType);
        return commentRepository.findByMatchIdAndSportType(matchId, sportType)
                .stream()
                .map(CommentDto::convert)
                .toList();
    }

    public void createComment(CreateCommentRequest request) {
        SportFan sportFan = sportFanService.findById(request.footballFanId());

        Comment comment = Comment
                .builder()
                .sportFan(sportFan)
                .matchId(request.matchId())
                .content(request.comment())
                .likeCount(0)
                .sportType(request.sportType())
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
