package view;
import view.order.OrderViewLauncher;
import view.product.MenuProductView;
import view.user.MenuUserView;

import java.util.Scanner;
public class MainLauncher {






    static Scanner scanner = new Scanner(System.in);

    public MainLauncher() {
        launch();
    }
    public static void launch() {
        LoginView.printMenu();
    }


    public static void menuOption() {
        do {
            mainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        MenuProductView.runProduct();
                        break;
                    case 2:
                        OrderViewLauncher.runOrder();
                        break;
                    case 3:
                        LoginView.printMenu();
                        break;
                    default:
                        System.err.println("Nhập sai! Vui lòng nhập lại! ");
                        break;
                }
            }catch (Exception e) {
                System.err.println("Error!");
            }
        }while (true);
    }

    public static void mainMenu() {
        System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                            ║                     MENU QUẢN LÝ                    ║");
        System.out.println("                                            ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                            ║ Options:                                            ║");
        System.out.println("                                            ║ ▶ 1.Quản lý sản phẩm                                ║");
        System.out.println("                                            ║ ▶ 2.Quản lý đơn đặt hàng                            ║");
        System.out.println("                                            ║ ▶ 3.Đăng xuất                                       ║");
        System.out.println("                                            ║ ▶ Chon chức năng                                    ║");
        System.out.println("                                            ╚═════════════════════════════════════════════════════╝");


    }
}


