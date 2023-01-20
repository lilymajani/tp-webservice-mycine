package tp.mycine.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp.mycine.model.Genre;
import tp.mycine.repository.GenreRepository;
import tp.mycine.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    /**
     * Retrieves the genre list
     * @return the genre list
     */
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
