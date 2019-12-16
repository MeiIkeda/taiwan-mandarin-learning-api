package com.learning.mandarin.controller.vocabulary;


import com.learning.mandarin.controller.vocabulary.dto.ExampleDto;
import com.learning.mandarin.controller.vocabulary.dto.VocabularyExampleOutputDto;
import com.learning.mandarin.controller.vocabulary.dto.VocabularyExampleRawDto;
import com.learning.mandarin.domain.MandarinVocabulary;
import com.learning.mandarin.domain.MandarinVocabularyRepository;
import com.learning.mandarin.domain.VocabularyExampleRepository;
import com.learning.mandarin.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/vocabulary")
public class VocabularyController {

    @Autowired
    private MandarinVocabularyRepository mandarinVocabularyRepository;

    @Autowired
    private VocabularyExampleRepository vocabularyExampleRepository;

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<VocabularyExampleRawDto> getAllData() throws ResourceNotFoundException{
        List<VocabularyExampleRawDto> mandarinVocabularyList = vocabularyExampleRepository.findAllByDeletedFlag();
        if (mandarinVocabularyList == null || mandarinVocabularyList.size() == 0) {
            log.error("VocabularyController.getAllData");
            throw new ResourceNotFoundException();
        }
        return mandarinVocabularyList;
    }

    @CrossOrigin
    @RequestMapping(value = "/all/vocabulary", method = RequestMethod.GET)
    public List<MandarinVocabulary> getAllVocabularyData() throws ResourceNotFoundException{
        List<MandarinVocabulary> mandarinVocabularyList = mandarinVocabularyRepository.findAll();
        if (mandarinVocabularyList == null || mandarinVocabularyList.size() == 0) {
            log.error("VocabularyController.getAllVocabularyData");
            throw new ResourceNotFoundException();
        }
        return mandarinVocabularyList;
    }

    @CrossOrigin
    @RequestMapping(value = "/old/{level}", method = RequestMethod.GET)
    public List<MandarinVocabulary> getLevelDataOld(@PathVariable("level") Integer level) throws ResourceNotFoundException {
        List<MandarinVocabulary> mandarinVocabularyList = mandarinVocabularyRepository.findAllByLevel(level);
        if (mandarinVocabularyList == null || mandarinVocabularyList.size() == 0) {
            log.error("VocabularyController.getLevelDataOld");
            throw new ResourceNotFoundException();
        }
        return mandarinVocabularyList;
    }

    @CrossOrigin
    @RequestMapping(value = "/{level}", method = RequestMethod.GET)
    public List<VocabularyExampleOutputDto> getLevelData(@PathVariable("level") Integer level) throws ResourceNotFoundException {
        List<VocabularyExampleRawDto> mandarinVocabularyList = vocabularyExampleRepository.findAllByLevelAndDeletedFlag(level);
        if (mandarinVocabularyList == null || mandarinVocabularyList.size() == 0) {
            log.error("VocabularyController.getLevelData");
            throw new ResourceNotFoundException();
        }

        long previousVocabularyId = 0;
        List<VocabularyExampleOutputDto> outputDtoList = new ArrayList<VocabularyExampleOutputDto>();
        for (VocabularyExampleRawDto rawDto: mandarinVocabularyList) {
            if (rawDto.vocabularyId != previousVocabularyId) {
                VocabularyExampleOutputDto outputDto = new VocabularyExampleOutputDto();
                outputDto.setVocabularyId(rawDto.vocabularyId);
                outputDto.setSimplified(rawDto.simplified);
                outputDto.setTraditional(rawDto.traditional);
                outputDto.setPinyin(rawDto.pinyin);
                outputDto.setBopomofo(rawDto.bopomofo);
                outputDto.setMeaning(rawDto.meaning);
                outputDto.setLevel(rawDto.level);
                outputDto.setCategory(rawDto.category);
                List<ExampleDto> exampleDtoList = new ArrayList<ExampleDto>();
                if (rawDto.exampleId != 0) {
                    ExampleDto exampleDto = new ExampleDto();
                    exampleDto.setId(rawDto.exampleId);
                    exampleDto.setVocabularyId(rawDto.vocabularyId);
                    exampleDto.setSentence(rawDto.sentence);
                    exampleDto.setTranslation(rawDto.translation);
                    exampleDtoList.add(exampleDto);
                }
                outputDto.setExampleDtoList(exampleDtoList);
                outputDtoList.add(outputDto);
            } else {
                VocabularyExampleOutputDto outputDto = outputDtoList.get((int)previousVocabularyId);
                List<ExampleDto> exampleDtoList = outputDto.getExampleDtoList();
                if (rawDto.exampleId != 0) {
                    ExampleDto exampleDto = new ExampleDto();
                    exampleDto.setId(rawDto.exampleId);
                    exampleDto.setVocabularyId(rawDto.vocabularyId);
                    exampleDto.setSentence(rawDto.sentence);
                    exampleDto.setTranslation(rawDto.translation);
                    exampleDtoList.add(exampleDto);
                }
            }

            previousVocabularyId = rawDto.vocabularyId;
        }
        return outputDtoList;
    }

    @CrossOrigin
    @RequestMapping(value = "/search/{word}", method = RequestMethod.GET)
    public List<MandarinVocabulary> searchWordData(@PathVariable("word") String word) throws ResourceNotFoundException {
        List<MandarinVocabulary> mandarinVocabularyList = mandarinVocabularyRepository.findWord(word);
        if (mandarinVocabularyList == null || mandarinVocabularyList.size() == 0) {
            log.error("VocabularyController.searchWordData");
            throw new ResourceNotFoundException();
        }
        return mandarinVocabularyList;
    }
}
