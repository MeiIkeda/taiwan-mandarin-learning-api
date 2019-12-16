package com.learning.mandarin.controller.vocabulary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExampleDto {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("vocabularyId")
    public Long vocabularyId;
    @JsonProperty("sentence")
    public String sentence;
    @JsonProperty("translation")
    public String translation;
}
