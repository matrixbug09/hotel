package hotel.Book;

import java.util.ArrayList;

public interface bookService {
    public void  bookinsert(BookVO bookVO);
    ArrayList<BookVO> mypage(String id);
}
