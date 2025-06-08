package com.projetosara.sara_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "NIVELALERTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NivelAlerta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nivel_alerta_seq")
    @SequenceGenerator(name = "nivel_alerta_seq", sequenceName = "SEQ_NIVEL_ALERTA", allocationSize = 1)
    @Column(name = "ID_NIVEL_ALERTA")
    private Long id;

    private String codigo;

    private String descricao;

    @OneToMany(mappedBy = "nivelAlerta")
    private List<Alerta> alertas;
}