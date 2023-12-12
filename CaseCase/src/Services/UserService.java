package Services;

import Models.Show;
import Models.User;
import Utils.FileUtils;
import java.util.List;
import java.util.Objects;

public class UserService implements IModelService<User> {
    String fileUser = "./data/Users.txt";
    private static UserService userService;

    public static List<User> listUsers;


    @Override
    public void create(User user) {
        List<User> userList = getAll();
        userList.add(user);
        FileUtils.writeData(fileUser, userList);
        System.out.println("Tạo người dùng thành công!");
    }



    @Override
    public List<User> getAll() {
        return FileUtils.readData(fileUser, User.class);
    }

    @Override
    public void update(User user) {
        List<User> userList = getAll();
        for (User u : userList){
            if (u.getIdUser() == user.getIdUser()){
                u.setName(user.getName());
                u.setPassword(user.getPassword());
                u.setDob(user.getDob());
                u.setEmail(user.getEmail());
                u.setAddress(user.getAddress());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setGender(user.getGender());
                u.setRole(user.getRole());
                break;
            }
        }
        FileUtils.writeData(fileUser, userList);
        System.out.println("Edit successful!");
    }

    @Override
    public void delete(long id) {
        List<User> userList = getAll();
        int count = 0;
        for (User u : userList){
            if (u.getIdUser() == id){
                userList.remove(u);
                FileUtils.writeData(fileUser, userList);
                System.out.println("Delete user successful!");
                count++;
                break;
            }
        }
        if (count == 0) System.out.println("Cannot find id user to delete!");
    }

    public User findById(long id){
        List<User> userList = getAll();
        for (User u : userList){
            if (u.getIdUser() == id){
                return u;
            }
        }
        return null;
    }

    public User findUserByEmail(String email){
        List<User> userList = getAll();
        return userList.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
    public User findUserByAccountName(String accountName){
        List<User> userList = getAll();
        return userList.stream().filter(user -> user.getName().equals(accountName)).findFirst().orElse(null);
    }
    public long nextId(){
        long maxIdUser = 10000;
        List<User> userList = getAll();
        for (User u : userList){
            if (u.getIdUser() > maxIdUser){
                maxIdUser = u.getIdUser();
            }
        }
        return maxIdUser + 1;
    }
    public static User getByAccountName(String accountName) {
        User u = listUsers.stream().filter(us -> Objects.equals(us.getName(), accountName)).findFirst().orElse(null);
        return listUsers.stream().filter(us -> Objects.equals(us.getName(), accountName)).findFirst().orElse(null);
//        return listUsers.stream().filter(us -> Objects.equals(us.getPhonenumber(), phoneNumber)).findFirst().orElse(null);
    }

    @Override
    public void create(Show show) {
    }
}
