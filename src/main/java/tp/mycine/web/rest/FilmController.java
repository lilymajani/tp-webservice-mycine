package tp.mycine.web.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.mycine.model.Film;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.FilmService;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final FilmService filmService;

    /**
     * Create a user account
     * @param film Film to create
     * @return Film created
     */
    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        log.debug("Request for the creation of a film with the title {}", film.getTitle());
        return ResponseEntity.ok(filmService.createFilm(film));
    }
}
