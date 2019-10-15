package hotel.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
public class UserController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    UserServiceIml userServiceIml;

    @Autowired
    HttpSession httpSession;


    @GetMapping(value = "/checkId")
    public @ResponseBody
    String checkId(@RequestParam("id") String id, Model model) {
        System.out.println(id);
        String state = userService.checkId(id);
        String tes = "false";
//        System.out.println(userService.checkId(id));

        return state;
    }

    @PostMapping(value = "/memberJoin")
    public String join(UserVO userVO) {
        try {
            userService.userJoin(userVO);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return "join";
    }


    @GetMapping("/doLogout")
    public String doLogout() {
        httpSession.invalidate();
        return "home";
    }


    @PostMapping(value = "/loginCheck")
    public String login(UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            httpSession = request.getSession(true);
            UserVO dbID = userDAO.loginCheck(userVO);

            if (!userDAO.loginCheck(userVO).getPass().equals(userVO.getPass()) || dbID == null) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('아이디 또는 비밀번호 불일치.');window.location.href='login';</script>");
                out.flush();
                return "redirect:login";
            }
            if (SessionListen.getInstance().isUsing(userVO.getId())) {
                System.out.println("이미 아이디가 접속중 입니다.");
                return "login";
            }
            httpSession.setAttribute("userID", userVO.getId());
//            httpSession.setMaxInactiveInterval(1);

            /*세션 바인딩 호출*/
            SessionListen.getInstance().setSession(httpSession, userVO);
            SessionListen.getInstance().printloginUsers();
        } catch (Exception r) {
            r.printStackTrace();
            System.out.println("널포인트익셉선");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 비밀번호 불일치.');window.location.href='login';</script>");
            out.flush();
        }
        return "home";
    }

    @GetMapping(value = "/insert")
    public String insertTest(UserVO userVO, HttpServletRequest request) {
        String id = request.getParameter("id");

        userDAO.insert1(userVO);
        return "join";
    }

//    @Bean
//    public void logout() {
//        System.out.println("logout빈");
//
//    }
}