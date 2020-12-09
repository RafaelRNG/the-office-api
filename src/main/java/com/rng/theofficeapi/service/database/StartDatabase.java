package com.rng.theofficeapi.service.database;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.entities.Salesman;
import com.rng.theofficeapi.repositories.CategoryRepository;
import com.rng.theofficeapi.repositories.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StartDatabase {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        salesmanRepository.saveAll(Arrays.asList(jim, pam, dwight, andy, stanley));
        categoryRepository.saveAll(Arrays.asList(sulfite, couche, reciclato, kraft, vegetal, duoDesign, duplex));
    }
}