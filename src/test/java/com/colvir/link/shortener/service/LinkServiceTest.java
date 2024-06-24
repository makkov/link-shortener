package com.colvir.link.shortener.service;

import com.colvir.link.shortener.config.TestConfig;
import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.dto.LinkResponse;
import com.colvir.link.shortener.exception.LinkNotFoundException;
import com.colvir.link.shortener.generator.LinkDtoGenerator;
import com.colvir.link.shortener.generator.LinkGenerator;
import com.colvir.link.shortener.mapper.LinkMapper;
import com.colvir.link.shortener.model.Link;
import com.colvir.link.shortener.repository.LinkCacheRepository;
import com.colvir.link.shortener.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

import static com.colvir.link.shortener.generator.LinkDtoGenerator.*;
import static com.colvir.link.shortener.generator.LinkGenerator.*;
import static com.colvir.link.shortener.model.LinkStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        LinkService.class,
        LinkMapper.class
})
@SpringBootTest(classes = {TestConfig.class})
class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @MockBean
    private LinkRepository linkRepository;

    @MockBean
    private LinkCacheRepository linkCacheRepository;

    @Test
    void generateShortLink_success() {
        //Подготовка входных данных
        GenerateLinkRequest request = generateLinkRequestBuilder()
                .build();

        //Подготовка ожидаемого результата
        GenerateLinkResponse expectedResponse = generateLinkResponseBuilder()
                .build();

        when(linkRepository.save(any())).thenReturn(any());

        //Начало теста
        GenerateLinkResponse actualResponse = linkService.generateShortLink(request);
        assertEquals(expectedResponse, actualResponse);
        verify(linkRepository).save(any());
        verifyNoMoreInteractions(linkRepository);
    }

    @Test
    void redirectByShortLink_success() {
        //Подготовка входных данных
        String shortLink = SHORTED_LINK;

        //Подготовка ожидаемого результата
        Link link = generateLinkAfterCreateBuilder()
                .build();

        when(linkRepository.findByShorted(shortLink)).thenReturn(link);

        RedirectView expectedRedirectView = generateRedirectView();

        //Начало теста
        RedirectView actualRedirectView = linkService.redirectByShortLink(shortLink);
        assertEquals(expectedRedirectView.getUrl(), actualRedirectView.getUrl());
        verify(linkRepository).findByShorted(shortLink);
        verifyNoMoreInteractions(linkRepository);
    }

    @Test
    void redirectByShortLink_withException() {
        //Подготовка входных данных
        String shortLink = SHORTED_LINK;

        //Подготовка ожидаемого результата
        when(linkRepository.findByShorted(shortLink)).thenReturn(null);
        String expectedMessage = "Ссылка не найдена";

        //Начало теста
        Exception exception = assertThrows(LinkNotFoundException.class, () -> linkService.redirectByShortLink(shortLink));
        assertEquals(expectedMessage, exception.getMessage());
        verify(linkRepository).findByShorted(shortLink);
        verifyNoMoreInteractions(linkRepository);
    }

    @Test
    void getByIdInDb_success() {
        //Подготовка входных данных
        Integer id = LINK_ID;

        //Подготовка ожидаемого результата
        LinkResponse expectedLinkResponse = LinkDtoGenerator.linkResponseBuilder()
                .build();

        Link link = generateLinkAfterCreateBuilder()
                .build();

        when(linkCacheRepository.findById(id)).thenReturn(Optional.empty());
        when(linkRepository.findById(id)).thenReturn(Optional.of(link));
        when(linkCacheRepository.save(link)).thenReturn(link);

        //Начало теста
        LinkResponse actualLinkResponse = linkService.getById(id);
        assertEquals(expectedLinkResponse, actualLinkResponse);

        verify(linkCacheRepository).findById(id);
        verify(linkRepository).findById(id);
        verify(linkCacheRepository).save(link);
        verifyNoMoreInteractions(linkRepository, linkCacheRepository);
    }
}
