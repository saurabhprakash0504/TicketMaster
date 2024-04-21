package com.ticketmaster.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.mockito.Mockito.when;
import reactor.core.publisher.Mono;

@WebFluxTest(ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private com.ticketmaster.service.ArtistService artistService;

    @BeforeEach
    public void setUp() {
        com.ticketmaster.model.Artist mockArtist = new com.ticketmaster.model.Artist();
        mockArtist.setId("1");
        mockArtist.setName("Test Artist");

        when(artistService.getArtistWithEvents("1")).thenReturn(Mono.just(mockArtist));
         
        when(artistService.getArtistWithEvents("189"))
        .thenReturn(Mono.error(new WebClientResponseException(
                500, "Server Error", HttpHeaders.EMPTY, null, null)));
  
       
    }

    @Test
    public void getArtistWithEvents_whenArtistExists_returnsArtist() {
        webTestClient.get().uri("/artists/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo("1")
            .jsonPath("$.name").isEqualTo("Test Artist");
    }
    
    @Test
    public void getArtistWithEvents_whenServerIsDown_returnsServerError() {
        webTestClient.get().uri("/artists/189")
        .exchange()
        .expectStatus().is5xxServerError();
    }

}
