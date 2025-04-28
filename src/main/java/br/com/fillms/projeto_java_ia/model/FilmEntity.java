package br.com.fillms.projeto_java_ia.model;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Locale.LanguageRange;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.fillms.projeto_java_ia.enums.LanguageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;



@Entity
@Table(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "film_id")
    private UUID filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "language", nullable = false)
    private LanguageEnum language;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
