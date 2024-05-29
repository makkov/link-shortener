package com.colvir.link.shortener.service;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.exception.LinkNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.*;


class LinkServiceTest {

    LinkService linkService = new LinkService();


    @Test
    void generateShortLink_success() {
        //Подготовка входных данных
        GenerateLinkRequest request = new GenerateLinkRequest();
        request.setOriginalLink("https://ya.ru");

        //Подготовка ожидаемого результата
        GenerateLinkResponse expectedResponse = new GenerateLinkResponse();
        expectedResponse.setShortLink("aHR0cHM6Ly95YS5ydQ==");

        //Начало теста
        GenerateLinkResponse actualResponse = linkService.generateShortLink(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void redirectByShortLink_success() {
        //Подготовка входных данных
        String shortLink = "aHR0cHM6Ly95YS5ydQ==";

        //Подготовка ожидаемого результата
        GenerateLinkRequest request = new GenerateLinkRequest();
        request.setOriginalLink("https://ya.ru");

        linkService.generateShortLink(request);

        RedirectView expectedRedirectView = new RedirectView("https://ya.ru");

        //Начало теста
        RedirectView actualRedirectView = linkService.redirectByShortLink(shortLink);

        assertEquals(expectedRedirectView.getUrl(), actualRedirectView.getUrl());
    }

    @Test
    void redirectByShortLink_withException() {
        //Подготовка входных данных
        String shortLink = "aHR0cHM6Ly95YS5ydQ==";

        //Подготовка ожидаемого результата
        String expectedMessage = "Ссылка не найдена";

        //Начало теста
        Exception exception = assertThrows(LinkNotFoundException.class, () -> linkService.redirectByShortLink(shortLink));
        assertEquals(expectedMessage, exception.getMessage());
    }
}
