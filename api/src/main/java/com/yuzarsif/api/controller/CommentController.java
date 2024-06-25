package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CommentDto;
import com.yuzarsif.api.dto.CreateCommentRequest;
import com.yuzarsif.api.model.Comment;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByMatchId(@RequestParam Long matchId, @RequestParam SportType sportType) {
        return ResponseEntity.ok(commentService.getCommentsByMatchId(matchId, sportType));
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentRequest request, Authentication authentication) {
        commentService.createComment(request, authentication);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> likeComment(@PathVariable Long commentId, Authentication authentication) {
        commentService.likeComment(commentId, authentication);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication);
        return ResponseEntity.ok().build();
    }
}
