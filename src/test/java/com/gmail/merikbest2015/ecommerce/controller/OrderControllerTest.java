package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.OrderRepository;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.Impl.MailSender;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderController orderController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void getOrderTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        User user = new User();
        Perfume perfume = new Perfume();

        user.setPerfumeList(perfumes);
        user.getPerfumeList().add(perfume);

        assertNotNull(user.getPerfumeList());
        assertEquals(1, user.getPerfumeList().size());
    }

    @Test
    public void postOrderTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        User user = new User();
        Perfume perfume = new Perfume();

        user.setPerfumeList(perfumes);
        user.getPerfumeList().add(perfume);

        userService.save(user);

        Order order = new Order(user);
        order.setId(1L);
        order.setFirstName("John");
        order.setPerfumeList(user.getPerfumeList());

        orderService.save(order);

        assertNotNull(user);
        assertNotNull(user.getPerfumeList());
        assertEquals(1, user.getPerfumeList().size());
        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals("John", order.getFirstName());
        assertEquals(1, order.getPerfumeList().size());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void finalizeOrderTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        User user = new User();
        Perfume perfume = new Perfume();
        Order order = new Order(user);

        user.setPerfumeList(perfumes);
        user.getPerfumeList().add(perfume);
        order.setPerfumeList(user.getPerfumeList());

        when(orderService.findAll()).thenReturn(Collections.singletonList(order));

        assertNotNull(user);
        assertNotNull(user.getPerfumeList());
        assertEquals(1, user.getPerfumeList().size());
        assertNotNull(order);
        assertEquals(1, order.getPerfumeList().size());
    }
}
