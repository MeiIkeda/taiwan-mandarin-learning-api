package com.learning.mandarin.domain;

import com.learning.mandarin.controller.vocabulary.dto.VocabularyExampleRawDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VocabularyExampleRowMapper implements RowMapper<VocabularyExampleRawDto> {

    private static final String VOCABULARY_ID = "mandarin_vocabulary.id";
    private static final String SIMPLIFIED = "mandarin_vocabulary.simplified";
    private static final String TRADITIONAL = "mandarin_vocabulary.traditional";
    private static final String PINYIN = "mandarin_vocabulary.pinyin";
    private static final String BOPOMOFO = "mandarin_vocabulary.bopomofo";
    private static final String MEANING = "mandarin_vocabulary.meaning";
    private static final String LEVEL = "mandarin_vocabulary.level";
    private static final String CATEGORY = "mandarin_vocabulary.category";
    private static final String EXAMPLE_ID = "mandarin_example.id";
    private static final String SENTENCE = "mandarin_example.sentence";
    private static final String TRANSLATION = "mandarin_example.translation";

    @Override
    public VocabularyExampleRawDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        VocabularyExampleRawDto dto = new VocabularyExampleRawDto();

        dto.setVocabularyId(rs.getLong(VOCABULARY_ID));
        dto.setSimplified(rs.getString(SIMPLIFIED));
        dto.setTraditional(rs.getString(TRADITIONAL));
        dto.setPinyin(rs.getString(PINYIN));
        dto.setBopomofo(rs.getString(BOPOMOFO));
        dto.setMeaning(rs.getString(MEANING));
        dto.setLevel(rs.getInt(LEVEL));
        dto.setCategory(rs.getInt(CATEGORY));

        dto.setExampleId(rs.getInt(EXAMPLE_ID));
        dto.setSentence(rs.getString(SENTENCE));
        dto.setTranslation(rs.getString(TRANSLATION));

        return dto;
    }
}
