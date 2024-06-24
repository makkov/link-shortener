package com.colvir.link.shortener.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class LinkResponse {

    private Integer id;

    private String original;

    private String shorted;
}
