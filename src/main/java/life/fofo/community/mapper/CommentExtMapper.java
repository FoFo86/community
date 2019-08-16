package life.fofo.community.mapper;


import life.fofo.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}