package org.psu.lab567.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BinFile {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private int size;

    @Lob
    @Column(nullable = false)
    private byte[] content;
}
