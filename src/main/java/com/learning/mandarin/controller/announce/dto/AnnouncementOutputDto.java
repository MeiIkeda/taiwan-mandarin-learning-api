package com.learning.mandarin.controller.announce.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class AnnouncementOutputDto {

    public Integer current_page;
    public Integer total_page;

    public List<AnnouncementDto> announcementDtoList;
}
