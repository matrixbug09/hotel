package hotel.Book;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class BookVO {

    private String book_id;
    private String room_type;
    private String checkin_date;
    private String checkout_date;
    private int required_room;
    private int total_pay;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String book_type) {
        this.room_type = book_type;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public int getRequired_room() {
        return required_room;
    }

    public void setRequired_room(int required_room) {
        this.required_room = required_room;
    }

    public int getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(int total_pay) {
        this.total_pay = total_pay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
