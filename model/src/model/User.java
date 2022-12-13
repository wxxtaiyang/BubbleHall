package model;


public class User {

    private int id;
    private String username;
    private String nickname;
    private String password;
    private String time;

    public User(int id, String username, String nickname, String password, String time) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.time = time;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return username;
    }
}
