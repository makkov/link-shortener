package com.colvir.link.shortener.dto;

import lombok.Data;

@Data
public class LinkResponse {

    private Integer id;

    private String original;

    private String shorted;
}
