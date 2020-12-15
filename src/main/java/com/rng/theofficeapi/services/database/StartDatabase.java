package com.rng.theofficeapi.service.database;

import com.rng.theofficeapi.entities.*;
import com.rng.theofficeapi.entities.enums.PaymentStatus;
import com.rng.theofficeapi.repositories.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class StartDatabase {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void database(){
        Salesman jim = new Salesman(null, "Jim Halpert", "34g3k1049fd04");
        Salesman pam = new Salesman(null, "Pam Beesly", "938278fduf2903");
        Salesman dwight = new Salesman(null, "Dwight Schrute", "38938943rgege");
        Salesman andy = new Salesman(null, "Andy Bernard", "9483742389fwefe");
        Salesman stanley = new Salesman(null, "Stanley Hudson", "rvguehi342");

        Category sulfite = new Category(null, "Sulfite");
        Category couche = new Category(null, "Couché");
        Category reciclato = new Category(null, "Reciclato");
        Category kraft = new Category(null, "Kraft");
        Category vegetal = new Category(null, "Vegetal");
        Category duoDesign = new Category(null, "Duo Design");
        Category duplex = new Category(null, "Duplex");

        Product chamex = new Product(null, "Chamex", 15.35);
        Product usapel = new Product(null, "Usapel", 22.89);

        chamex.setCategories(Arrays.asList(sulfite));
        usapel.setCategories(Arrays.asList(couche));

        Client ryan = new Client(null, "Ryan", "38388989238928923", "ryan@mail.com", "3393237964");
        Client ashley = new Client(null, "Ashley", "45059249382789", "Ashley@mail.com", "076989087");

        Address address1 = new Address(null, "Rua srBagner", "265", "CAPKNG", "NEW YORK","reuig", null, ryan);
        Address address2 = new Address(null, "Rua MCDonald", "396", "flangfung", "washington","dc", "next time", ashley);

        ryan.setAddress(address1);
        ashley.setAddress(address2);

        Order order1 = new Order(null, new Date(), ryan, jim, address1);
        Order order2 = new Order(null, new Date(), ryan, dwight, ryan.getAddress());
        Order order3 = new Order(null, new Date(),ashley, pam, ashley.getAddress());

        Payment payment1 = new PaymentWithBillet(null, PaymentStatus.PENDING, order1, new Date(), new Date());
        Payment payment2 = new PaymentWithCard(null, PaymentStatus.CANCELED, order2, 15L);
        order1.setPayment(payment1);
        order2.setPayment(payment2);

        jim.setOrders(Arrays.asList(order1));
        dwight.setOrders(Arrays.asList(order2));
        pam.setOrders(Arrays.asList(order3));

        ryan.setOrders(Arrays.asList(order1, order2));
        ashley.setOrders(Arrays.asList(order3));

        OrderItem orderItem1 = new OrderItem(order1, chamex, 0.0, 5L, 20.00);
        OrderItem orderItem2 = new OrderItem(order1, usapel, 0.0, 3L, 13.00);

        salesmanRepository.saveAll(Arrays.asList(jim, pam, dwight, andy, stanley));
        categoryRepository.saveAll(Arrays.asList(sulfite, couche, reciclato, kraft, vegetal, duoDesign, duplex));
        productRepository.saveAll(Arrays.asList(chamex, usapel));
        clientRepository.saveAll(Arrays.asList(ryan, ashley));
        addressRepository.saveAll(Arrays.asList(address1, address2));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));
        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));
    }
}