package life.chen.community.mapper;

import life.chen.community.model.Comment;
import life.chen.community.model.CommentExample;
import life.chen.community.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}