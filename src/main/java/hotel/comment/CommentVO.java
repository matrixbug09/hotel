package hotel.comment;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommentVO {

    private String comId;
    private String comContent;
    private Timestamp comDate;
    private int bdNumber;
    private  int comgrp;
    private  int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getComgrp() {
        return comgrp;
    }

    public void setComgrp(int comgrp) {
        this.comgrp = comgrp;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Timestamp getComDate() {
        return comDate;
    }

    public void setComDate(Timestamp comDate) {
        this.comDate = comDate;
    }

    public int getBdNumber() {
        return bdNumber;
    }

    public void setBdNumber(int bdNumber) {
        this.bdNumber = bdNumber;
    }
}
