package com.axeane.services;

import com.axeane.model.Salarie;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SalariesServiceImplTest {

    private SalariesServiceImpl salariesServiceImpl;

    @Before
    public void setUp() {
        salariesServiceImpl = new SalariesServiceImpl();
    }

    @Test
    public void addSalarie() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        int sizeAfter = sizeBefore + 1;
        assertThat(sizeAfter, is(sizeBefore + 1));
        Salarie salarie1 = salaries.get(salaries.size() - 1);
        assertEquals("amira", salarie1.getNom());
    }

    @Test
    public void addSalarieNotEmpty() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        assertFalse(salarie.getNom().isEmpty());
        assertFalse(salarie.getPrenom().isEmpty());
        assertFalse(salarie.getAdresse().isEmpty());
    }

    @Test
    public void addSalarieNotNull() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        assertNotNull(salarie.getNom());
        assertNotNull(salarie.getPrenom());
        assertNotNull(salarie.getAdresse());
        assertNotNull(salarie.getSalaire());
        assertFalse(salarie.getAdresse().length() > 256);
    }

    @Test
    public void testAdresseLengh() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
    }

    @Test
    public void getListSalaries() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        salaries.add(salarie);
        int sizeAfter = sizeBefore + 1;
        assertFalse(salaries.isEmpty());
        assertThat(sizeAfter, is(sizeBefore + 1));
        Assertions.assertThat(salaries).contains(salarie);
    }

    @Test
    public void findSalariedById() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        System.out.println(salaries.toString());
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        salaries.add(salarie);
        Salarie salarie1 = salariesServiceImpl.findSalariedById((long) (salaries.size()));

        assertThat(salarie1.getId(), is(salarie.getId()));
        assertThat(salarie1.getNom(), is(salarie.getNom()));
        assertThat(salarie1.getPrenom(), is(salarie.getPrenom()));
        assertThat(salarie1.getAdresse(), is(salarie.getAdresse()));
    }

    @Test
    public void deleteSalaried() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        salariesServiceImpl.deleteSalaried((long) salaries.size());
        int sizeAfter = salaries.size();
        assertThat(sizeAfter, is(sizeBefore - 1));
    }

    @Test
    public void updateSalarie() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        Salarie salarie = new Salarie("raissi", "abir", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        Salarie salarie2 = new Salarie((long) (salaries.size()), "wifek", "wifek", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.updateSalarie(salarie2);
        assertThat(salaries.get(salaries.size() - 1).getPrenom(), is("wifek"));
        assertThat(salaries.get(salaries.size() - 1).getNom(), is("wifek"));
    }
}