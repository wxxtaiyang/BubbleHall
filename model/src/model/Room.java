package model;


public class Room {

  private int id;
  private String mid;
  private String againstA;
  private String againstB;
  private String againstC;
  private String againstD;
  private String winner;

  public Room(int id, String mid, String againstA, String againstB, String againstC, String againstD, String winner) {
    this.id = id;
    this.mid = mid;
    this.againstA = againstA;
    this.againstB = againstB;
    this.againstC = againstC;
    this.againstD = againstD;
    this.winner = winner;
  }

  public Room() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getAgainstA() {
    return againstA;
  }

  public void setAgainstA(String againstA) {
    this.againstA = againstA;
  }

  public String getAgainstB() {
    return againstB;
  }

  public void setAgainstB(String againstB) {
    this.againstB = againstB;
  }

  public String getAgainstC() {
    return againstC;
  }

  public void setAgainstC(String againstC) {
    this.againstC = againstC;
  }

  public String getAgainstD() {
    return againstD;
  }

  public void setAgainstD(String againstD) {
    this.againstD = againstD;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String winner) {
    this.winner = winner;
  }

}
