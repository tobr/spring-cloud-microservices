package eu.tobr.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MovieInitialization implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void run(final String... args) throws Exception {
        Stream.of("Die Hard", "Cloudy with a chance of meatballs")
                .forEach(x -> movieRepository.save(new Movie(x)));

        movieRepository.findAll().forEach(System.out::println);
    }
}
