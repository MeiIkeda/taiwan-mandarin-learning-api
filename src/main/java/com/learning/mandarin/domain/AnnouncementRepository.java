package com.learning.mandarin.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    public List<Announcement> findAllByDeletedFlagOrderByIdDesc(String deletedFlag, Pageable pageable);
}
