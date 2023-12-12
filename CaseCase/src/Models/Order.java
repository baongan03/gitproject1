package Models;
import Utils.DateUtils;

import java.time.LocalDateTime;

public class Order implements  IParseModel{
    private long idOrder;
    private long idUser;
    private LocalDateTime timeCreate;
    private long totalPrice;


    public Order(long idOrder, long idUser, LocalDateTime timeCreat, long totalPrice) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.timeCreate = timeCreat;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", idOrder, idUser, DateUtils.formatDateTime(timeCreate), totalPrice);
    }


    @Override
    public Object parse(String line) {
        String[] str = line.split(",");
        Order o = new Order(Long.parseLong(str[0]),Long.parseLong(str[1]), DateUtils.parseDateTime(str[2]), Long.parseLong(str[3]));
        return o;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
