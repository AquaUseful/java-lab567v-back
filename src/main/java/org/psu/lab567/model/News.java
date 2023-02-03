package org.psu.lab567.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class News {
    public News(@NonNull String title, @NonNull String content, BinFile picture) {
        this.title = title;
        this.content = content;
        this.picture = picture;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne(optional = true)
    @JoinColumn
    @JsonIgnore
    private BinFile picture;
}
