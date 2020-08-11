package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repos.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfumeRepository perfumeRepository;

    @Autowired
    private PerfumeService perfumeService;

    @Autowired
    private MainController mainController;

    @Test
    public void homeTest() throws Exception {
        Perfume perfume1 = generatePerfume(39L, "Dior", "Dior");
        Perfume perfume2 = generatePerfume(40L, "Gucci", "Gucci");

        when(perfumeRepository.findAll()).thenReturn(Arrays.asList(perfume1, perfume2));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(model().attribute("perfumes", hasSize(2)))
                .andExpect(model().attribute("perfumes", hasItem(
                        allOf(
                                hasProperty("id", is(39L)),
                                hasProperty("perfumeTitle", is("Dior")),
                                hasProperty("perfumer", is("Dior"))
                        )
                )))
                .andExpect(model().attribute("perfumes", hasItem(
                        allOf(
                                hasProperty("id", is(40L)),
                                hasProperty("perfumeTitle", is("Gucci")),
                                hasProperty("perfumer", is("Gucci"))
                        )
                )));
    }

    @Test
    public void searchTest() throws Exception {
        List<Perfume> perfumeList = new ArrayList<>();
        Perfume perfume = new Perfume();
        perfume.setPerfumer("Dior");
        perfumeList.add(perfume);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Perfume> page = new PageImpl<>(perfumeList);

        when(perfumeRepository.findByPerfumerOrPerfumeTitle(perfume.getPerfumer(), perfume.getPerfumer(), pageable)).thenReturn(page);

        assertNotNull(perfumeList);
        assertNotNull(perfume.getPerfumer());
        assertEquals(1, perfumeService.findByPerfumerOrPerfumeTitle(perfume.getPerfumer(), perfume.getPerfumer(), pageable).getSize());
        assertEquals("Dior", perfumeService.findByPerfumerOrPerfumeTitle(perfume.getPerfumer(), perfume.getPerfumer(), pageable).getContent().get(0).getPerfumer());
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Long id = 7L;
        Perfume perfume = new Perfume();
        perfume.setId(7L);

        assertNotNull(perfume.getId());
        assertEquals(id, perfume.getId());
    }

    private Perfume generatePerfume(Long id, String perfumeTitle, String perfumer) {
        Perfume perfume = new Perfume();
        perfume.setId(id);
        perfume.setPerfumeTitle(perfumeTitle);
        perfume.setPerfumer(perfumer);
        perfume.setPrice(1000);

        return perfume;
    }
}
