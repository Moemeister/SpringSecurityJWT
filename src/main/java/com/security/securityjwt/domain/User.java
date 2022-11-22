package com.security.securityjwt.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
public class User {

    @Id
    @GeneratedValue(generator = "usuario_idusuario_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "usuario_idusuario_seq", sequenceName = "public.usuario_idusuario_seq", allocationSize = 1)
    @Column(name = "idusuario")
    private int id;

    @Column(name="nombre")
    private String name;

    @Column(name="apellido")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="pass")
    private String pass;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idusuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles;
}
