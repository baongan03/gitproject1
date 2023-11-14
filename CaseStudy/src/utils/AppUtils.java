package utils;

import view.EFunction;
import view.product.MenuProductView;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.System.exit;

public class AppUtils {
    static Scanner sc = new Scanner(System.in);
    public static int getNumber(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            return num;
        } catch (Exception e) {
            System.out.println("Khong dung dinh dang");
            return getNumber(str);
        }

    }
    public static int getNumberMinMax(String str,int min, int max) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            if(num<min || num>max) {
                System.out.println("Chon trong khoang " + min + " va " + max);
                return getNumberMinMax(str,min,max);
            }
            return num;
        } catch (Exception e) {
            System.out.println("Khong dung dinh dang");
            return getNumberMinMax(str, min, max);
        }

    }


    public static int getNumber(String str, int min, int max) throws IndexOutOfBoundsException {
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            //min = 1 max = 7
            if(num < min || num > max) {
                System.err.println("Chọn từ khoảng " + min + " và " + max);
                return getNumber(str,min,max);
            }
            return num;
        } catch (Exception e) {
            System.err.println("Khong dung dinh dang");
            return getNumber(str,min,max);
        }

    }
    public static Date getDate(String str){
        System.out.println(str);
        System.out.println("Format yyyy-mm-dd, Example: 2023-03-30");
        try{
            return Date.valueOf(sc.nextLine());
        }catch (Exception e){
            return getDate(str);
        }

    }


    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }
    public static int retryParseInt() {
        int result;
        do {
            try {
                result = Integer.parseInt(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static int retryChoose(int min, int max) {
        int option;
        do {
            try {
                option = Integer.parseInt(sc.nextLine());
                if (option > max || option < min) {
                    System.err.println("Chọn chức năng không đúng! Vui lòng nhập lại.!");
                    continue;
                }
                break;
            } catch (Exception ex) {
                System.err.println("Nhập sai! Vui lòng nhập lại!!! ");
            }
        } while (true);
        return option;
    }

    public static double retryParseDouble() {
        double result;
        do {
            try {
                result = Double.parseDouble(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.err.println("Nhập sai! Vui lòng nhập lại!!!");
            }
        } while (true);
    }

    public static String retryString(String fieldName) {
        String result;
        while ((result = sc.nextLine()).isEmpty()) {
            System.out.printf("%s không được để trống\n", fieldName);
        }
        return result;
    }

    public static boolean isRetry(EFunction choose) {
        do {
            switch (choose) {
                case ADD:
                    System.out.println("Nhấn 'y' để thêm tiếp || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    break;
                case UPDATE:
                    System.out.println("Nhấn 'y' để sửa tiếp || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    break;
                case REMOVE:
//                    System.out.println("Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình");
                    MenuProductView.menuProduct();
                    break;
                case SHOW:
                    System.out.println("Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình!");
                    break;
                case PRINT:
                    System.out.println("Nhấn 'i' để in hoá đơn || Nhấn 'q' để quay lại || Nhấn 't' để thoát chương trình! ");
                default:
                    throw new IllegalStateException("Unexpected value " + choose);
            }
//            String option = sc.nextLine().toLowerCase();
//            switch (option) {
//                case "y":
//                    return true;
//                case "q":
//                    return false;
//                case "t":
//                    exit();
//                    break;
//                default:
//                    System.err.println("Chọn chức năng không đúng! Vui lòng nhập lại.");
//            }
        } while (true);
    }

    public static void exit() {
        System.out.println("\t Cảm ơn quý khách. Hẹn gặp lại !");
        System.exit(0);
    }

    public static long retryParseLong() {
        long result;
        do {
            try {
                result = Long.parseLong(sc.nextLine());
                return result;
            } catch (Exception ex) {
                System.out.println("Nhập sai! vui lòng nhập lại");
            }
        } while (true);
    }


    public static String doubleToVND(double value) {
        String patternVND = ",### VNĐ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

    public static void pressToContinue() {
        System.out.println("Ấn nút bất kỳ để tiếp tục. ");
        sc.nextLine();
    }

    public static void menuDelete() {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║               BẠN CÓ MUỐN XÓA KHÔNG                 ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║ Options:                                            ║");
        System.out.println("║ ▶ 1.Có                                              ║");
        System.out.println("║ ▶ 2.Không                                           ║");
        System.out.println("║ ▶ Nhập lựa chọn                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");

    }
}
