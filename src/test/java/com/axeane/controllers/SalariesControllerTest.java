package com.axeane.controllers;
import com.axeane.model.Salarie;
import com.axeane.services.SalariesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(SalariesController.class)
public class SalariesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    @Autowired
    SalariesService salarieServiceMock;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addSalarie() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        given(salarieServiceMock.addsalarie(salarie)).willReturn(salarie);
        mockMvc.perform(post("/salaries")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salarie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("ilyes"))
                .andExpect(jsonPath("$.prenom").value("raissi"))
                .andExpect(jsonPath("$.salaire").value(444444))
                .andExpect(jsonPath("$.adresse").value("Tunis"));
        verify(salarieServiceMock, times(1)).addsalarie(salarie);
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void getSalaries() throws Exception {
        Salarie salarie = new Salarie();
        salarie.setNom("Amine");
        List<Salarie> salaries = singletonList(salarie);
        given(salarieServiceMock.getListSalaries()).willReturn(salaries);
        mockMvc.perform(get("/salaries")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nom").value(salarie.getNom()));
    }

    @Test
    public void getSalariesById() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        given(salarieServiceMock.findSalariedById(salarie.getId())).willReturn(salarie);
        mockMvc.perform(get("/salaries/" + salarie.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom", is(salarie.getNom())));
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void updateSalaries() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        when(salarieServiceMock.findSalariedById(salarie.getId())).thenReturn(salarie);
        doNothing().when(salarieServiceMock).updateSalarie(salarie);
        mockMvc.perform(
                put("/salaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salarie)))
                .andExpect(status().isOk());
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verify(salarieServiceMock, times(1)).updateSalarie(salarie);
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void deleteSalaries() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        when(salarieServiceMock.findSalariedById(salarie.getId())).thenReturn(salarie);
        doNothing().when(salarieServiceMock).deleteSalaried(salarie.getId());
        mockMvc.perform(
                delete("/salaries/{id}", salarie.getId()))
                .andExpect(status().isOk());
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verify(salarieServiceMock, times(1)).deleteSalaried(salarie.getId());
        verifyNoMoreInteractions(salarieServiceMock);
    }
}