package com.colvir.link.shortener.service;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.exception.LinkNotFoundException;
import com.colvir.link.shortener.mapper.LinkMapper;
import com.colvir.link.shortener.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final Map<String, Link> linkByShortLink = new HashMap<>();
    private final LinkMapper linkMapper = new LinkMapper();

    public GenerateLinkResponse generateShortLink(GenerateLinkRequest request) {
        String originalLink = request.getOriginalLink();
        String shortLink = Base64.getEncoder().encodeToString(originalLink.getBytes(StandardCharsets.UTF_8));

        Link newLink = new Link(originalLink, shortLink);
        linkByShortLink.put(shortLink, newLink);

        return linkMapper.linkToGenerateLinkResponse(newLink);
    }

    public RedirectView redirectByShortLink(String shortLink) {
        Link link = linkByShortLink.get(shortLink);

        if (link == null) {
            throw new LinkNotFoundException("Ссылка не найдена");
        }

        return new RedirectView(link.getOriginal());
    }
}
