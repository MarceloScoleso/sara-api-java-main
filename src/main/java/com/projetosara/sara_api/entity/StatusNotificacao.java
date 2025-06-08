package com.projetosara.sara_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "STATUSNOTIFICACAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_notificacao_seq")
    @SequenceGenerator(name = "status_notificacao_seq", sequenceName = "SEQ_STATUS_NOTIFICACAO", allocationSize = 1)
    @Column(name = "ID_STATUS_NOTIFICACAO")
    private Long id;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToMany(mappedBy = "status")
    private List<Notificacao> notificacoes;
}
