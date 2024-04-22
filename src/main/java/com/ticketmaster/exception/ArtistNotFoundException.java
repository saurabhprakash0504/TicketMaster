package com.ticketmaster.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String artistId) {
        super("Artist not found with ID: " + artistId);
    }
}

