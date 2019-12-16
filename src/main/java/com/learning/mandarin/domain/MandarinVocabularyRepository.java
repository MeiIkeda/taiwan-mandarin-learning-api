package com.learning.mandarin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MandarinVocabularyRepository extends JpaRepository<MandarinVocabulary, Long> {

    public List<MandarinVocabulary> findAllByLevel(Integer level);

    @Query(value = "select * from mandarin_vocabulary m where m.traditional LIKE %:word% " +
            "Or m.simplified LIKE %:word% Or m.meaning LIKE %:word% ",
            nativeQuery = true)
    public List<MandarinVocabulary> findWord(String word);
}
