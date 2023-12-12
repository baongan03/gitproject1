package Models;

import Utils.DateUtils;

import java.time.LocalDate;

public class User implements IParseModel {
    private long idUser;
    private String name;
    private String password;
    private LocalDate dob;
    private String email;
    private String address;
    private String phoneNumber;
    private EGender gender;
    private ERole role;

    public User() {
    }

    public User(long idUser, String name, String password, LocalDate dob, String email, String address, String phoneNumber, EGender gender, ERole role) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", idUser, name, password, DateUtils.formatDate(dob), email, address, phoneNumber, gender, role);
    }

    @Override
    public User parse(String line) {
        String[] str = line.split(",");
        User user = new User(Long.parseLong(str[0]),
                str[1],
                str[2],
                DateUtils.parseDate(str[3]),
                str[4],
                str[5],
                str[6],
                EGender.valueOf(str[7]),
                ERole.valueOf(str[8]));
        return user;
    }
}
