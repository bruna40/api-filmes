package br.com.fillms.projeto_java_ia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;



@Entity
@Table(name = "Films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "language", nullable = false)
    private String language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_user_id", nullable = false)
    private UserEntity addedBy;
}
