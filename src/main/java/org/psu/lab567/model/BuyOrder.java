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
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuyOrder {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String mainInfo;

    @Column(nullable = true)
    private String additionalInfo;

    @OneToOne
    @JoinColumn
    private BinFile attachment;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;
}
