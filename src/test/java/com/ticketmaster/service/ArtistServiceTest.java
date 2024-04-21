package com.ticketmaster.service;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ArtistServiceTest {

	@Mock
	private WebClient webClient;
	@Mock
	private WebClient.Builder webClientBuilder;
	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpec;
	@Mock
	private WebClient.ResponseSpec responseSpec;

	@InjectMocks
	private ArtistService artistService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(webClientBuilder.build()).thenReturn(webClient);
		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
	}

	@Test
	public void getArtistWithEvents_whenArtistExists_returnsArtist() {

		List<String> eventNames = new ArrayList<>();
		eventNames.add("Event Names");

		Artist[] artistList = new Artist[1];
		Artist mockArtist = new Artist();
		mockArtist.setId("1");
		mockArtist.setName("Test Artist");
		mockArtist.setEventName(eventNames);
		artistList[0] = mockArtist;

		List<Artist> artists = new ArrayList<>();
		artists.add(mockArtist);

		Event[] eventList = new Event[1];
		Event mockEvent = new Event();
		mockEvent.setId("1");
		mockEvent.setTitle("Test Event");
		mockEvent.setDateStatus("singleDate");
		mockEvent.setTimeZone("Europe/London");
		mockEvent.setArtists(artists);
		eventList[0] = mockEvent;

		when(responseSpec.bodyToMono(Artist[].class)).thenReturn(Mono.just(artistList));

		when(responseSpec.bodyToFlux(Event.class)).thenReturn(Flux.just(eventList));

		Mono<Artist> result = artistService.getArtistWithEvents("1");

		StepVerifier.create(result)
				.expectNextMatches(artist -> artist.getId().equals("1") && artist.getEventName().size() == 1)
				.verifyComplete();
	}

	@Test
	void getArtistWithEvents_whenArtistNotFound_returnsEmpty() {
		List<String> eventNames = new ArrayList<>();
		eventNames.add("Event Names");

		Artist[] artistList = new Artist[1];
		Artist mockArtist = new Artist();
		mockArtist.setId("1");
		mockArtist.setName("Test Artist");
		mockArtist.setEventName(eventNames);
		artistList[0] = mockArtist;

		List<Artist> artists = new ArrayList<>();
		artists.add(mockArtist);

		Event[] eventList = new Event[1];
		Event mockEvent = new Event();
		mockEvent.setId("1");
		mockEvent.setTitle("Test Event");
		mockEvent.setDateStatus("singleDate");
		mockEvent.setTimeZone("Europe/London");
		mockEvent.setArtists(artists);
		eventList[0] = mockEvent;

		when(responseSpec.bodyToMono(Artist[].class)).thenReturn(Mono.just(artistList));

		when(responseSpec.bodyToFlux(Event.class)).thenReturn(Flux.just(eventList));

		Mono<Artist> result = artistService.getArtistWithEvents("99");

		StepVerifier.create(result).expectError();
	}
}
