package hotel.Book;

import hotel.Room.RoomDAO;
import hotel.Room.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class BookController {

    @Autowired
    bookService bookService;

    @Autowired
    BookDAO bookDAO;
    @Autowired
    RoomDAO roomDAO;
    @Autowired
    RoomVO roomVO;

    @Autowired
    HttpSession httpSession;

    /*
     * 트랜잭션: ROOMS TABLE 과 BOOK TABLE 처리.
     * 먼저 Roomcheck 함수에서 빈 방 유효성 체크 후 bookinsert실행
     * 만약 db의 방 갯수보다 더 많은 방을 요청하면 실패 처리.
     */
    @PostMapping(value = "/book")
    @Transactional
    public void bookbinder(BookVO bookVO, @RequestParam("stay") int stay, HttpSession httpSession1) {
        httpSession1.getAttribute("hjg");
        httpSession.getAttribute("hh");

        //예약 정보 insert
        bookService.bookinsert(bookVO);
        String[] room = {"Standard", "Superior", "Deluxe"};
        //예약 정보 처리 후. 객실 정보에서 남은 방 갯수를 빼준다.
        for (int x = 0; x < 3; x++) {
            if (bookVO.getRoom_type().equals(room[x])) {

                roomVO.setRoom_date(bookVO.getCheckin_date());
                roomVO.setRoom_type(room[x]);
                int index = roomDAO.roomcheck(roomVO).getRoom_index();

                for (int i = 0; i < stay; i++) {
                    roomVO.setRoom_index(index + i);
                    roomVO.setRoom_remain(roomDAO.stayroomcheck(roomVO).getRoom_remain() - bookVO.getRequired_room());
                    roomDAO.updateRoom(roomVO);
                }
            }
        }
    }

    @GetMapping("/mypage")
    public String mypage(Model model) {
        String user = (String) httpSession.getAttribute("userID");
        ArrayList<BookVO> bookinfo = bookService.mypage(user);
        System.out.println(bookinfo.size());
        model.addAttribute("book", bookinfo);
        return "mypage";
    }

}


        /* for if문 설정 전 세팅
        if (room.equals("Standard")){
            roomVO.setRoom_date(bookVO.getCheckin_date());
            roomVO.setRoom_type("Standard");
            int index = roomDAO.roomcheck(roomVO).getRoom_index();

            for (int i=0; i<stay; i++){
                roomVO.setRoom_index(index+i);
                System.out.println(roomVO.getRoom_index());
                roomVO.setRoom_remain(roomDAO.stayroomcheck(roomVO).getRoom_remain() - bookVO.getRequired_room());
                roomDAO.updateRoom(roomVO);
            }
        }else if (bookVO.getRoom_type().equals("Superior")){
            roomVO.setRoom_date(bookVO.getCheckin_date());
            roomVO.setRoom_type("Superior");
            int index = roomDAO.roomcheck(roomVO).getRoom_index();

            for (int i=0; i<stay; i++){
                roomVO.setRoom_index(index+i);
                roomVO.setRoom_remain(roomDAO.stayroomcheck(roomVO).getRoom_remain() - bookVO.getRequired_room());
                roomDAO.updateRoom(roomVO);
            }
        }else {
            roomVO.setRoom_date(bookVO.getCheckin_date());
            roomVO.setRoom_type("Deluxe");
            int index = roomDAO.roomcheck(roomVO).getRoom_index();

            for (int i=0; i<stay; i++){
                roomVO.setRoom_index(index+i);
                roomVO.setRoom_remain(roomDAO.stayroomcheck(roomVO).getRoom_remain() - bookVO.getRequired_room());
                roomDAO.updateRoom(roomVO);
            }
        }
*/