package com.colvir.link_shortener.model;

import lombok.Data;

@Data
public class Link {

    private final String original;

    private final String shorted;
}
