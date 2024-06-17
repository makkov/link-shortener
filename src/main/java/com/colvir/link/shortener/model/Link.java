package com.colvir.link.shortener.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Table(name = "links")
@Entity
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_seq")
    @SequenceGenerator(name = "link_seq", sequenceName = "link_sequence", allocationSize = 1)
    private Integer id;

    private String original;

    private String shorted;

    private BigDecimal salary;

    @Enumerated(value = EnumType.STRING)
    private LinkStatus status;

    public Link(String original, String shorted, LinkStatus status) {
        this.original = original;
        this.shorted = shorted;
        this.status = status;
    }
}
