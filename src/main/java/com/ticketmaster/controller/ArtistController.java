package com.ticketmaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ticketmaster.model.Artist;
import com.ticketmaster.service.ArtistService;

@RestController
public class ArtistController {

	   @Autowired
	    private ArtistService artistService;

	    @GetMapping("/artists/{artistId}")
	    public Mono<ResponseEntity<Artist>> getArtistWithEvents(@PathVariable("artistId") String artistId) {
	        return artistService.getArtistWithEvents(artistId)
	                .map(artist -> ResponseEntity.ok(artist))
	                .defaultIfEmpty(ResponseEntity.notFound().build());
	    }
	
}
