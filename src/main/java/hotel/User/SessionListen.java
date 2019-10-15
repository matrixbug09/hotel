package hotel.User;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public class SessionListen implements HttpSessionBindingListener {

    @Autowired
    UserVO userVO;

    private static SessionListen sessionListen = null;

    private static Hashtable sessionTables = new Hashtable();


    //싱글톤 동기화: 여러 쓰레드가 객체에 접근하는걸 방지, 단점 느려진다. enum을 사용할 수 있지만
    //호출 되기 전에 널 조건을 줌으로써 생성이 안 돼어 있다면 호출불가.
    public synchronized static SessionListen getInstance() {
        System.out.println(sessionListen + "싱글톤 세션 널값체");
        if (sessionListen == null) {
            sessionListen = new SessionListen();
        }
        System.out.println(sessionListen + "싱글톤 세션");
        return sessionListen;
    }

    public void setSession(HttpSession httpSession, UserVO userVO) {
        System.out.println("세션 세팅");


        httpSession.setAttribute(userVO.getId(), this);
        Enumeration<String> e = httpSession.getAttributeNames();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
        System.out.println(e + "세션 세팅");
        System.out.println("세션 세팅끝");
    }

    public Boolean isUsing(String userVO) {
        System.out.println(userVO + "유저 검색");
        return sessionTables.containsValue(userVO);
    }


    public void printloginUsers() {
        System.out.println(sessionTables.keys() + "접속 유저 세션 테이블 키");
        Enumeration e = sessionTables.keys();//getSession=httpsession값.request.getSession동일
        HttpSession httpSession = null;
        System.out.println("===========================================");
        int i = 0;
        while (e.hasMoreElements()) {
            httpSession = (HttpSession) e.nextElement();
            System.out.println(httpSession + "http세션");
            System.out.println((++i) + ".접속자:" + sessionTables.get(httpSession));
        }
        System.out.println("===========================================");
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("세션 바인딩");
        sessionTables.put(event.getSession(), event.getName());
        System.out.println(event.getSession() + "겟세션");
        System.out.println(event.getName() + "겟네임");
        System.out.println(event.getName() + "로그인완료");

        System.out.println(sessionTables + "세션테이블");
        System.out.println(SessionListen.sessionListen);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        sessionTables.remove(event.getSession());
        System.out.println(event.getName() + " 로그아웃 완료");
        System.out.println("현재 접속자 수 : ");
    }
}


