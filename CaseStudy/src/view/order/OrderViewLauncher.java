package view.order;

import view.AdminView;
import view.LoginView;
import view.MainLauncher;
import view.EFunction;
import view.user.MenuUserView;

import java.util.Scanner;


public class OrderViewLauncher {
    static Scanner scanner = new Scanner(System.in);
    static OrderView orderView = new OrderView();

    public static void runOrder() {
        int choice;
        do {
            menuOrder();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        orderView.addOrder(AdminView.idOnlineUser);
                        break;

                    case 2:
                        orderView.showOrder(EFunction.SHOW);
                        break;
                    case 3:
                        SearchOrderView searchOrderView = new SearchOrderView();
                        searchOrderView.search(AdminView.idOnlineUser);
                        break;

                    case 4:
                        MainLauncher.menuOption();
                        break;
                    case 5:
                        LoginView.printMenu();
                        break;
                    default:
                        System.err.println("Nhập sai! Vui lòng nhập lại!");
                }
            } catch (Exception e) {
            }
        } while (true);
    }

    public static void menuOrder() {
        System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                            ║               QUẢN LÝ ĐƠN HÀNG                      ║");
        System.out.println("                                            ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                            ║ Options:                                            ║");
        System.out.println("                                            ║ ▶ 1.Tạo đơn hàng                                    ║");
        System.out.println("                                            ║ ▶ 2.Xem đơn hàng                                    ║");
        System.out.println("                                            ║ ▶ 3.Tìm kiếm đơn hàng                               ║");
        System.out.println("                                            ║ ▶ 4.Quay lại MAIN MENU                              ║");
        System.out.println("                                            ║ ▶ 5.Thoát                                           ║");
        System.out.println("                                            ║ ▶ Chọn chức năng                                    ║");
        System.out.println("                                            ╚═════════════════════════════════════════════════════╝");

    }
}

