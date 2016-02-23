package eu.tobr.cloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/movies")
public class MovieApiClient {

    @Autowired
    private RestTemplate restTemplate;

    public Collection<String> movieTitlesFallback() {
        return Collections.emptyList();
    }

    @HystrixCommand(fallbackMethod = "movieTitlesFallback")
    @RequestMapping(value = "/titles", method = RequestMethod.GET, produces = "application/json")
    public Collection<String> movieTitles() {
        return restTemplate.exchange("http://movie-service/movies", HttpMethod.GET, null,
                new ParameterizedTypeReference<Resources<ClientMovie>>() { })
                .getBody()
                .getContent()
                .stream()
                .map(ClientMovie::getTitle)
                .collect(toList());
    }

}
