package model;


public class Map {

  private int id;
  private String title;
  private String image;
  private int obstacles;

  public Map(int id, String title, String image, int obstacles) {
    this.id = id;
    this.title = title;
    this.image = image;
    this.obstacles = obstacles;
  }

  public Map() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public int getObstacles() {
    return obstacles;
  }

  public void setObstacles(int obstacles) {
    this.obstacles = obstacles;
  }

}
