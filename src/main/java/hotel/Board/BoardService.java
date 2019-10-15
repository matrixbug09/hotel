package hotel.Board;

import java.util.ArrayList;
import java.util.List;

public interface BoardService {

    public List<BoardVO> list(int pagenum, int contentnum);
    public void write(BoardVO boardVO);
    ArrayList<BoardVO> selectedList();
    ArrayList<BoardVO> contentView(BoardVO boardVO);

    public void delete(BoardVO boardVO);
    public void modify(BoardVO boardVO);
    public void reply();

}
