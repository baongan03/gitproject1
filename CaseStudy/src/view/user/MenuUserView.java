package view.user;

import view.AdminView;
import view.EFunction;
import view.LoginView;
import view.order.OrderView;
import view.order.SearchOrderView;
import view.product.ProductView;

import java.util.Scanner;


public class MenuUserView {

    private static Scanner sc = new Scanner(System.in);
    static ProductView productView = new ProductView();

    public static void menuOderUser() {
        System.out.println("                                            ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                            ║                       MAIN MENU                     ║");
        System.out.println("                                            ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                            ║ Options:                                            ║");
        System.out.println("                                            ║ ▶ 1.Tạo đơn hàng                                    ║");
        System.out.println("                                            ║ ▶ 2.Xem đơn hàng                                    ║");
        System.out.println("                                            ║ ▶ 0.Đăng xuất                                       ║");
        System.out.println("                                            ║ ▶ Chọn chức năng                                    ║");
        System.out.println("                                            ╚═════════════════════════════════════════════════════╝");

    }

    public static void runOderUser() {
        do {
            menuOderUser();
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    OrderView orderView = new OrderView();
                    orderView.addOrder(AdminView.idOnlineUser);
                    break;
                case 2:
                    OrderView orderView2 = new OrderView();
                    orderView2.showOrdersOfEmployee(AdminView.idOnlineUser , EFunction.SHOW);
                    break;
                case 0:
                    LoginView.printMenu();
                default:
                    System.err.println("Nhập chức năng sai! Vui lòng nhập lại !!!");
            }
        }while (true);
    }
}
