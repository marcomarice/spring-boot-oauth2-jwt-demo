package it.bitrock.springbootoauth2jwtdemo.repository;

import it.bitrock.springbootoauth2jwtdemo.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findAll();

    boolean existsById(Long curriculumId);

    Curriculum getCurriculumById(Long curriculumId);

    void deleteById(Long curriculumId);
}
