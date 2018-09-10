package com.axeane.services;


import com.axeane.model.Salarie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SalariesServiceImpl implements SalariesService {

    private static SalariesServiceImpl instance = null;

    public static SalariesServiceImpl getInstance() {
        if (instance == null) {
            instance = new SalariesServiceImpl();
        }
        return instance;
    }

    private Logger logger = LoggerFactory.getLogger(SalariesServiceImpl.class);
    private List<Salarie> salariess = Stream.of(
            new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis"),
            new Salarie("rahma", "raissi", new BigDecimal(55555), "Tunis"),
            new Salarie("amine", "raissi", new BigDecimal(88888), "Tunis"),
            new Salarie("ines", "raissi", new BigDecimal(999999), "Tunis"))
            .collect(Collectors.toList());

    @Override
    public void addsalarie(Salarie salarie) {
        salariess.add(salarie);
    }

    @Override
    public List<Salarie> getListSalaries() {
        return salariess;
    }

    @Override
    public Salarie findSalariedById(Long searchedId) {
        return salariess.stream()
                .filter(x -> searchedId.equals((x.getId())))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteSalaried(Long id) {
        Salarie salarie = findSalariedById(id);
        salariess.remove(salarie);
    }

    @Override
    public void updateSalarie(Salarie salarie) {
        Salarie salarie1 = findSalariedById(salarie.getId());
        if (salarie1 != null) {
            salarie1.setNom(salarie.getNom());
            salarie1.setPrenom(salarie.getPrenom());
            salarie1.setAdresse(salarie.getAdresse());
            salarie1.setSalaire(salarie.getSalaire());
        }
    }

}