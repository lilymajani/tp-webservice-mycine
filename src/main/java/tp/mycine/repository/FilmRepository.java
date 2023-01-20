package tp.mycine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.mycine.model.Film;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByTitleAndPosterPath(String title, String posterPath);

    Optional<Film> findAllById(Long id);
}
