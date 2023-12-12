package Utils;
import java.util.Scanner;

public class AppUtils {
    static Scanner sc = new Scanner(System.in);


    public static int getNumberMinMax(String str, int min, int max) throws IndexOutOfBoundsException{
        System.out.println(str);
        int num;
        try {
            num = Integer.parseInt(sc.nextLine());
            if (num < min || num > max) {
                System.out.println("Chọn trong khoáng" + min + " và " + max);
                return getNumberMinMax(str, min, max);
            }
            return num;
        } catch (Exception e) {
            System.out.println("Không đúng định dạng");
        }
        return getNumberMinMax(str, min, max) ;

    }
    public static String getString(String str) throws IndexOutOfBoundsException {
        System.out.println(str);
        return sc.nextLine();
    }
    public static void exit() {
        System.out.println("\t Cảm ơn quý khách. Hẹn gặp lại !");
        System.exit(0);
    }

}
