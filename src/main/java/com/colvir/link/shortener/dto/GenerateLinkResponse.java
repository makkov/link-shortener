package com.colvir.link.shortener.dto;

import lombok.Data;

@Data
public class GenerateLinkResponse {

    private String shorted;

    private String original;
}
