package com.learning.mandarin.domain;

import com.learning.mandarin.controller.vocabulary.dto.VocabularyExampleOutputDto;
import com.learning.mandarin.controller.vocabulary.dto.VocabularyExampleRawDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VocabularyExampleRepository {

    private NamedParameterJdbcTemplate template;

    public VocabularyExampleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public List<VocabularyExampleRawDto> findAllByDeletedFlag() {
        String sql = "select distinct * from mandarin_vocabulary left outer join mandarin_example " +
                "on mandarin_vocabulary.id=mandarin_example.vocabulary_id and mandarin_example.deleted_flag = '0' " +
                "where mandarin_vocabulary.deleted_flag = '0' ";

        return template.query(sql, new VocabularyExampleRowMapper());
    }

    public List<VocabularyExampleRawDto> findAllByLevelAndDeletedFlag(Integer level) {
        String sql = "select distinct * from mandarin_vocabulary left outer join mandarin_example " +
                "on mandarin_vocabulary.id=mandarin_example.vocabulary_id and mandarin_example.deleted_flag = '0' " +
                "where mandarin_vocabulary.deleted_flag = '0' and mandarin_vocabulary.level = :level";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("level", level);

        return template.query(sql, param, new VocabularyExampleRowMapper());
    }


}
