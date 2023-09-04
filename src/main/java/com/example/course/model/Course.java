package com.example.course.model;

import com.example.course.enums.Category;
import com.example.course.enums.conveters.CategoryConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity
@SQLDelete(sql = "UPDATE Course set STATUS  = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotNull
    @NotBlank
    @Length(min=5, max=100)
    @Column(length = 100, nullable = false)
    private String name;


    @Convert(converter = CategoryConverter.class)
    @Column(length = 10, nullable = false)
    private Category category;

    @NotBlank
    @Pattern(regexp= "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
