package hotel.comment;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CommentDAO {

    @Autowired
    private SqlSession sqlSession;

    void commentInsert(CommentVO commentVO){
        System.out.println(commentVO.getComContent());
        System.out.println(commentVO.getComId());
        System.out.println(commentVO.getBdNumber());
        System.out.println(commentVO.getLevel());
        System.out.println(commentVO.getComgrp());
        String level = String.valueOf(commentVO.getLevel());

        if (level.equals("0")){
            System.out.println("실");
            sqlSession.insert("CommentMapper.comInsert",commentVO);
        } else {
            sqlSession.insert("CommentMapper.comment_reply",commentVO);

        }


        sqlSession.update("CommentMapper.comUpdate",commentVO);

    }
    public ArrayList<CommentVO> commentList(CommentVO commentVO){
        return (ArrayList)sqlSession.selectList("CommentMapper.comList",commentVO);
    }

}
