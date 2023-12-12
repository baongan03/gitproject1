package Services;

import Models.EGender;
import Models.ERole;
import Models.User;

import java.io.*;
import java.time.LocalDate;
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
    public static ERole login(String username, String password) {
       currentUserLogin = users.stream().filter(e -> Objects.equals(e.getName(), username)&& Objects.equals(e.getPassword(), password)).findFirst().orElse(new User());
       return currentUserLogin.getRole();
    }

    public static void register(String name, String password,LocalDate dob, String address,String phoneNumber,EGender gender) {
//        public static void register( String name, String password)  {
        if (users.stream().anyMatch(e -> Objects.equals(e.getName(), name))) {
            return;
        }
        User user = new User();
        user.setIdUser(++currentId);
        user.setName(name);
        user.setPassword(password);
        user.setDob(dob);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setRole(ERole.USER);
        users.add(user);
        writeFile();
    }
    private static void writeFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/Users.txt"));
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
            File fileWriter = new File("data/Users.txt");
            FileReader fileReader = new FileReader(fileWriter);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null && !line.equals("")){
                String[] data = line.split(",");
                currentId = Integer.parseInt(data[0]);
                User user = new User();
                user.setIdUser(currentId);
                user.setName(data[1]);
                user.setPassword(data[2]);
                user.setDob(LocalDate.parse(data[3]));
                user.setAddress(data[4]);
                user.setPhoneNumber(data[5]);
                user.setGender(EGender.valueOf(data[6]));
                user.setRole(ERole.valueOf(data[7]));

                line = reader.readLine();
                users.add(user);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void login() {
       login();
    }
}
