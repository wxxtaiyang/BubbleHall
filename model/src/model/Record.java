package model;


public class Record {

  private int id;
  private String sendId;
  private String recvId;
  private int recType;
  private String content;
  private String recTime;
  private String state;

  public Record(int id, String sendId, String recvId, int recType, String content, String recTime, String state) {
    this.id = id;
    this.sendId = sendId;
    this.recvId = recvId;
    this.recType = recType;
    this.content = content;
    this.recTime = recTime;
    this.state = state;
  }

  public Record() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getSendId() {
    return sendId;
  }

  public void setSendId(String sendId) {
    this.sendId = sendId;
  }


  public String getRecvId() {
    return recvId;
  }

  public void setRecvId(String recvId) {
    this.recvId = recvId;
  }


  public int getRecType() {
    return recType;
  }

  public void setRecType(int recType) {
    this.recType = recType;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRecTime() {
    return recTime;
  }

  public void setRecTime(String recTime) {
    this.recTime = recTime;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}
