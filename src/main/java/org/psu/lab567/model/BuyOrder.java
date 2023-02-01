package org.psu.lab567.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BuyOrder {
    public BuyOrder(@NonNull String product, @NonNull String address, String comment, @NonNull User autor) {
        this.product = product;
        this.address = address;
        this.comment = comment;
        this.author = autor;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;
}
