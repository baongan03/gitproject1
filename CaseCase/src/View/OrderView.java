package View;

import Models.*;
import Services.*;
import Utils.AppUtils;
import Utils.DateUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import Enum.EStatus;
import static Services.SeatService.showSeatList;
//import static View.AdminView.adminMenu;
//import static View.ShowView.getAllMusicShows;
import static View.ShowView.getAllMusicShows;
import static View.UserView.*;

public class OrderView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final OrderService orderService = new OrderService();
    private static final TicketService STICKET_SERVICE = new TicketService();
    private static final ShowService showService = new ShowService();
    private static final SeatService seatService = new SeatService();

    static void orderMenu() {
        System.out.println("            ╔════════════════════════════════════════════╗");
        System.out.println("            ║              MENU QUẢN LÝ ORDER            ║");
        System.out.println("            ║      1. Hiển thị tất cả order              ║");
        System.out.println("            ║      0. Quay lại                           ║");
        System.out.println("            ╚════════════════════════════════════════════╝");

        int choice = AppUtils.getNumberMinMax("Nhập lựa chọn", 0, 2);
        switch (choice) {
            case 1: {
                showAllOrders();
                break;
            }
            case 2: {
                showRevenue();
                break;
            }
            case 0: {
                AdminView.adminMenu();
                break;
            }
        }
    }


    private static void showRevenue() {
        LocalDateTime dayStart = inputDayTime("day start");
        LocalDateTime dayEnd = inputDayTime("day end");
        List<Order> orderListByInputTime = orderService.getOrdersByInputTime(dayStart, dayEnd);
        long revenue = orderService.getRevenue(orderListByInputTime);
        showOrder(orderListByInputTime);
        System.out.printf("%46s | %15s\n", "REVENUE", revenue);
        orderMenu();
    }

    private static void showAllOrders() {
        List<Order> orderList = orderService.getAll();
        System.out.printf("%10s | %10s | %20s | %15s\n", "ID ORDER", "ID USER", "TIME CREATE", "TOTAL PRICE");
        for (Order o : orderList) {
            System.out.printf("%10s | %10s | %20s | %15s\n", o.getIdOrder(), o.getIdUser(),
                    DateUtils.formatDateTime(o.getTimeCreate()), o.getTotalPrice());
        }
        orderMenu();
    }
    private static void showOrder(List<Order> orderList) {
        System.out.printf("%10s | %10s | %20s | %15s\n", "ID ORDER", "ID USER", "TIME CREATE", "TOTAL PRICE");
        for (Order o : orderList) {
            System.out.printf("%10s | %10s | %20s | %15s\n", o.getIdOrder(), o.getIdUser(),
                    DateUtils.formatDateTime(o.getTimeCreate()), o.getTotalPrice());
        }
    }
    public static void yourOrder(long idUser){
        List<Order> orderList = orderService.findOrderByIdUser(idUser);
        long totalOrder = orderService.getRevenue(orderList);
        showOrder(orderList);
        System.out.printf("%46s | %15s\n", "TOTAL ORDER", totalOrder);
        userMenu(idUser);
    }

    public static void yourTicket(long idUser) {
        System.out.printf("%10s | %15s | %16s | %15s | %16s | %16s | %10s | %15s | %15s\n", "ID ORDER", "USER NAME",
                "SHOW NAME", "SINGER", "TIME START", "TIME END", "LOCATION", "SEAT POSITION", "TICKET PRICE");
        List<Order> orderList = orderService.findOrderByIdUser(idUser);
        User user = userService.findById(idUser);
        for (Order order : orderList) {
            List<Ticket> ticketList = STICKET_SERVICE.findByIdOrder(order.getIdOrder());
            for (Ticket t : ticketList) {
                long idShow = t.getIdShow();
                Show show = showService.findById(idShow);
                long idTicket = t.getIdTicket();
                Ticket ticket = STICKET_SERVICE.findById(idTicket);
                long idSeat = t.getIdSeat();
                Seat seat = seatService.findSeatById(idSeat);
                System.out.printf("%10s | %15s | %16s | %15s | %16s | %16s | %10s | %15s | %15s\n", order.getIdOrder(), user.getName(),
                        show.getShowName(), show.getSinger(), DateUtils.formatDateTime(show.getTimeStart()),
                        DateUtils.formatDateTime(show.getTimeEnd()), show.getLocation(), seat.getSeatPosition(), ticket.getTicketPrice());
            }
        }
        userMenu(idUser);
    }

    public static void bookTicket(long idUser) {
        Order order = new Order();
        long idOrder = orderService.nextId();
        boolean checkContinueBooking = false;
        List<Ticket> ticketList = new ArrayList<>();
        do {
            inputTicket(idOrder, ticketList);

            System.out.println("Bạn có muốn đặt thêm vé nữa không? (Y/N)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "Y":
                case "y": {
                    checkContinueBooking = true;
                    break;
                }
                case "N":
                case "n": {
                    checkContinueBooking = false;
                    break;
                }
                default: {
                    System.out.println("Chọn Y hoặc N!");
                    checkContinueBooking = true;
                    break;
                }
            }
        } while (checkContinueBooking);


        long totalPrice = 0;
        for (Ticket t : ticketList) {
            totalPrice += t.getTicketPrice();
        }
        order.setIdOrder(idOrder);
        order.setIdUser(idUser);
        order.setTimeCreate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        orderService.create(order);
        userMenu(idUser);
    }

    public static void inputTicket(long idOrder, List<Ticket> ticketList) {
        getAllMusicShows();
        System.out.print("Nhập id show muốn book: ");
        long idShow = Long.parseLong(scanner.nextLine());
        Show show = showService.findById(idShow);
        List<Seat> seatList = seatService.showSeatListByLocation(show.getLocation());
        boolean isValidSeat = true;
        long idSeat;
        do {
            showSeatList(seatList);
            System.out.println("Chọn chỗ ngồi của bạn: ");
            idSeat = Long.parseLong(scanner.nextLine());
            for (Seat s : seatList) {
                if (s.getIdSeat() == idSeat && s.getStatus().equals(EStatus.AVAILABLE)) {
                    isValidSeat = false;
                    seatService.changeSeatStatus(s.getIdSeat());
                    break;
                }
            }
            if (isValidSeat) {
                System.out.println("Không có chỗ ngồi hợp lệ, vui lòng chọn lại!");
            }
        }
        while (isValidSeat);

        Ticket ticket = new Ticket(STICKET_SERVICE.nextId(), idOrder, idShow, idSeat, show.getShowPrice());
        STICKET_SERVICE.create(ticket);
        ticketList.add(ticket);
    }

    public static LocalDateTime inputDayTime(String message) {
        LocalDateTime day;
        do {
            System.out.print("Enter " + message + " 'dd-mm-yyyy hh:mm': ");
            day = DateUtils.parseDateTime(scanner.nextLine());
        }
        while (day == null);
        return day;
    }
}
