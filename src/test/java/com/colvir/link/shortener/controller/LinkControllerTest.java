package com.colvir.link.shortener.controller;

import com.colvir.link.shortener.dto.GenerateLinkRequest;
import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.service.LinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.colvir.link.shortener.generator.LinkDtoGenerator.generateLinkRequestBuilder;
import static com.colvir.link.shortener.generator.LinkDtoGenerator.generateLinkResponseBuilder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LinkController.class)
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private LinkController linkController;

    @MockBean
    private LinkService linkService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void generateLink_success() throws Exception {

        GenerateLinkRequest req = generateLinkRequestBuilder()
                .build();

        String stringReq = objectMapper.writeValueAsString(req);

        GenerateLinkResponse resp = generateLinkResponseBuilder()
                .build();

        when(linkService.generateShortLink(req)).thenReturn(resp);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/link/generate") //send
                        .content(stringReq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shorted").value(resp.getShorted()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original").value(resp.getOriginal()));

        verify(linkService).generateShortLink(req);
        verifyNoMoreInteractions(linkService);
    }
}
