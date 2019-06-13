package com.spring.service.ratingsdataservice.resource;

import com.spring.service.ratingsdataservice.model.Rating;
import com.spring.service.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId, " 4");
    }

    @RequestMapping("/users/{userId}")
    public UserRating getAllRatings(@PathVariable("userId")String userId){

        List<Rating> ratings = Arrays.asList(
                new Rating("1234","4.5"),
                new Rating("4567","4.9")
        );
        UserRating userRatings = new UserRating();
        userRatings.setUserRating(ratings);
        return userRatings;
    }

}
