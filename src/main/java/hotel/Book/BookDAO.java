package hotel.Book;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;

@Repository
public class BookDAO implements bookService {

    private static final String namespace = "BookMapper";

    @Inject
    private SqlSession sqlSession;

    public void bookinsert(BookVO bookVO) {
        System.out.println("--------0000--------0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0--0-");
        System.out.println(bookVO.toString());
         sqlSession.insert(namespace + ".bookinsert", bookVO);
    }

    public ArrayList<BookVO> mypage(String id) {


        return  (ArrayList)sqlSession.selectList(namespace + ".mypage", id);

    }
}
