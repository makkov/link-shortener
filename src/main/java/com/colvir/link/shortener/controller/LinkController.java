package com.colvir.link.shortener.controller;

import com.colvir.link.shortener.dto.*;
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

    @GetMapping("/go/{shortLink}")
    public RedirectView redirect(@PathVariable String shortLink) {
        return linkService.redirectByShortLink(shortLink);
    }

    @GetMapping
    public LinkPageResponse getAll() {
        return linkService.getAll();
    }

    @GetMapping("/{id}")
    public LinkResponse getById(@PathVariable("id") Integer id) {
        return linkService.getById(id);
    }

    @PutMapping
    public LinkResponse update(@RequestBody UpdateLinkRequest request) {
        return linkService.update(request);
    }

    @DeleteMapping("/{id}")
    public LinkResponse delete(@PathVariable Integer id) {
        return linkService.delete(id);
    }
}
