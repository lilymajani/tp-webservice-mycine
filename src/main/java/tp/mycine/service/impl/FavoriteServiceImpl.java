package tp.mycine.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tp.mycine.model.Favorite;
import tp.mycine.model.Film;
import tp.mycine.model.User;
import tp.mycine.repository.FavoriteRepository;
import tp.mycine.security.jwt.JwtUtils;
import tp.mycine.service.FavoriteService;
import tp.mycine.service.FilmService;
import tp.mycine.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private final UserService userService;

    private final FilmService filmService;

    private final FavoriteRepository favoriteRepository;

    @Override
    public Favorite createFavorite(Long filmId) {
        Optional<User> currentUser = userService.getConnectedUser();
        if (currentUser.isPresent()) {
            Optional<Film> filmOptional = filmService.getFilmById(filmId);
            if (filmOptional.isPresent()) {
                Favorite favoriteToSave = new Favorite();
                favoriteToSave.setUser(currentUser.get());
                favoriteToSave.setFilm(filmOptional.get());
                Favorite favorite = favoriteRepository.save(favoriteToSave);
                log.debug("The favorite has been successfully added");
                return favorite;
            } else {
                log.debug("Unable to add a favorite: film not found");
            }
        } else {
            log.debug("Unable to add a favorite: no user logged in");
        }
        return null;
    }

    @Override
    public List<Film> getFavorites() {
        Optional<User> currentUser = userService.getConnectedUser();
        if (currentUser.isPresent()) {
            return favoriteRepository.findByUserId(currentUser.get().getId())
                    .stream().map(Favorite::getFilm).collect(Collectors.toList());
        }
        log.debug("Unable to get films favorite: no user logged in");
        return null;
    }
}
