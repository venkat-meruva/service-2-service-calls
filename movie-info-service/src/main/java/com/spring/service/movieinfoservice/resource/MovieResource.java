package com.spring.service.movieinfoservice.resource;

import com.spring.service.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieDetails(@PathVariable("movieId") String movieId){

        return new Movie(movieId,"Simhadri" );
    }
}
