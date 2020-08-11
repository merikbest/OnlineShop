package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.repos.UserRepository;
import com.gmail.merikbest2015.ecommerce.service.Impl.MailSender;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void getCartTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        perfumes.add(new Perfume());
        perfumes.add(new Perfume());

        User user = new User();
        user.setPerfumeList(perfumes);

        assertNotNull(user.getPerfumeList());
        assertEquals(2, user.getPerfumeList().size());
    }

    @Test
    public void addToCartTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        User user = new User();

        user.setPerfumeList(perfumes);
        user.getPerfumeList().add(new Perfume());

        userService.addUser(user);

        assertNotNull(user.getPerfumeList());
        assertEquals(1, user.getPerfumeList().size());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void removeFromCartTest() throws Exception {
        List<Perfume> perfumes = new ArrayList<>();
        User user = new User();
        Perfume perfume = new Perfume();

        user.setPerfumeList(perfumes);
        user.getPerfumeList().add(perfume);

        userService.addUser(user);

        user.getPerfumeList().remove(perfume);

        userService.addUser(user);

        assertNotNull(user.getPerfumeList());
        assertEquals(0, user.getPerfumeList().size());

        Mockito.verify(userRepository, Mockito.times(2)).save(user);
    }
}
