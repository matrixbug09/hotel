package hotel.Room;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class RoomVO {

    private String room_date;
    private String checkinDate;
    private String room_type;
    private int room_index;
    private int remain_room;
    private int room_remain;

    public String getRoom_date() {
        return room_date;
    }

    public void setRoom_date(String room_date) {
        this.room_date = room_date;
    }

    public int getRoom_remain() {
        return room_remain;
    }

    public void setRoom_remain(int room_remain) {
        this.room_remain = room_remain;
    }




    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getRoom_index() {
        return room_index;
    }

    public void setRoom_index(int room_index) {
        this.room_index = room_index;
    }

    public int getRemain_room() {
        return remain_room;
    }

    public void setRemain_room(int remain_room) {
        this.remain_room = remain_room;
    }


//    @Override
//    public String toString() {
//        return "RoomVO{" +
//                "room_date='" + room_date + '\'' +
//                ", checkinDate='" + checkinDate + '\'' +
//                ", room_type='" + room_type + '\'' +
//                ", room_index=" + room_index +
//                ", remain_room=" + remain_room +
//                ", room_remain=" + room_remain +
//                '}';
//    }

    //ToStringBuilder 사용 apache commons maven 추가
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
