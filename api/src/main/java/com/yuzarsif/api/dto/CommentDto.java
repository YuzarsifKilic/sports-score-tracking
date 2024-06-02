package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.Comment;

public record CommentDto(
    Long id,
    Long matchId,
    FootballFanDto footballFan,
    String content,
    Integer likeCount,
    String date
) {

    public static CommentDto convert(Comment comment) {
        return new CommentDto(
            comment.getId(),
            comment.getMatchId(),
            FootballFanDto.convert(comment.getSportFan()),
            comment.getContent(),
            comment.getLikeCount(),
            comment.getCreatedDate().toString());
    }
}
