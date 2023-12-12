package Models;

public class Ticket implements IParseModel<Ticket> {
    private long idTicket;
    private long idOrder;
    private long idShow;
    private long idSeat;
    private long ticketPrice;

    public Ticket() {
    }

    public Ticket(long idTicket, long idOrder, long idShow, long idSeat, long ticketPrice) {
        this.idTicket = idTicket;
        this.idOrder = idOrder;
        this.idShow = idShow;
        this.idSeat = idSeat;
        this.ticketPrice = ticketPrice;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public long getIdShow() {
        return idShow;
    }

    public void setIdShow(long idShow) {
        this.idShow = idShow;
    }

    public long getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(long idSeat) {
        this.idSeat = idSeat;
    }

    public long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", idTicket, idOrder, idShow, idSeat, ticketPrice);
    }

    @Override
    public Ticket parse(String line) {
        String[] str = line.split(",");
        Ticket t = new Ticket(Long.parseLong(str[0]), Long.parseLong(str[1]), Long.parseLong(str[2]), Long.parseLong(str[3]), Long.parseLong(str[4]));
        return t;
    }
}
