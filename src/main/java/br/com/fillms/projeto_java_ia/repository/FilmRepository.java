package br.com.fillms.projeto_java_ia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fillms.projeto_java_ia.model.FilmEntity;


@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    // // Custom query methods can be defined here if needed
    // // For example, find by title or release year
    // List<FilmEntity> findByTitle(String title);
    // List<FilmEntity> findByReleaseYear(Integer releaseYear);
    
}
