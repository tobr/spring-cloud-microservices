package eu.tobr.cloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

@RepositoryRestResource
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @RestResource (path = "by-title")
    Collection<Movie> findByTitle(@Param("title") final String title);
}
