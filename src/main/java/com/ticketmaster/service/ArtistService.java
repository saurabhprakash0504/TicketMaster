package com.ticketmaster.service;

import java.util.List;

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
        Mono<Artist> artistMono = webClientBuilder.build()
            .get()
            .uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json")
            .retrieve()
            .bodyToMono(Artist[].class)
            .flatMapMany(Flux::fromArray)
            .filter(artist -> artist.getId().equals(artistId))
            .next();

       /* Mono<List<Event>> eventsMono = webClientBuilder.build()
            .get()
            .uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json")
            .retrieve()
            .bodyToMono(Event[].class)
            .flatMapMany(Flux::fromArray)
            //.filter(event -> event.getArtistIds().contains(artistId))
           // .filter(event -> event.getArtists().stream().map(Dummy::artistId))
            .filter(event -> event.getArtists().forEach(data -> data.getId().equals(artistId)))
            .collectList();*/
           
        Mono<List<Event>> eventsFlux = webClientBuilder.build().get()
                .uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json")
                .retrieve()
                .bodyToFlux(Event.class)
                .filter(event -> event.getArtists().stream().anyMatch(artist -> artist.getId().equals(artistId))).collectList().log();
        
 


       /* return artistMono
                .flatMap(artist -> eventsFlux.collectList().map(events -> {
                    artist.setEvents(events);
                    return artist;
                }));*/
        
        return Mono.zip(artistMono, eventsFlux, (artist, events) -> {
            artist.setEvents(events);
            return artist;
        }); 
    }
    
  /*  public Mono<Artist> getArtistWithEvents(String artistId) {
        Mono<Artist> artistMono = webClientBuilder.build().get()
            .uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json")
            .retrieve()
            .bodyToFlux(Artist.class)
            .filter(artist -> artist.getId().equals(artistId))
            .single();

        Flux<Event> eventsFlux = webClientBuilder.build().get()
            .uri("https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json")
            .retrieve()
            .bodyToFlux(Event.class)
            .filter(event -> event.getArtists().stream().filter(data -> data.getId().contains(artistId)))
            .collectList();

        return artistMono
            .flatMap(artist -> eventsFlux.collectList().map(events -> {
                artist.setEvents(events); // Assuming Artist has a List<Event> field
                return artist;
            }));
    }*/
}