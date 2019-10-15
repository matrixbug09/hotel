package hotel.User;

import org.springframework.ui.Model;

import java.util.HashMap;

public interface UserService {

    //유저 정보 조회
    void userJoin(UserVO userVO);
    String checkId(String id);
    void userInfo(Model model);
    void userUpdate(UserVO userVO);
    void userPwChange(HashMap<String, String> pass);

}
