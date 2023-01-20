package tp.mycine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.mycine.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
