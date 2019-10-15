package hotel.navi;

import hotel.Board.BoardVO;
import hotel.Book.BookVO;
import hotel.User.SessionListen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class naviController {

@Autowired
BookVO bookVO;

    @GetMapping("/")
    public String main(){
        return "home";

    }
    @GetMapping(value = "/home")
    public String home(){
        return "home";

    }
    @GetMapping("/portofolio")
    public String portfolio(){
        return "portofolio";
    }

    @GetMapping(value = "/login")
    public String login2(){
        return "login";
    }

    @GetMapping(value = "/join")
    public String join(){
        return "join";

    }
    @GetMapping("/writePage")
    public String  writePage(){
        return "writePage";
    }

    @GetMapping("/content_modify")
    public String modifyView(Model model, BoardVO boardVO){
        model.addAttribute("board",boardVO);
        return "board/content_modify";
    }

}
