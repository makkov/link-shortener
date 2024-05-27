package com.colvir.link.shortener.controller;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("generate")
    public GenerateLinkResponse generateLink(@RequestBody GenerateLinkRequest request) {
        return linkService.generateShortLink(request);
    }

    @GetMapping("/{shortLink}")
    public RedirectView redirect(@PathVariable String shortLink) {
        return linkService.redirectByShortLink(shortLink);
    }
}
