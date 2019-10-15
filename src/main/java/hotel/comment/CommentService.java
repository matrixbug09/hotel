package hotel.comment;

import java.util.ArrayList;

public interface CommentService {

    void commentInsert(CommentVO commentVO);
    ArrayList<CommentVO> commentList(CommentVO commentVO);


}
