package com.orbitech.npvet.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutor", schema = "public")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tutor extends AbstractEntity {
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(unique = true, length = 20)
    private String rg;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private Genero genero;

    @OneToMany(mappedBy = "tutor")
    @JsonIgnoreProperties("tutor")
    private List<Anamnese> anamneses = new ArrayList<>();

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "tutor_contato",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "contato_id"))
    private List <Contato> telefones;


    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
            name = "tutor_endereco",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos;
}
