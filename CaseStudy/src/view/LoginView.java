package view;

import model.ERole;

import service.LoginService;

import utils.AppUtils;
import view.order.OrderView;
import view.order.OrderViewLauncher;
import view.product.MenuProductView;
import view.user.MenuUserView;

public class LoginView {
    private static LoginService loginService = new LoginService();
    public static void optionMenu() {
        System.out.println("                                ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                ║                                                     ║");
        System.out.println("                                ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                ║ Options:                                            ║");
        System.out.println("                                ║ ▶ 1.ProductView                                     ║");
        System.out.println("                                ║ ▶ 2.OrderView                                       ║");
        System.out.println("                                ║ ▶ Chọn chức năng                                    ║");
        System.out.println("                                ╚═════════════════════════════════════════════════════╝");

        int choiceView = AppUtils.getNumber("Nhap lua chon");
        if(choiceView == 1){
            MenuProductView.menuProduct();
            MenuProductView.runProduct();
            return;
        }else {
            OrderViewLauncher.menuOrder();
            return;
        }
    }
    public static void printMenu(){
        System.out.println("                                ╔═════════════════════════════════════════════════════╗");
        System.out.println("                                ║                       LOGIN                         ║");
        System.out.println("                                ╠═════════════════════════════════════════════════════╣");
        System.out.println("                                ║ Options:                                            ║");
        System.out.println("                                ║ ▶ 1.Đăng nhập                                       ║");
        System.out.println("                                ║ ▶ 2.Đăng kí                                         ║");
        System.out.println("                                ║ ▶ Chọn chức năng                                    ║");
        System.out.println("                                ╚═════════════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhap lua chon", 0, 2);
        if(choice == 1){
            String username = AppUtils.getString("Nhap username");
            String password = AppUtils.getString("Nhap password");
            ERole role = loginService.login(username, password);
            if(role == null){
                System.out.println("tai khoan hoac mat khau sai");
                printMenu();
            } else if (role == ERole.ADMIN) {
                optionMenu();
            }
            else if (role == ERole.USER) {
                MenuUserView.runOderUser();


//                while (true){
//                    System.out.println("                                ╔═════════════════════════════════════════════════════╗");
//                    System.out.println("                                ║                                                     ║");
//                    System.out.println("                                ╠═════════════════════════════════════════════════════╣");
//                    System.out.println("                                ║ Options:                                            ║");
//                    System.out.println("                                ║ ▶ 1.ProductView                                     ║");
//                    System.out.println("                                ║ ▶ 2.OrderView                                       ║");
//                    System.out.println("                                ║ ▶ Chọn chức năng                                    ║");
//                    System.out.println("                                ╚═════════════════════════════════════════════════════╝");
//
//                    int choiceView = AppUtils.getNumber("Nhap lua chon");
//                    if(choiceView == 1){
//                        MenuProductView.runProduct();
//                        return;
//                    }else {
//                        OrderViewLauncher.menuOrder();
//                        return;
//                    }



            }
            //nếu  Role user => tạo đơn hàng
            // nếu Role Admin
        }else{
            String username = AppUtils.getString("Nhap username");
            String password = AppUtils.getString("Nhap password");
            loginService.register(username, password);
            printMenu();
        }
    }
}
