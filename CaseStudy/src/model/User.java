package model;

import java.time.Instant;

public class User {
    private long idUser;
    private String userName;
    private String passWord;
    private ERole role;

    public User() {

    }

    public User(long idUser, String userName, String passWord, String fullName, String phone, String email, String address, ERole role, Instant createAt, Instant updateAt) {
        this.idUser = idUser;
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    public User(long idUser, String username, String password, String fullName, String phone, String email, String address, ERole role) {
        this.idUser = idUser;
        this.userName = username;
        this.passWord = password;
        this.role = role;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", idUser, userName, passWord,  role);
    }

    public static User parseUser(String line) {
        User user = new User();
        String[] item = line.split(",");
        user.idUser = Long.parseLong(item[0]);
        user.userName = item[1];
        user.passWord = item[2];
        user.role = ERole.parseRole(item[3]);
        return user;
    }
}