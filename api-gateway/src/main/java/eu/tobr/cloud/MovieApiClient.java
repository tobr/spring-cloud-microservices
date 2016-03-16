package eu.tobr.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/movies")
public class MovieApiClient {

    @Autowired
    private MovieRestClient movieClient;

    public Collection<String> movieTitlesFallback() {
        return Collections.emptyList();
    }

    @HystrixCommand(fallbackMethod = "movieTitlesFallback")
    @RequestMapping(value = "/titles", method = RequestMethod.GET, produces = "application/json")
    public Collection<String> movieTitles() {
        return movieClient.getMovies().stream().map(Movie::getTitle).collect(toList());
    }

}
