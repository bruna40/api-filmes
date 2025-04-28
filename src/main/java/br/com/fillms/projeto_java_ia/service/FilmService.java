package br.com.fillms.projeto_java_ia.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.fillms.projeto_java_ia.enums.RoleEnum;
import br.com.fillms.projeto_java_ia.model.FilmEntity;
import br.com.fillms.projeto_java_ia.model.UserEntity;
import br.com.fillms.projeto_java_ia.repository.FilmRepository;

@Service
public class FilmService {
    

    private final FilmRepository filmRepository;

    private final UserService userService;

    @Autowired
    public FilmService(FilmRepository filmRepository, UserService userService) {
        this.filmRepository = filmRepository;
        this.userService = userService;
    }


   public FilmEntity addFilm(String userId, FilmEntity film) {
        UserEntity user = userService.getUserById(userId);
        if( user.getRole() != RoleEnum.ADMIN) {
            throw new RuntimeException("User is not an admin");
        }
        film.setUserId(user.getId());
        return filmRepository.save(film);
   }

   @Cacheable(value = "films")
   public List<FilmEntity> getAllFilms() {
    return filmRepository.findAll();
   }

}
