package com.security.securityjwt.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "USER")
public class User {

    @Id
    private int id;

    private String name;
    private String lastName;
    private String username;
    private String pass;
}
