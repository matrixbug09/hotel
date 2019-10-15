package hotel.Book;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class BookServiceIml  implements bookService{

    @Autowired
    BookDAO bookDAO;

    @Override
    public void bookinsert(BookVO bookVO) {
        bookDAO.bookinsert(bookVO);


    }

    @Override
    public ArrayList<BookVO> mypage(String id) {

    ArrayList<BookVO> mylist =bookDAO.mypage(id);
       return mylist;

    }
}
