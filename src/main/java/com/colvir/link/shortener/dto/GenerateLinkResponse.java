package com.colvir.link.shortener.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class GenerateLinkResponse {

    private String shorted;

    private String original;
}
