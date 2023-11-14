package service;

import model.ERole;

import model.User;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginService {
    private static List<User> users = new ArrayList<>();
    private static int currentId = 0;

    private static User currentUserLogin = new User();
    static {
        readData();
    }

    public static User currentUser(){
        return currentUserLogin;
    }

    public ERole login(String username, String password){
        currentUserLogin = users.stream().filter(e -> Objects.equals(e.getUserName(), username) &&
                Objects.equals(e.getPassWord(), password)
        ).findFirst().orElse(new User());
        return currentUserLogin.getRole();
    }

    public boolean register(String username, String password){
        if(users.stream().anyMatch(e -> Objects.equals(e.getUserName(), username))){
            return false;
        }
        User user = new User();
        user.setIdUser(++currentId);
        user.setUserName(username);
        user.setPassWord(password);
        user.setRole(ERole.USER);
        users.add(user);
        writeFile();
        return true;
    }

    private static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/user.txt"));
            for (var user :users) {
                writer.write(user.toString());
                writer.newLine();
            }


            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static void readData(){
        try{
            File fileWriter = new File("data/user.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                User user = new User();
                user.setIdUser(currentId);
                user.setUserName(data[1]);
                user.setPassWord(data[2]);
                user.setRole(ERole.valueOf(data[3]));

                line = reader.readLine();
                users.add(user);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}