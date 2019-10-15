package hotel.comment;

import hotel.Board.BoardService;
import hotel.Board.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CommentController{

    static int koko =30;
    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardVO boardVO;
    @Autowired
    HttpSession httpSession;

    @PostMapping("/commentInsert")
    public String commentsInsert(CommentVO commentVO,
                                 Model model,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam("bdId") String bdId,
                                 HttpServletResponse response) throws IOException {

        String id = (String)httpSession.getAttribute("userID");
        if (id==null){
            System.out.println("null 908098908098908908098908908908098098");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 정보를 확인해주세요.');history.go(-1);</script>");
            out.flush();
            return "home";
        }
        commentService.commentInsert(commentVO);
        redirectAttributes.addAttribute("bdNumber",commentVO.getBdNumber());
        redirectAttributes.addAttribute("bdId",bdId);
        return "redirect:/content_view";
    }

//    @GetMapping("/replyForm")
//    public String reply(HttpServletRequest request){
//        request.getParameter("level");
//    }
}
