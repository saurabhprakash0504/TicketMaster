package com.ticketmaster.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;

@Service
public class ArtistService {

	@Autowired
	private WebClient.Builder webClientBuilder;

	public Mono<Artist> getArtistWithEvents(String artistId) {
		Mono<Artist> artistMono = webClientBuilder.build().get()
				.uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json").retrieve()
				.bodyToMono(Artist[].class).flatMapMany(Flux::fromArray)
				.filter(artist -> artist.getId().equals(artistId)).next();

		Mono<List<String>> eventsFlux = webClientBuilder.build().get()
				.uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json").retrieve()
				.bodyToFlux(Event.class)
				.filter(event -> event.getArtists().stream().anyMatch(artist -> artist.getId().equals(artistId)))
				.map(Event::getTitle).collect(Collectors.toList());

		return Mono.zip(artistMono, eventsFlux, (artist, events) -> {
			artist.setEventName(events);
			return artist;
		});
	}

}
