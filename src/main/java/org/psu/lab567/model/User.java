package org.psu.lab567.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginCount = 0;
        this.orders = null;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(optional = true)
    @JoinColumn
    @JsonIgnore
    private BinFile avatar;

    @Column(nullable = false)
    private int loginCount;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @JsonIgnore
    private Collection<BuyOrder> orders;
}
