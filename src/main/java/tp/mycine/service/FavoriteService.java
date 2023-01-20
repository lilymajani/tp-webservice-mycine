package tp.mycine.service;

import tp.mycine.model.Favorite;
import tp.mycine.model.Film;

import java.util.List;

public interface FavoriteService {

    Favorite createFavorite(Long filmId);

    List<Film> getFavorites();
}
