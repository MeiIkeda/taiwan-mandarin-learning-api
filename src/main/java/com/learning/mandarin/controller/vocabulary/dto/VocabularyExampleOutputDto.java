package com.learning.mandarin.controller.vocabulary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabularyExampleOutputDto {

    public Long vocabularyId;

    public String simplified;

    public String traditional;

    public String pinyin;

    public String bopomofo;

    public String meaning;

    public Integer level;

    public Integer category;

    public List<ExampleDto> exampleDtoList;

}
