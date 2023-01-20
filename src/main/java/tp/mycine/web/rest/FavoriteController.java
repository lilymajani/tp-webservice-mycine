package tp.mycine.web.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.mycine.model.Favorite;
import tp.mycine.model.Film;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final FavoriteService favoriteService;

    /**
     * request to retrieve the user favorites
     * @return List of favorites films
     */
    @GetMapping
    public ResponseEntity<List<Film>> getFavorites() {
        log.debug("request to retrieve the user favorites");
        return ResponseEntity.ok(favoriteService.getFavorites());
    }

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Long filmId) {
        log.debug("request to retrieve add a new favorite");
        return ResponseEntity.ok(favoriteService.createFavorite(filmId));
    }
}
