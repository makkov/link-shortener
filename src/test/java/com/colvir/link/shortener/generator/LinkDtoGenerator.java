package com.colvir.link.shortener.generator;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.dto.LinkResponse;
import com.colvir.link.shortener.model.Link;
import org.springframework.web.servlet.view.RedirectView;

public class LinkDtoGenerator {

    public static GenerateLinkRequest.GenerateLinkRequestBuilder generateLinkRequestBuilder() {
        Link link = LinkGenerator.generateLinkAfterCreateBuilder()
                .build();

        return GenerateLinkRequest.builder()
                .setOriginalLink(link.getOriginal());
    }

    public static GenerateLinkResponse.GenerateLinkResponseBuilder generateLinkResponseBuilder() {
        Link link = LinkGenerator.generateLinkAfterCreateBuilder()
                .build();

        return GenerateLinkResponse.builder()
                .setShorted(link.getShorted())
                .setOriginal(link.getOriginal());
    }

    public static LinkResponse.LinkResponseBuilder linkResponseBuilder() {
        Link link = LinkGenerator.generateLinkAfterCreateBuilder()
                .build();

        return LinkResponse.builder()
                .setId(link.getId())
                .setShorted(link.getShorted())
                .setOriginal(link.getOriginal());
    }

    public static RedirectView generateRedirectView() {
        Link link = LinkGenerator.generateLinkAfterCreateBuilder()
                .build();
        return new RedirectView(link.getOriginal());
    }
}
