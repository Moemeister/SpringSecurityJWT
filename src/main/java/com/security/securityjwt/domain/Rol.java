package com.security.securityjwt.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(generator = "rol_id_rol_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "rol_id_rol_seq", sequenceName = "public.rol_id_rol_seq", allocationSize = 1)
    @Column(name = "id_rol")
    private int id;

    @Column(name = "rol")
    private String rol;

}
