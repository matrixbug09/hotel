package hotel.User;

import org.springframework.stereotype.Component;

@Component
public class UserVO {

    private String nickname;
    private String id;
    private String pass;
    private String email;
    private int phoneNume;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNume() {
        return phoneNume;
    }

    public void setPhoneNume(int phoneNume) {
        this.phoneNume = phoneNume;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "nickname='" + nickname + '\'' +
                ", id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", phoneNume=" + phoneNume +
                '}';
    }
}


