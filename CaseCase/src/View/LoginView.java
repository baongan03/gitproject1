package View;
import Models.ERole;
import Models.User;
import Services.*;
import Utils.AppUtils;
import java.util.Scanner;



public class LoginView {
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUserLogin;

    public static int mainMenu() {
        System.out.println("             ╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("             ║                          CHÀO MỪNG ĐẾN VỚI CHƯƠNG TRÌNH ÂM NHẠC                    ║");
        System.out.println("             ╠════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("             ║                                                                                    ║");
        System.out.println("             ║                                      1. Đăng nhập                                  ║");
        System.out.println("             ║                                      2. Đăng ký                                    ║");
        System.out.println("             ║                                                                                    ║");
        System.out.println("             ╚════════════════════════════════════════════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0, 2);
        if (choice == 1) {
            String accountName = AppUtils.getString("Nhập username");
            String password = AppUtils.getString("Nhập mật khẩu");

            UserService userService = new UserService();
            UserService.listUsers = userService.getAll();

            User user = UserService.getByAccountName(accountName);

            if (user != null && user.getPassword() != null && user.getRole().equals(ERole.ADMIN)) {
                currentUserLogin = UserService.getByAccountName(accountName);
                AdminView.adminMenu();
            } else if (user != null && user.getPassword().equals(password) && user.getRole().equals(ERole.USER)) {
                currentUserLogin = UserService.getByAccountName(accountName);
                UserView.userMenu(currentUserLogin.getIdUser());
                ;
            } else {
                System.err.println("Tài khoản không hợp lệ!!");
                System.err.println("Vui lòng nhập lại tài khoản và mật khẩu hợp lệ!!");
                LoginService.login();
            }
        } else {
            UserView.addUser();
            mainMenu();
        }
        return choice;

    }
}


