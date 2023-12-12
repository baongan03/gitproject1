package View;

import Services.*;
import Utils.AppUtils;

import java.util.Scanner;
import static View.LoginView.mainMenu;
import static View.OrderView.orderMenu;
import static View.ShowView.*;
import static View.UserView.*;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService iUserService = new UserService();

    public static void adminMenu(){
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║                  ADMIN MENU                ║");
        System.out.println("            ║      1. Quản lý users                      ║");
        System.out.println("            ║      2. Quản lý show âm nhạc               ║");
        System.out.println("            ║      3. Quản lý orders                     ║");
        System.out.println("            ║      0. Quay lại                           ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0, 3);

        switch (choice) {
            case 1: {
                userManagementMenu();
                break;
            }
            case 2: {
                showMenu();
                break;
            }
            case 3: {
                orderMenu();
                break;
            }
            case 0: {
                mainMenu();
                break;
            }
        }
    }
}
