package hotel.Board;

import org.springframework.stereotype.Component;


import java.sql.Timestamp;

@Component
public class BoardVO {
    private String bdId;
    private String bdTitle;
    private String bdContent;
    private Timestamp bdDate;
    private int bdHit;
    private int bdComments;
    private int bdGroup;
    private int bdIndent;
    private int bdStep;
    private int bdNumber;

    public String getBdId() {
        return bdId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public String getBdTitle() {
        return bdTitle;
    }

    public void setBdTitle(String bdTitle) {
        this.bdTitle = bdTitle;
    }

    public String getBdContent() {
        return bdContent;
    }

    public void setBdContent(String bdContent) {
        this.bdContent = bdContent;
    }

    public Timestamp getBdDate() {
        return bdDate;
    }

    public void setBdDate(Timestamp bdDate) {
        this.bdDate = bdDate;
    }


    public int getBdHit() {
        return bdHit;
    }

    public void setBdHit(int bdHit) {
        this.bdHit = bdHit;
    }

    public int getBdComments() {
        return bdComments;
    }

    public void setBdComments(int bdComments) {
        this.bdComments = bdComments;
    }

    public int getBdGroup() {
        return bdGroup;
    }

    public void setBdGroup(int bdGroup) {
        this.bdGroup = bdGroup;
    }

    public int getBdIndent() {
        return bdIndent;
    }

    public void setBdIndent(int bdIndent) {
        this.bdIndent = bdIndent;
    }

    public int getBdStep() {
        return bdStep;
    }

    public void setBdStep(int bdStep) {
        this.bdStep = bdStep;
    }

    public int getBdNumber() {
        return bdNumber;
    }

    public void setBdNumber(int bdNumber) {
        this.bdNumber = bdNumber;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "bdId='" + bdId + '\'' +
                ", bdTitle='" + bdTitle + '\'' +
                ", bdContent='" + bdContent + '\'' +
                ", bdDate=" + bdDate +
                ", bdHit=" + bdHit +
                ", bdComments=" + bdComments +
                ", bdGroup=" + bdGroup +
                ", bdIndent=" + bdIndent +
                ", bdStep=" + bdStep +
                ", bdNumber=" + bdNumber +
                '}';
    }
}
