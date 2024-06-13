package com.colvir.link.shortener.service;

import com.colvir.link.shortener.dto.*;
import com.colvir.link.shortener.exception.LinkNotFoundException;
import com.colvir.link.shortener.mapper.LinkMapper;
import com.colvir.link.shortener.model.Link;
import com.colvir.link.shortener.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkMapper linkMapper;
    private final LinkRepository linkRepository;

    public GenerateLinkResponse generateShortLink(GenerateLinkRequest request) {
        String originalLink = request.getOriginalLink();
        String shortLink = Base64.getEncoder().encodeToString(originalLink.getBytes(StandardCharsets.UTF_8));

        Link newLink = new Link(originalLink, shortLink);

        linkRepository.save(newLink);

        return linkMapper.linkToGenerateLinkResponse(newLink);
    }

    public RedirectView redirectByShortLink(String shortLink) {
        Link link = linkRepository.findByShorted(shortLink);

        if (link == null) {
            throw new LinkNotFoundException("Ссылка не найдена");
        }

        return new RedirectView(link.getOriginal());
    }

    public LinkPageResponse getAll() {
        List<Link> allLinks = linkRepository.findAll();
        return linkMapper.linksToLinkPageResponse(allLinks);
    }

    public LinkResponse getById(Integer id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(String.format("Ссылка с id = %s не найдена", id)));
        return linkMapper.linkToLinkResponse(link);
    }

    public LinkResponse update(UpdateLinkRequest request) {
        Integer linkId = request.getId();
        Link link = linkRepository.findById(linkId)
                .orElseThrow(() -> new LinkNotFoundException(String.format("Ссылка с id = %s не найдена", linkId)));

        Link updatedLink = linkMapper.updateLinkRequestToLink(request);//todo затираются поля

        linkRepository.save(updatedLink);

        return linkMapper.linkToLinkResponse(updatedLink);
    }

    public LinkResponse delete(Integer id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(String.format("Ссылка с id = %s не найдена", id)));

        linkRepository.deleteById(id);

        return linkMapper.linkToLinkResponse(link);
    }
}
