package hotel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class UserServiceIml implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    HttpSession session;

    @Override
    public void userJoin(UserVO userVO) {

        userDAO.join(userVO);

    }

    @Override
    public String checkId(String id) {
        System.out.println("체크아이디 함수");
        System.out.println(id);




        String checkId = userDAO.checkId(id);
        System.out.println("dfsf"+checkId);
        //null값이 들어오면 equals는 null을 비교하지 못한다. 고로 다음 라인의 코드를 읽어 오지 못한다.(에러또는 동작멈춤)
//        if(checkId.equals(id)){
//            System.out.println("sjdhqwh");
//            return state;
//        }else{
//            System.out.println(stateFalse);
//            return stateFalse;
//        }
        if(checkId==null){
            return "ok";
        }else{
            System.out.println("flase873874");
            return "false";
        }

    }

    @Override
    public void userInfo(Model model){

    }



    @Override
    public void userUpdate(UserVO userVO) {

    }

    @Override
    public void userPwChange(HashMap<String, String> pass) {

    }



}
