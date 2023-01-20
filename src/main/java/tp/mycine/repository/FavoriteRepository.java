package tp.mycine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.mycine.model.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);
}
