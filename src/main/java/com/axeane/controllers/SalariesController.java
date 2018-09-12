package com.axeane.controllers;

import com.axeane.model.Salarie;
import com.axeane.services.SalariesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalariesController {

    private final SalariesService salariesService;

    public SalariesController(SalariesService salariesService) {
        this.salariesService = salariesService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addSalaries(@RequestBody Salarie salarie) {
        salariesService.addsalarie(salarie);
        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getSalaries() {
        List<Salarie> salaries = salariesService.getListSalaries();
        if (salaries != null) {
            return new ResponseEntity<>(salaries, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSalariesById(@PathVariable("id") long id) {
        Salarie salarie = salariesService.findSalariedById(id);
        if (salarie != null) {
            return new ResponseEntity<>(salarie, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity updateSalaries(@RequestBody Salarie salarie) {
        if (salariesService.findSalariedById(salarie.getId()) != null) {
            salariesService.updateSalarie(salarie);
            return new ResponseEntity<>(salarie, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSalaries(@PathVariable("id") Long id) {
        Salarie salarie = salariesService.findSalariedById(id);
        if (salarie != null) {
            salariesService.deleteSalaried(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
