package hotel.Board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<BoardVO> boardList2(int pagenum, int contentnum){
        Map<String, Integer> map = new HashMap<>();
        map.put("pagenum",pagenum);//먗 번째 페이지 인지 설정, 3페이지면 3페이지 게시글 10개 출력
        map.put("contentnum",contentnum);//1페이지에 10개 게시글 출력 갯수(현재10개)

        return sqlSession.selectList("BoardMapper.boardList2",map);
    }
    public void writeInsert(BoardVO boardVO){
        sqlSession.selectOne("BoardMapper.writeInsert",boardVO);
    }

    public ArrayList<BoardVO> boardList(){
        return (ArrayList)sqlSession.selectList("BoardMapper.boardList");
    }

    public ArrayList<BoardVO> contentView(BoardVO boardVO){
        return (ArrayList)sqlSession.selectList("BoardMapper.contentView",boardVO);
    }

    public void modifyPost(BoardVO boardVO){
        sqlSession.update("BoardMapper.modifyPost",boardVO);
    }

    public void deleteContent(BoardVO boardVO){
        sqlSession.delete("BoardMapper.deleteContent",boardVO);
    }
}
