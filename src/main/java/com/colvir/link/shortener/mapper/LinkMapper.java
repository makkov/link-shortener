package com.colvir.link.shortener.mapper;

import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.model.Link;
import org.springframework.stereotype.Component;

//@Component
public class LinkMapper {

    public GenerateLinkResponse linkToGenerateLinkResponse(Link link) {
        GenerateLinkResponse response = new GenerateLinkResponse();
        response.setShortLink(link.getShorted());
        return response;
    }
}
