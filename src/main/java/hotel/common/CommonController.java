package hotel.common;

import hotel.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;

    @PostMapping("/sessionCheck")
    public @ResponseBody String sessionCheck(HttpServletRequest request,
                                             @RequestParam("bdId") String id){
        String sessionState = "SESSION_IS_OK";
        String userID = (String)session.getAttribute("userID");
        System.out.println(id+"아이디값 파라미");

        if (!id.equals(userID)){
            System.out.println("세션체크: 아이디가 불일치합니다.");
            sessionState="SESSION_IS_FALSE";
            return sessionState;
        }
        return "sessionState";
    }
}

