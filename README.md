"# TicketMaster" 

# Software used

Java 17
Spring Boot 
Gradle 8.7


# Start the application

command -> gradle bootRun

# Endpoints

Request -> GET - http://localhost:8080/artists/22

Response -> 

{
    "id": "22",
    "name": "Colosseum",
    "imgSrc": "//some-base-url/colosseum.jpg",
    "url": "/colosseum-tickets/artist/22",
    "rank": 2,
    "eventName": [
        "Blues In Space",
        "A festival Live",
        "Harisson Live",
        "Huge Live"
    ]
}

Request -> GET - http://localhost:8080/artists/2289

Response -> 

404 Not Found

