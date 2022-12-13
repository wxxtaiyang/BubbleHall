package model;


import java.awt.*;
public class Role {

    private int id;
    private String name;
    private String image;
    private String hat;
    private String clothes;
    // 下面属性数据库不存在
    private int pointX;// 坐标
    private int pointY;
    private int speed;// 速度
    private int blood;// 血量
    private int bomNum;// 炸弹数量
    private int power;// 威力
    private String direction;// 方向
    private boolean flag;// 是否受伤害

    public Role(int id, String name, String image, String hat, String clothes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.hat = hat;
        this.clothes = clothes;
        speed = 1;
        blood = 3;
        bomNum = 20;
        power = 1;
    }

    public Role() {
        speed = 2;
        blood = 3;
        bomNum = 20;
        power = 1;
    }

    public void setNormal() {
        speed = 2;
        blood = 3;
        bomNum = 20;
        power = 1;
        flag= true;
    }

    public void roleMove(String direction,int[][] map) {
        this.direction = direction;
        switch (direction) {
            case "W":
                if (pointY - speed <= 0) {
                    pointY = 0;
                } else if(checkCollision(map,pointX,pointY-speed)){
                    break;
                } else {
                    pointY -= speed;
                }
                break;
            case "S":
                if (pointY + speed >= 560) {
                    pointY = 560;
                } else if(checkCollision(map,pointX,pointY+speed)){
                    break;
                } else {
                    pointY += speed;
                }
                break;
            case "A":
                if (pointX - speed <= 0) {
                    pointX = 0;
                } else if(checkCollision(map,pointX-speed,pointY)){
                    break;
                } else {
                    pointX -= speed;
                }
                break;
            case "D":
                if (pointX + speed > 560) {
                    pointX = 560;
                } else if(checkCollision(map,pointX+speed,pointY)){
                    break;
                } else {
                    pointX += speed;
                }
                break;
        }
    }
// 移动碰撞检测
    public boolean checkCollision(int[][] map,int x,int y) {
        Rectangle play = new Rectangle(x, y, 40, 40);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Rectangle m = new Rectangle(i*40, j*40, 40, 40);
                if(map[i][j] != 0 && map[i][j] != 10 && map[i][j] != 21 && map[i][j] < 30){
                    if(play.intersects(m)) {
                        switch (direction) {
                            case "W":
                                pointY = (j + 1) * 40;
                                break;
                            case "S":
                                pointY = (j - 1) * 40;
                                break;
                            case "A":
                                pointX = (i + 1) * 40;
                                break;
                            case "D":
                                pointX = (i - 1) * 40;
                                break;
                        }
                        return true;
                    }
                }else if(map[i][j] == 10 && !play.intersects(m)){
                    map[i][j] = 20;
                }
            }
        }
        return false;
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

    public String getHat() {
        return hat;
    }

    public void setHat(String hat) {
        this.hat = hat;
    }

    public String getClothes() {
        return clothes;
    }

    public void setClothes(String clothes) {
        this.clothes = clothes;
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getBomNum() {
        return bomNum;
    }

    public void setBomNum(int bomNum) {
        this.bomNum = bomNum;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
