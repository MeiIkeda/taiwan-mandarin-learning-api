package com.learning.mandarin.controller.announce;


import com.learning.mandarin.controller.announce.dto.AnnouncementDto;
import com.learning.mandarin.controller.announce.dto.AnnouncementOutputDto;
import com.learning.mandarin.domain.Announcement;
import com.learning.mandarin.domain.AnnouncementRepository;
import com.learning.mandarin.domain.MandarinVocabulary;
import com.learning.mandarin.domain.MandarinVocabularyRepository;
import com.learning.mandarin.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/announcement")
public class AnnounceController {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @CrossOrigin
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public AnnouncementOutputDto getAllData(@PathVariable("page") Integer page ) throws ResourceNotFoundException {

        int pagingUnit = 10;
        PageRequest paging = PageRequest.of(page - 1, pagingUnit);
        List<Announcement> announcementList = announcementRepository.findAllByDeletedFlagOrderByIdDesc("0",paging);
        if (announcementList == null || announcementList.size() == 0) {
            log.error("AnnounceController.getAllData");
            throw new ResourceNotFoundException();
        }

        List<AnnouncementDto> dtoList = new ArrayList<AnnouncementDto>();
        for (Announcement announcement: announcementList) {
            AnnouncementDto dto = new AnnouncementDto();

            dto.id = announcement.id;
            dto.announcement = announcement.announcement;
            dto.announcementDate = new SimpleDateFormat("yyyy-MM-dd").format(announcement.announcementDate);
            dto.announcementUrl = announcement.announcementUrl;
            dto.deletedFlag = announcement.deletedFlag;
            dtoList.add(dto);
        }

        // ページング計算
        long count = announcementRepository.count();
        int total_page = (int) count / pagingUnit;
        if(count % pagingUnit != 0) {
            total_page = total_page + 1;
        }
        if(count == 0) {
            total_page = 1;
            page = 1;
        }

        AnnouncementOutputDto output = new AnnouncementOutputDto();
        output.announcementDtoList = dtoList;
        output.current_page = page;
        output.total_page = total_page;

        return output;
    }
}
