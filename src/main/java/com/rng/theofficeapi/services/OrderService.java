package com.rng.theofficeapi.services;

import com.rng.theofficeapi.dto.ClientDTO;
import com.rng.theofficeapi.dto.OrderDTO;
import com.rng.theofficeapi.dto.SalesmanDTO;
import com.rng.theofficeapi.entities.*;
import com.rng.theofficeapi.entities.enums.PaymentStatus;
import com.rng.theofficeapi.repositories.*;
import com.rng.theofficeapi.security.UserDetailsSecurity;
import com.rng.theofficeapi.services.email.EmailService;
import com.rng.theofficeapi.services.exceptions.AuthorizationException;
import com.rng.theofficeapi.services.exceptions.ObjectNotFoundException;
import com.rng.theofficeapi.services.exceptions.OrderWithoutAddressException;
import com.rng.theofficeapi.services.security.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
    private SalesmanRepository salesmanRepository;

    @Autowired
    private EmailService emailService;

    public Page<Order> pagination(Integer page, Integer linesPerPage, String direction, String orderBy) {

        UserDetailsSecurity userDetailsSecurity = UserService.authenticated();
        if(userDetailsSecurity == null) {
            throw new AuthorizationException();
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        ClientDTO clientDTO = clientService.findById(userDetailsSecurity.getId());

        return orderRepository.findByClient(clientDTO, pageRequest);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
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

        emailService.sendOrderConfirmationEmail(order);
        orderItemRepository.saveAll(order.getProducts());
    }

    public OrderDTO fromDTO(Order order){
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getDate(), order.getClient(), order.getSalesman(), order.getAddress());
        orderDTO.setProducts(order.getProducts());
        return orderDTO;
    }

    public Order fromDTO(OrderDTO orderDTO){
        ClientDTO clientDTO = clientService.findById(orderDTO.getClient());
        Client client = new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getCnpj(), clientDTO.getEmail(), clientDTO.getCellPhone(), clientDTO.getPassword());
        client.setAddress(clientDTO.getAddress());
        Salesman salesman = salesmanRepository.findById(orderDTO.getSalesman()).orElseThrow(() -> new ObjectNotFoundException());

        if(client.getAddress() != null){

            Order order = new Order(orderDTO.getId(), orderDTO.getDate(), client, salesman, client.getAddress());
            order.setPayment(orderDTO.getPayment());
            order.setProducts(orderDTO.getProducts());
            return order;
        } else {
            throw new OrderWithoutAddressException();
        }
    }
}