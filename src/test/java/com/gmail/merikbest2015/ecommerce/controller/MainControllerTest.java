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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

    @Test
    public void testHome() throws Exception {
        List<Perfume> perfumeList = new ArrayList<>();
        perfumeList.add(new Perfume());
        perfumeList.add(new Perfume());

        when(perfumeRepository.findAll()).thenReturn(perfumeList);

        assertEquals(2, perfumeService.findAll().size());
    }

    @Test
    public void testSearch() throws Exception {
        List<Perfume> perfumeList = new ArrayList<>();
        Perfume perfume = new Perfume();
        perfume.setPerfumer("Dior");
        perfumeList.add(perfume);

        Pageable pageable = PageRequest.of(0, 12);
        Page<Perfume> page = new PageImpl<Perfume>(perfumeList);

        when(perfumeRepository.findByPerfumerOrPerfumeTitle(perfume.getPerfumer(), perfume.getPerfumer(), pageable)).thenReturn(page);

        assertEquals(1, perfumeService.findByPerfumerOrPerfumeTitle(perfume.getPerfumer(), perfume.getPerfumer(), pageable).getSize());
    }

    @Test
    public void testGetProductById() throws Exception {
        Long id = 7L;
        Perfume perfume = new Perfume();
        perfume.setId(7L);

        when(perfumeRepository.getOne(id)).thenReturn(perfume);

        assertEquals(id, perfume.getId());
    }
}
