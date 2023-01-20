package tp.mycine.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tp.mycine.model.Genre;
import tp.mycine.service.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/save")
    public ResponseEntity<Void> saveGenres() {
        String uri = "https://api.themoviedb.org/3/genre/movie/list?api_key=7c522565b2f68f9343176988bce19cfe&language=fr";
        RestTemplate restTemplate = new RestTemplate();
        String genreResponse = restTemplate.getForObject(uri, String.class);
        //ResponseDTO<Genre> genreR = gson.fromJson(genreResponse, ResponseDTO.class);
        //ResponseDTO<Genre> genreR = (ResponseDTO<Genre>) genreResponse;
        //ResponseDTO<Genre> genreResponse = restTemplate.getForObject(uri, ResponseDTO.class);
        //List<Genre> genres = genreResponse.getValue();

        return ResponseEntity.ok().build();
    }
}
