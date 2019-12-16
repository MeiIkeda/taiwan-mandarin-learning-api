package com.learning.mandarin.controller.announce.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnnouncementDto {

    public Integer id;
    public String announcement;
    public String announcementDate;
    public String announcementUrl;
    public String deletedFlag;
}
