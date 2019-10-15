package hotel.Board;

public class PageVO {
    private int totalpage;
    private int countlist;
    private int pagenum;
    private int contentnum=10;
    private int startPage=1;
    private int endPage=5;
    private boolean prev=false;//첫페이지 초기 설정 안보이게
    private boolean next=true;
    private int CurrentBlock=1;
    private int lastBlock;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getCountlist() {
        return countlist;
    }

    public void setCountlist(int countlist) {
        this.countlist = countlist;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getContentnum() {
        return contentnum;
    }

    public void setContentnum(int contentnum) {
        this.contentnum = contentnum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int currentBlock) {
        this.startPage = (currentBlock*5)-4;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int lastBlock,int currentBlock ) {
        if (lastBlock==currentBlock){
            this.endPage = carPage(getTotalpage(),getContentnum());
        }
        else {
            this.endPage = getStartPage()+4;
        }
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getCurrentBlock() {
        return CurrentBlock;
    }

    public void setCurrentBlock(int pagenum) {
        this.CurrentBlock= pagenum/5;
        if (pagenum%5>0) {
            this.CurrentBlock++;
        }
    }

    public int getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(int totalpage) {
        this.lastBlock = totalpage/(5*this.contentnum);
        if (totalpage%(5*this.contentnum)>0) {
            this.lastBlock++;
        }
    }

    public int carPage(int totalcount, int contentnum){
        int totalpage = totalcount/contentnum;
        if (totalcount%contentnum>0){
            totalpage++;
        }
        if (totalpage<this.pagenum){
            this.pagenum=totalpage;
        }
        return totalpage;
    }
    public void prevnext(int pagenum) {

        if (pagenum>0 && pagenum<6) {
            setPrev(false);
            setNext(true);
            if (lastBlock==1){
                setNext(false);
            }
        }
        else if(getLastBlock()==getCurrentBlock()) {//5개씩 페이지의 그룹번호를 지정한다.
            //그룹 번호가 3이라는 것은 해당 페이지 그룹이 마지막이라는 것이기에 이전 화살표만 활성화한다
            //이 두개가 같다면 마지막 블록이므로 이전만 있고 이후가 없다.
            setPrev(true);
            setNext(false);
        }
        else {
            setPrev(true);
            setNext(true);
        }
    }

}
