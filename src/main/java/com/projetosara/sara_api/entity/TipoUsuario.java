package com.projetosara.sara_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIPOUSUARIO")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_usuario_seq")
    @SequenceGenerator(name = "tipo_usuario_seq", sequenceName = "SEQ_TIPO_USUARIO", allocationSize = 1)
    @Column(name = "ID_TIPO_USUARIO")
    private Long id;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuarios;
}