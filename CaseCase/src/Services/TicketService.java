package Services;

import Models.Show;
import Models.Ticket;
import Utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService implements IModelService<Ticket> {
    private static final String fileTicket = "./data/Tickets.txt";

    @Override
    public void create(Ticket ticket) {
        List<Ticket> ticketList = getAll();
        ticketList.add(ticket);
        FileUtils.writeData(fileTicket, ticketList);
        System.out.println("Tạo vé thành công!");
    }



    @Override
    public List<Ticket> getAll() {
        return FileUtils.readData(fileTicket, Ticket.class);
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long nextId() {
        long maxIdTicket = 400000;
        List<Ticket> ticketList = getAll();
        for (Ticket t : ticketList){
            if (t.getIdTicket() > maxIdTicket){
                maxIdTicket = t.getIdTicket();
            }
        }
        return maxIdTicket + 1;
    }

    @Override
    public Ticket findById(long id) {
        List<Ticket> ticketList = getAll();
        for (Ticket t : ticketList){
            if (t.getIdTicket() == id){
                return t;
            }
        }
        return null;
    }
    public List<Ticket> findByIdOrder(long idOrder){
        List<Ticket> ticketList = getAll();
        return ticketList.stream().filter(ticket -> ticket.getIdOrder() == idOrder).collect(Collectors.toList());
    }
    @Override
    public void create(Show show) {

    }
}