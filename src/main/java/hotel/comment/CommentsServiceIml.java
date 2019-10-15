package hotel.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentsServiceIml implements CommentService  {

    @Autowired
    CommentDAO commentDAO;

    @Override
    public void commentInsert(CommentVO commentVO) {
        commentDAO.commentInsert(commentVO);
    }

    @Override
    public ArrayList<CommentVO> commentList(CommentVO commentVO) {
        return  (ArrayList<CommentVO>)commentDAO.commentList(commentVO);
    }
}
