package com.example.backend.repository;

import com.example.backend.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from Section s where s.title = :#{#title}")
    List<Section> findAllByTitle(@Param("title") String title);

    @Query("select s from Section s where s.title = :#{#title}")
    Section findByTitle(@Param("title") String title);
}
