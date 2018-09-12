package com.axeane.controllers;

import com.axeane.MainApplicationClass;

import com.axeane.model.Salarie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplicationClass.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalariesControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addSalarie() {
        ResponseEntity<Salarie> responseEntity =
                restTemplate.postForEntity("/salaries", new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis"), Salarie.class);
        Salarie salarie = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assert salarie != null;
        assertEquals("ilyes", salarie.getNom());
    }
}