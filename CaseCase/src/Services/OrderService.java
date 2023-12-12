package Services;

import Models.Order;
import Models.Show;
import Utils.FileUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements IModelService<Order> {
    private final String fileOrder = "./data/Orders.txt";

    @Override
    public void create(Order order) {
        List<Order> orderList = getAll();
        orderList.add(order);
        FileUtils.writeData(fileOrder, orderList);
        System.out.println("Tạo đơn hàng thành công!");
    }

    @Override
    public void create(Show show) {

    }

    @Override
    public List<Order> getAll() {
        return FileUtils.readData(fileOrder, Order.class);
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long nextId() {
        long maxIdOrder = 300000;
        List<Order> orderList = getAll();
        for (Order o : orderList) {
            if (o.getIdOrder() > maxIdOrder) {
                maxIdOrder = o.getIdOrder();
            }
        }
        return maxIdOrder + 1;
    }

    public List<Order> findOrderByIdUser(long idUser) {
        List<Order> orderList = getAll();
        List<Order> list = orderList.stream().filter(order -> order.getIdUser() == idUser).collect(Collectors.toList());
        return list;
    }

    @Override
    public Order findById(long id) {
        List<Order> orderList = getAll();
        for (Order o : orderList) {
            if (o.getIdOrder() == id) {
                return o;
            }
        }
        return null;
    }

    public List<Order> getOrdersByInputTime(LocalDateTime dayStart, LocalDateTime dayEnd){
        List<Order> orderList = getAll();
        return orderList.stream().filter(order -> order.getTimeCreate().isAfter(dayStart)
                && order.getTimeCreate().isBefore(dayEnd)).collect(Collectors.toList());
    }
    public long getRevenue(List<Order> orderListByInputTime){
        long revenue = 0;
        List<Order> orderList = getAll();
        for (Order o : orderListByInputTime){
            revenue += o.getTotalPrice();
        }
        return revenue;
    }
}