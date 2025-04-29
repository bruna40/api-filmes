package br.com.fillms.projeto_java_ia.controller;

import java.util.List;

import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillms.projeto_java_ia.model.FilmEntity;

import br.com.fillms.projeto_java_ia.security.CustomUserDetails;
import br.com.fillms.projeto_java_ia.service.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;

    }
    
    
    @PostMapping("/create")
    public FilmEntity createFilm(@RequestBody FilmEntity film) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        String userId = userDetails.getId().toString();
    
        return filmService.addFilm(userId, film);
    }

    @PutMapping("/edit/{filmId}")
    public FilmEntity editFilm(@RequestBody FilmEntity film, @PathVariable String filmId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        String userId = userDetails.getId().toString();
        
        return filmService.editFilm(userId, filmId, film);
    }

    @GetMapping("/all")
    public List<FilmEntity> getAllFilms() {
        return filmService.getAllFilms();
    }

    @DeleteMapping("/delete/{filmId}")
    public void deleteFilm(@PathVariable String filmId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        String userId = userDetails.getId().toString();

       filmService.deleteFilm(userId, filmId);
    }
}
