package View;

import Models.Show;
import Services.ShowService;
import Utils.AppUtils;
import Utils.DateUtils;
import Utils.ValidateUtils;
import Models.ELocation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import static View.AdminView.adminMenu;


public class ShowView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ShowService iShowService = new ShowService();
    static void showMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║               MENU QUẢN LÝ SHOW            ║");
        System.out.println("            ║      1. Hiển thị tất cả các show           ║");
        System.out.println("            ║      2. Thêm show                          ║");
        System.out.println("            ║      3. Xóa show                           ║");
        System.out.println("            ║      0. Quay lại                           ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0, 3);

        switch (choice) {
            case 1: {
                getAllMusicShows();
                showMenu();
                break;
            }
            case 2: {
                addShow();
                showMenu();
                break;
            }
            case 3: {
                deleteShow();
                break;
            }
            case 0: {
                adminMenu();
                break;
            }
        }
    }

    public static void getAllMusicShows() {
        List<Show> showList = iShowService.getAll();
        System.out.printf("%10s | %25s | %20s | %20s | %20s | %12s | %16s\n", "ID SHOW", "SHOW NAME", "SINGER",
                "TIME START", "TIME END", "LOCATION", "SHOW PRICE(VND)");
        for (Show s : showList){
            System.out.printf("%10s | %25s | %20s | %20s | %20s | %12s | %16s\n", s.getIdShow(), s.getShowName(),
                    s.getSinger(), DateUtils.formatDateTime(s.getTimeStart()), DateUtils.formatDateTime(s.getTimeEnd()), s.getLocation(), s.getShowPrice());
        }
    }

    public static void addShow() {
        String showName = inputShowName();
        String singer = inputSinger();
        LocalDateTime timeStart = OrderView.inputDayTime("thời gian bắt đầu");
        LocalDateTime timeEnd = OrderView.inputDayTime("thời gian kết thúc");
        ELocation location = inputLocation();
        long showPrice = Long.parseLong(inputShowPrice());

        Show show = new Show(iShowService.nextId(), showName, singer, timeStart, timeEnd, location, showPrice);
        iShowService.create(show);
        showMenu();
    }


    public static void editShow(){

    }

    public static void deleteShow(){
        System.out.print("Nhập id muốn xóa: ");
        long idShow = Long.parseLong(scanner.nextLine());
        iShowService.delete(idShow);
        showMenu();
    }

    private static String inputShowName(){
        boolean validateName = true;
        String showName;
        do {
            System.out.print("Nhập tên show: ");
            showName = scanner.nextLine();
            if (ValidateUtils.isValidShowName(showName)){
                validateName = false;
            } else {
                System.out.println(" 'Tên Show' phải bắt đầu bằng ký tự hoặc số theo thứ tự bảng chữ cái, bao gồm ký tự 2-30");
            }
        } while (validateName);
        return showName;
    }
    private static String inputSinger(){
        boolean validateSinger = true;
        String singer;
        do {
            System.out.print("Nhập tên ca sỹ: ");
            singer = scanner.nextLine();
            if (ValidateUtils.isValidSinger(singer)){
                validateSinger = false;
            } else {
                System.out.println("'Tên ca sỹ' phải bắt đầu bằng ký tự hoặc số theo thứ tự bảng chữ cái, bao gồm ký tự 2-30");
            }
        } while (validateSinger);
        return singer;
    }
    private static ELocation inputLocation(){
        System.out.print("Chọn chỗ ngồi: ");
        for (ELocation e : ELocation.values()) {
            System.out.print(e.getId() + ". " + e +"\t");
        }
        ELocation location;
        do {
            long idLocation = Long.parseLong(scanner.nextLine());
            location = ELocation.findLocationById(idLocation);
        }
        while (location == null);
        return location;
    }

    private static String inputShowPrice(){
        boolean validateShowPrice = true;
        String showPrice;
        do {
            System.out.print("Nhập giá : ");
            showPrice = scanner.nextLine();
            if (ValidateUtils.isValidShowPrice(showPrice)){
                validateShowPrice = false;
            } else {
                System.out.println("'Giá show' là một số");
            }
        } while (validateShowPrice);
        return showPrice;
    }

}
