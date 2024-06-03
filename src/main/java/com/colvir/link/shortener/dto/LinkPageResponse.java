package com.colvir.link.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LinkPageResponse {
    
    private List<LinkResponse> links;
}
