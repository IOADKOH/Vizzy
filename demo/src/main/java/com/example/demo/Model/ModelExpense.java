package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table (name ="expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelExpense {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int ammontare;
    private Date data;
    @ManyToOne
    @JoinColumn(name="id_category")
    private ModelCategories categories;


}
