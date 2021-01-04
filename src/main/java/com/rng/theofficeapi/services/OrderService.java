package com.rng.theofficeapi.services;

import com.rng.theofficeapi.dto.OrderDTO;
import com.rng.theofficeapi.dto.SalesmanDTO;
import com.rng.theofficeapi.entities.*;
import com.rng.theofficeapi.entities.enums.PaymentStatus;
import com.rng.theofficeapi.repositories.*;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BilletService billetService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private SalesmanService salesmanService;

    @Autowired
    private SalesmanRepository salesmanRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found, ID: " + id));
    }

    public void save(Order order) {
        order.setDate(new Date());
        order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
        order.getPayment().setOrder(order);

        if(order.getPayment() instanceof PaymentWithBillet) {
            PaymentWithBillet paymentWithBillet = (PaymentWithBillet) order.getPayment();
            billetService.fillPaymentWithBillet(paymentWithBillet, order.getDate());
        }

        orderRepository.save(order);
        paymentRepository.save(order.getPayment());

        for(OrderItem orderItem : order.getProducts()) {
            orderItem.setDiscount(0.0);
            orderItem.setPrice(productService.findById(orderItem.getProduct().getId()).getPrice());
            orderItem.setOrder(order);
        }

        orderItemRepository.saveAll(order.getProducts());
    }

    public OrderDTO fromDTO(Order order){
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getDate(), order.getClient(), order.getSalesman(), order.getAddress());
        orderDTO.setProducts(order.getProducts());
        return orderDTO;
    }

    public Order fromDTO(OrderDTO orderDTO){
        Client client = clientService.findById(orderDTO.getClient());
        Salesman salesman = salesmanRepository.findById(orderDTO.getSalesman()).orElseThrow(() -> new ObjectNotFoundException());

        Order order = new Order(orderDTO.getId(), orderDTO.getDate(), client, salesman, client.getAddress());
        order.setPayment(orderDTO.getPayment());
        order.setProducts(orderDTO.getProducts());
        return order;
    }
}