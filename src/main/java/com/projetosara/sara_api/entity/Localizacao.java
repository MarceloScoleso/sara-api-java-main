package com.projetosara.sara_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOCALIZACAO")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localizacao_seq")
    @SequenceGenerator(name = "localizacao_seq", sequenceName = "SEQ_LOCALIZACAO", allocationSize = 1)
    @Column(name = "ID_LOCALIZACAO")
    private Long id;

    private String cidade;
    private String estado;

    @OneToMany(mappedBy = "localizacao")
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "localizacao")
    private List<Alerta> alertas;
}