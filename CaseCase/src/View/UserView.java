package View;

import Models.EGender;
import Models.ERole;
import Models.*;
import Services.*;
import Utils.AppUtils;
import Utils.DateUtils;
//import Utils.PasswordUtils;
import Utils.ValidateUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static View.AdminView.adminMenu;
import static View.LoginView.mainMenu;
import static View.OrderView.*;
import static View.ShowView.getAllMusicShows;

public class UserView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();

    public static void userMenu(long idUser) {
        System.out.println("                ╔════════════════════════════════════════════╗");
        System.out.println("                ║                   USER MENU                ║");
        System.out.println("                ║      1. Xem tất cả các Shows               ║");
        System.out.println("                ║      2. Đặt vé                             ║");
        System.out.println("                ║      3. Xem vé của user                    ║");
        System.out.println("                ║      4. Xem order của user                 ║");
        System.out.println("                ║      0. Quay lại Main Menu                 ║");
        System.out.println("                ╚════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0,4);

        switch (choice) {
            case 1: {
                getAllMusicShows();
                userMenu(idUser);
                break;
            }
            case 2: {
                bookTicket(idUser);
                break;
            }
            case 3: {
                yourTicket(idUser);
                break;
            }
            case 4: {
                yourOrder(idUser);
                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }

    public static void userManagementMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║               MENU QUẢN LÝ USER            ║");
        System.out.println("            ║      1. Hiển thị tất cả user               ║");
        System.out.println("            ║      2. Thêm user                          ║");
        System.out.println("            ║      3. Sửa user                           ║");
        System.out.println("            ║      4. Tìm kiếm user theo ID              ║");
        System.out.println("            ║      5. Xóa user                           ║");
        System.out.println("            ║      0. Quay lại                           ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0,5);

        switch (choice) {
            case 1: {
                showAllUser();
                break;
            }
            case 2: {
                addUser();
                userManagementMenu();
                break;
            }
            case 3: {
                editUser();
                break;
            }
            case 4: {
                findUserById();
                break;
            }
            case 5: {
                deleteUser();
                break;
            }
            case 0: {
                adminMenu();
                break;
            }
        }
    }


    private static void showAllUser() {
        List<User> userList = userService.getAll();
        System.out.printf("%10s | %20s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID USER", "NAME"
                , "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        for (User u : userList) {
            System.out.printf("%10s | %20s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(),
                    u.getName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
        }
        userManagementMenu();
    }

    public static void addUser() {
        String name = inputName();
        String password = inputPassword();
        LocalDate dob = inputDOB();
        String email = inputEmail();
        String address = inputAddress();
        String phoneNumber = inputPhoneNumber();
        EGender gender = inputGender();

        User user = new User(userService.nextId(), name, password, dob, email, address, phoneNumber, gender, ERole.USER);
        userService.create(user);
    }

    private static void editUser() {
        System.out.print("Nhập id muốn sửa: ");
        long idUser = Long.parseLong(scanner.nextLine());
        User editUser = userService.findById(idUser);
        showUser(editUser);

        editUser.setName(inputName());
        editUser.setPassword(inputPassword());
        editUser.setDob(inputDOB());
        editUser.setEmail(inputEmail());
        editUser.setAddress(inputAddress());
        editUser.setPhoneNumber(inputPhoneNumber());
        editUser.setGender(inputGender());

        userService.update(editUser);
        userManagementMenu();
    }

    private static void findUserById() {
        showAllUser();
        System.out.print("Nhập id muốn tìm: ");
        long idUser = Long.parseLong(scanner.nextLine());
        showUser(userService.findById(idUser));
        userManagementMenu();
    }

    private static void deleteUser() {
        showAllUser();
        System.out.print("Nhập id muốn xóa: ");
        long idUser = Long.parseLong(scanner.nextLine());
        userService.delete(idUser);
        userManagementMenu();
    }

    private static void showUser(User u) {
        System.out.printf("%10s | %20s | %15s | %30s | %20s | %15s | %10s | %10s\n", "ID", "NAME",
                "DATE OF BIRTH", "EMAIL", "ADDRESS", "PHONE NUMBER", "GENDER", "ROLE");
        System.out.printf("%10s | %20s | %15s | %30s | %20s | %15s | %10s | %10s\n", u.getIdUser(), u.getName(), DateUtils.formatDate(u.getDob()), u.getEmail(), u.getAddress(), u.getPhoneNumber(), u.getGender(), u.getRole());
    }

    private static String inputName() {
        boolean validateName = true;
        String name;
        do {
            System.out.print("Nhập tên: ");
            name = scanner.nextLine();
            if (ValidateUtils.isValidName(name)) {
                validateName = false;
            } else {
                System.out.println("'Tên' phải bắt đầu bằng chữ in hoa, bao gồm 2-20 ký tự chữ cái");
            }
        } while (validateName);
        return name;
    }


    private static String inputPassword() {
        String password;
        boolean validatePassword = false;
        do {
            System.out.print("Nhập password: ");
            password = scanner.nextLine();
            if (ValidateUtils.isValidPassword(password)) {
                validatePassword = false;
            } else {
                validatePassword = true;
                System.out.println("'Password' chứa ít nhất một ký tự chữ cái, một chữ số hoặc một ký tự đặc biệt từ '@$!%*?&', bao gồm 6-20 ký tự!");
            }
        } while (validatePassword);
        return password;
    }

    private static LocalDate inputDOB() {
        LocalDate dob;
        do {
            System.out.print("Nhập ngày sinh của bạn: ");
            dob = DateUtils.parseDate(scanner.nextLine());
        }
        while (dob == null);
        return dob;
    }

    private static String inputEmail() {
        String email;
        boolean validateEmail = false;
        do {
            System.out.print("Nhập email của bạn: ");
            email = scanner.nextLine();
            if (ValidateUtils.isValidEmail(email)) {
                validateEmail = false;
            } else {
                validateEmail = true;
                System.out.println("'Email' chứa 5-32 ký tự chữ cái, chữ số hoặc dấu gạch dưới + @ + ít nhất hai ký tự chữ cái hoặc chữ số + '.' + 2-4 ký tự chữ cái hoặc chữ số");
            }
        } while (validateEmail);
        return email;
    }

    private static String inputAddress() {
        String address;
        boolean validateAddress = false;
        do {
            System.out.print("Nhập địa chỉ của bạn: ");
            address = scanner.nextLine();
            if (ValidateUtils.isValidAddress(address)) {
                validateAddress = false;
            } else {
                validateAddress = true;
                System.out.println("'Địa chỉ' chứa ít nhất 2 ký tự chữ cái hoặc chữ số");
            }
        } while (validateAddress);
        return address;
    }

    private static String inputPhoneNumber() {
        String phoneNumber;
        boolean validatePhoneNumber = false;
        do {
            System.out.print("Nhập số điện thoại của bạn: ");
            phoneNumber = scanner.nextLine();
            if (ValidateUtils.isValidPhoneNumber(phoneNumber)) {
                validatePhoneNumber = false;
            } else {
                validatePhoneNumber = true;
                System.out.println("'Số điện thoại' chứa 10 chữ số và bắt đầu bằng số 0");
            }
        } while (validatePhoneNumber);
        return phoneNumber;
    }

    private static EGender inputGender() {
        System.out.print("Nhập giới tính của bạn: ");
        for (EGender e : EGender.values()) {
            System.out.print(e.getId() + ". " + e.getName() + "\t");
        }
        EGender gender;
        do {
            long idGender = Long.parseLong(scanner.nextLine());
            gender = EGender.finGenderById(idGender);
        }
        while (gender == null);
        return gender;
    }


    public static void main(String[] args) {
        UserView userView = new UserView();
    }


}
