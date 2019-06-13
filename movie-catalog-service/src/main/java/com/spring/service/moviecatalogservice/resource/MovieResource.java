package com.spring.service.moviecatalogservice.resource;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.spring.service.moviecatalogservice.model.CatalogItem;
import com.spring.service.moviecatalogservice.model.Movie;
import com.spring.service.moviecatalogservice.model.Rating;
import com.spring.service.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getMovieDetails(@PathVariable("userId") String userId){

      UserRating ratings= restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+ userId,UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {

            Movie movie=  restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "Action Movie",  rating.getRating());
        })
                .collect(Collectors.toList());
            }

    /**
     * WebClient calls as RestTemplate is depricated

    Movie movie = webClientBuilder.build()
            .get()
            .uri("http://localhost:8081/movie/\"+rating.getMovieId()")
            .retrieve()
            .bodyToMono(Movie.class)
            .block();
     */
}
