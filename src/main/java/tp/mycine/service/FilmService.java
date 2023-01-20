package tp.mycine.service;

import tp.mycine.model.Film;

import java.util.Optional;

public interface FilmService {

    Film createFilm(Film film);

    Optional<Film> getFilmById(Long id);
}
