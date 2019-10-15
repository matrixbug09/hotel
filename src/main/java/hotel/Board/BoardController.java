package hotel.Board;

import hotel.comment.CommentService;
import hotel.comment.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    @Autowired
    CommentService commentService;

    @Autowired
    HttpSession session;

    @GetMapping("/content_view")
    public String contentView(BoardVO boardVO, Model model,CommentVO commentVO){
        String id = (String)session.getAttribute("userID");
        ArrayList<CommentVO> comment = commentService.commentList(commentVO);
        model.addAttribute("board",boardService.contentView(boardVO));
        model.addAttribute("comment",comment);
        return "board/content_view";
    }
    @PostMapping("/writeBox")
    public String writeBox(BoardVO boardVO, Model model,HttpSession httpSession
            ,HttpServletResponse response,
                           RedirectAttributes redirectAttributes) throws IOException {
        if (httpSession.getAttribute("userID")==null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 정보를 확인해주세요.')window.location.href='login';</script>");
            out.flush();
            return "home";
        }
        boardVO.setBdId((String)httpSession.getAttribute("userID"));
        boardService.write(boardVO);
        redirectAttributes.addAttribute("pagenum",1);
        redirectAttributes.addAttribute("contentnum",10);
        return "redirect:/list";
    }

    @GetMapping("/writeView")
    public String writeView(BoardVO boardVO, Model model){
        ArrayList<BoardVO> boardView  = boardService.selectedList();
        model.addAttribute("pagenum",1);
        model.addAttribute("contentnum",10);
        model.addAttribute("boardView",boardView);

        return "list";
    }
    @GetMapping("/list")
    public String boardList(HttpServletRequest request,Model model){
        if (Integer.parseInt(request.getParameter("contentnum"))>100){
            return "index";
        }
        PageVO pageVO = new PageVO();
        int pagenum= Integer.parseInt(request.getParameter("pagenum"));
        int contentnum = Integer.parseInt(request.getParameter("contentnum"));
        ArrayList<BoardVO> boardView  = boardService.selectedList();
        pageVO.setTotalpage(boardView.size());
        pageVO.setPagenum(pagenum-1);
        pageVO.setContentnum(contentnum);
        pageVO.setCurrentBlock(pagenum);
        pageVO.setLastBlock(pageVO.getTotalpage());

        pageVO.prevnext(pagenum);
        pageVO.setStartPage(pageVO.getCurrentBlock());
        pageVO.setEndPage(pageVO.getLastBlock(),pageVO.getCurrentBlock());

        List<BoardVO> list = new ArrayList<>();
        list = boardService.list(pageVO.getPagenum()*10,contentnum);
        model.addAttribute(pageVO);
        model.addAttribute("boardView",list);
        return "list";
    }

    @GetMapping("modifyBoard")
    public String modifyBoard(HttpSession httpSession, BoardVO boardVO){

       // String textReplace = boardVO.getBdContent().replaceAll("\\<.*?\\>", "");
//        textReplace.replaceAll("\u00a0","");
       // boardVO.setBdContent(textReplace);
        if (httpSession.getAttribute("userID")==null){

            return "login";
        }
        boardService.modify(boardVO);
        return "home";
    }

    @GetMapping("/deleteContent")
    public @ResponseBody String deleteContent(HttpSession httpSession, BoardVO boardVO) {
        String id = boardVO.getBdId().trim();
        String session = (String)httpSession.getAttribute("userID");
        // null || equals와 equals || null 실행이 다르다 확인 해볼 것
        if (session == null || !session.equals(id)){

            return "false";
        }

            boardService.delete(boardVO);
        return "true";

    }


}
