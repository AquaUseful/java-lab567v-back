package org.psu.lab567.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private final Long id;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    @JoinColumn
    private BinFile avatar;
}
