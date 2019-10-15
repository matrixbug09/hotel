package hotel.Room;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import groovy.transform.ToString;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class RoomController {


    @Autowired
    private RoomDAO roomDao;
    @Autowired
    private RoomVO roomVo;

    public void tesz() {


        String index = roomVo.getRoom_type();

        System.out.println("테스트 인덱스" + index);
    }

    @GetMapping("/getRoomList")
    public
    JsonObject getRoom(@RequestParam("required_room") String required_room
            , @RequestParam("room_type") String room_type
            , Model model, HttpServletResponse response) throws IOException {
        System.out.println(required_room);
        ArrayList<RoomVO> room_list = (ArrayList)roomDao.getRoom(room_type,required_room);
        Iterator<RoomVO> it = room_list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(String.valueOf(room_list));
        JsonObject jsonObject = new JsonObject();
        for (int i = 0 ; i<room_list.size(); i++) {

            jsonObject.add(String.valueOf(i),jsonArray.get(i).getAsJsonObject());
            System.out.println(jsonObject.get(String.valueOf(i)));
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonObject);
        out.flush();
        return jsonObject;
    }


    @RequestMapping(value = "/room")
    public String test(Model model) {

        String skill = "sdf";
        int strList = 323154;
        model.addAttribute("list", strList);
        model.addAttribute("skill", skill);
        return "room";
    }


    @GetMapping(value = "/qkqh3")
    public String wer(Model model) {
        String skill = "sdf";
        int strList = 323154;
        model.addAttribute("list", strList);
        model.addAttribute("skill", skill);
        return "login";
    }

    @GetMapping(value = "/roomcheck")
    public @ResponseBody
    String roomcheck(
            RoomVO roomVo,
            @RequestParam("stay") int stay,
            @RequestParam("required_room") int required_room) {

        //stay 값은 스프링 커맨드객체 VO에 등록 되지 않았으므로 직접 리퀘스트파람하여 값을 가져옴.

        System.out.println("stay값..." + stay);
        System.out.println("required_room..." + required_room);
        System.out.println(roomVo.toString());

        int index = roomDao.roomcheck(roomVo).getRoom_index();

        System.out.println("인덱스 " + index);
        System.out.println(roomDao.roomcheck(roomVo).getRoom_index());
        System.out.println("직접 가져온 인덱스" + roomVo.getRoom_index());


        for (int i = 0; i < stay; i++) {
            roomVo.setRoom_index(index + i);
            System.out.println(roomDao.RemainRoomCheck(roomVo).getRoom_remain());
            if (roomDao.RemainRoomCheck(roomVo).getRoom_remain() - required_room < 0) {
                System.out.println(roomDao.RemainRoomCheck(roomVo).getRoom_remain());
                System.out.println("가져오기 실패 빈방 체크 확인");
                return roomVo.getRoom_date();
            }
        }
        return "AllSerene";
    }

    @Scheduled(cron = "* * 12 * * *")
    @Transactional
    public void roomScheduler() {
        TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
        Calendar calendar = Calendar.getInstance(tz);
        calendar.setTimeZone(tz);
        String format = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(tz);
        /*
        for문 rommtype은 해쉬맵에서 키 값을 위한 것
        */
        HashMap type = new HashMap();
        type.put(1, "Standard");
        type.put(2, "Superior");
        type.put(3, "Deluxe");

        List<Integer> list_index = new ArrayList<Integer>();

        for (int rommtype = 1; rommtype < 4; rommtype++) {
            roomVo.setRoom_date(dateFormat.format(calendar.getTime()));
            roomVo.setRoom_type((String) type.get(rommtype));
            list_index.add(roomDao.roomcheck(roomVo).getRoom_index());
        }
        calendar.add(Calendar.DATE, 31);

        for (int i = 0; i < 3; i++) {
            roomVo.setRoom_date(dateFormat.format(calendar.getTime()));
            roomVo.setRoom_index(list_index.get(i));
            roomDao.roomchange(roomVo);
        }
    }
}
        /*
        System.out.println("스케쥴러 호출 입니다. set date");
        roomVo.setRoom_date(dateFormat.format(calendar.getTime()));
        System.out.println("스케쥴러 호출 입니다.list_add index setting");
        //for문이 돌면서 list_index에 스탠다드 0 슈페리얼1 디럭스 2 순서로 담아 둔다.
        list_index.add(roomDao.roomcheck(roomVo).getRoom_index());

        System.out.println("스케쥴러 호출 입니다.setting date 31 day");
        31일 후 날짜 로 변경한다. 인덱스가 1이면 31일 후 날짜가 1번 인덱스가 된다. 1~31 순환이 반복된다.
        */

        /* 스케쥴러 날짜 테스팅.
        for(int day=0; day<62; day++){
        calendar.add(Calendar.DATE, 31+day);
        int in=1;
        System.out.println(calendar);
        //31일 후 날짜 세팅
        roomVo.setRoom_date(dateFormat.format(calendar.getTime()));
        System.out.println("스케쥴러 호출 입니다.after 31");
        calendar.add(Calendar.DATE, -31-day);
        //list_index 값 가져오기
        roomVo.setRoom_index(1+day);
        System.out.println(list_index.get(0));
        System.out.println("스케쥴러 호출 입니다.index call");
        roomDao.roomchange(roomVo);
        System.out.println("스케쥴러 호출 입니다.room change");
        }
        */