package model;


public class Dress {

  private int id;
  private String name;
  private String image;
  private int rid;

  public Dress(int id, String name, String image, int rid) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.rid = rid;
  }

  public Dress() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public int getRid() {
    return rid;
  }

  public void setRid(int rid) {
    this.rid = rid;
  }

  @Override
  public String toString() {
    return name;
  }
}
