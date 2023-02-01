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
@Getter
@Setter
public class BinFile {
    public BinFile(String mimeType, long size, byte[] content) {
        this.mimeType = mimeType;
        this.size = size;
        this.content = content;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private long size;

    @Lob
    @Column(nullable = false)
    private byte[] content;
}
