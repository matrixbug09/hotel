package hotel.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceIml implements BoardService {


    @Autowired
    BoardDAO boardDAO;
    @Autowired
    HttpSession httpSession;

    @Override
    public List<BoardVO> list(int pagenum, int contentnum) {
        List<BoardVO> boarList = boardDAO.boardList2(pagenum,contentnum);
        // String textReplace = boardVO.getBdContent().replaceAll("\\<.*?\\>", "");
//        textReplace.replaceAll("\u00a0","");
        // boardVO.setBdContent(textReplace);
        return boarList;
    }

    @Override
    public void write(BoardVO boardVO) {
        boardDAO.writeInsert(boardVO);
    }

    @Override
    public ArrayList<BoardVO> selectedList() {
       ArrayList<BoardVO> postlist = boardDAO.boardList();

        return postlist;
    }
    @Override
    public ArrayList<BoardVO> contentView(BoardVO boardVO) {
        ArrayList<BoardVO> postlist = boardDAO.contentView(boardVO);
        return postlist;
    }
    @Override
    public void delete(BoardVO boardVO) {
        boardDAO.deleteContent(boardVO);
    }

    @Override
    public void modify(BoardVO boardVO) {
        boardDAO.modifyPost(boardVO);
    }

    @Override
    public void reply() {
    }

}
