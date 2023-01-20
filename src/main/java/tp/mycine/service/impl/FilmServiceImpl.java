package tp.mycine.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tp.mycine.model.Film;
import tp.mycine.repository.FilmRepository;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.FilmService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {
    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final FilmRepository filmRepository;

    @Override
    public Film createFilm(Film film) {
        Optional<Film> filmOptional = filmRepository.findByTitleAndPosterPath(film.getTitle(), film.getPosterPath());
        if (filmOptional.isPresent()) {
            return filmOptional.get();
        } else {
            Film filmSave = filmRepository.save(film);
            log.debug("The film was successfully created");
            return filmSave;
        }
    }

    @Override
    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findAllById(id);
    }
}
