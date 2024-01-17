package com.ecommerce.service;

import com.ecommerce.dao.AddressDao;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.dao.OrderItemDao;
import com.ecommerce.dao.UserDao;
import com.ecommerce.exception.OrderException;
import com.ecommerce.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemDao orderItemDao;


    @Override
    public Order createOrder(User user, Address shippAddress) {

        shippAddress.setUser(user);
        Address address= addressDao.save(shippAddress);
        user.getAddresses().add(address);
        userDao.save(user);

        Cart cart=cartService.findUserCart(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();

        for(CartItem item: cart.getCartItems()) {
            OrderItem orderItem=new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());


            OrderItem createdOrderItem=orderItemDao.save(orderItem);

            orderItems.add(createdOrderItem);
        }


        Order createdOrder=new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItem(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        Order savedOrder=orderDao.save(createdOrder);

        for(OrderItem item:orderItems) {
            item.setOrder(savedOrder);
            orderItemDao.save(item);
        }

        return savedOrder;

    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");


        return orderDao.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderDao.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderDao.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderDao.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional<Order> opt=orderDao.findById(orderId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new OrderException("order not exist with id "+orderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderDao.getUsersOrders(userId);
    }

    @Override
    public List<Order> getAllOrders() {

        return orderDao.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order =findOrderById(orderId);

        orderDao.deleteById(orderId);

    }

}