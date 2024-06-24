package com.colvir.link.shortener.generator;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.model.Link;
import com.colvir.link.shortener.model.LinkStatus;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class LinkGenerator {

    public static final Integer LINK_ID = 1;
    public static final String ORIGINAL_LINK = "https://ya.ru";
    public static final String SHORTED_LINK = Base64.getEncoder().encodeToString(ORIGINAL_LINK.getBytes(StandardCharsets.UTF_8));

    public static Link.LinkBuilder generateLinkAfterCreateBuilder() {
        return Link.builder()
                .setId(LINK_ID)
                .setOriginal(ORIGINAL_LINK)
                .setShorted(SHORTED_LINK);
    }

    public static Link.LinkBuilder generateLinkAfterUpdateBuilder(String newOriginalField) {
        return generateLinkAfterCreateBuilder()
                .setOriginal(newOriginalField)
                .setStatus(LinkStatus.UPDATED);
    }
}
