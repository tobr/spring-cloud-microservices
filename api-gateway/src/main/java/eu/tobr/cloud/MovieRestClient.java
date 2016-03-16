package eu.tobr.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient("movie-service")
public interface MovieRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    Collection<Movie> getMovies();
}
