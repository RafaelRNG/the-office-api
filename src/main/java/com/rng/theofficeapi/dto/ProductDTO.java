package com.rng.theofficeapi.dto;

import com.rng.theofficeapi.entities.Category;
import com.rng.theofficeapi.entities.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDTO {

    private Long id;

    @NotNull(message = "null is not allowed")
    @Length(min = 3, max = 120, message = "only products between 3 and 120 characters")
    private String name;

    @NotNull(message = "null is not allowed")
    private Double price;

    @NotNull(message = "null is not allowed")
    @NotEmpty(message = "Must not be empty")
    private List<Long> categories = new ArrayList<>();

    public ProductDTO(){}

    public ProductDTO(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }
}
