package com.projetosara.sara_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "LEITURASENSOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leitura_sensor_seq")
    @SequenceGenerator(name = "leitura_sensor_seq", sequenceName = "SEQ_LEITURA_SENSOR", allocationSize = 1)
    @Column(name = "ID_LEITURA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;

    private Double valor;
    private String unidade;

    @Column(name = "DATA_HORA")
    private Date dataHora;
}