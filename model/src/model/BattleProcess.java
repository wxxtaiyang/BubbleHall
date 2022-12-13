package model;


public class BattleProcess {
    private int id;
    private String uaid;
    private String ubid;
    private String win;
    private String timeEnd;

    public BattleProcess(int id, String uaid, String ubid, String win, String timeEnd) {
        this.id = id;
        this.uaid = uaid;
        this.ubid = ubid;
        this.win = win;
        this.timeEnd = timeEnd;
    }

    public BattleProcess() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUaid() {
        return uaid;
    }

    public void setUaid(String uaid) {
        this.uaid = uaid;
    }

    public String getUbid() {
        return ubid;
    }

    public void setUbid(String ubid) {
        this.ubid = ubid;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
