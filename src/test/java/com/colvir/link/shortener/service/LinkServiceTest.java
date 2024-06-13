//package com.colvir.link.shortener.service;
//
//import com.colvir.link.shortener.dto.GenerateLinkRequest;
//import com.colvir.link.shortener.dto.GenerateLinkResponse;
//import com.colvir.link.shortener.exception.LinkNotFoundException;
//import com.colvir.link.shortener.mapper.LinkMapper;
//import com.colvir.link.shortener.model.Link;
//import com.colvir.link.shortener.repository.LinkRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.servlet.view.RedirectView;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {
//        LinkService.class,
//        LinkMapper.class
//})
//class LinkServiceTest {
//
//    @Autowired
//    private LinkService linkService;
//
//    @MockBean
//    private LinkRepository linkRepository;
//
//    @Test
//    void generateShortLink_success() {
//        //Подготовка входных данных
//        GenerateLinkRequest request = new GenerateLinkRequest();//todo написать генераторы
//        request.setOriginalLink("https://ya.ru");
//
//        //Подготовка ожидаемого результата
//        GenerateLinkResponse expectedResponse = new GenerateLinkResponse();
//        expectedResponse.setShorted("aHR0cHM6Ly95YS5ydQ==");
//
//        when(linkRepository.save(any())).thenReturn(any());
//
//        //Начало теста
//        GenerateLinkResponse actualResponse = linkService.generateShortLink(request);
//        assertEquals(expectedResponse, actualResponse);
//        verify(linkRepository).save(any());
//        verifyNoMoreInteractions(linkRepository);
//    }
//
//    @Test
//    void redirectByShortLink_success() {
//        //Подготовка входных данных
//        String shortLink = "aHR0cHM6Ly95YS5ydQ==";
//
//        //Подготовка ожидаемого результата
//        Link link = new Link("https://ya.ru", shortLink);
//        when(linkRepository.getByShorted(shortLink)).thenReturn(link);
//
//        RedirectView expectedRedirectView = new RedirectView("https://ya.ru");
//
//        //Начало теста
//        RedirectView actualRedirectView = linkService.redirectByShortLink(shortLink);
//        assertEquals(expectedRedirectView.getUrl(), actualRedirectView.getUrl());
//        verify(linkRepository).getByShorted(shortLink);
//        verifyNoMoreInteractions(linkRepository);
//    }
//
//    @Test
//    void redirectByShortLink_withException() {
//        //Подготовка входных данных
//        String shortLink = "aHR0cHM6Ly95YS5ydQ==";
//
//        //Подготовка ожидаемого результата
//        when(linkRepository.getByShorted(shortLink)).thenReturn(null);
//        String expectedMessage = "Ссылка не найдена";
//
//        //Начало теста
//        Exception exception = assertThrows(LinkNotFoundException.class, () -> linkService.redirectByShortLink(shortLink));
//        assertEquals(expectedMessage, exception.getMessage());
//        verify(linkRepository).getByShorted(shortLink);
//        verifyNoMoreInteractions(linkRepository);
//    }
//}
