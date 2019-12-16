package com.learning.mandarin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mandarin_vocabulary")
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class MandarinVocabulary {

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "simplified")
    public String simplified;

    @Column(name = "traditional")
    public String traditional;

    @Column(name = "pinyin")
    public String pinyin;

    @Column(name = "bopomofo")
    public String bopomofo;

    @Column(name = "meaning")
    public String meaning;

    @Column(name = "hsk_level")
    public Integer hskLevel;

    @Column(name = "level")
    public Integer level;

    @Column(name = "category")
    public Integer category;

    @Column(name = "deleted_flag")
    public String deletedFlag = "0";

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date updatedAt;

    @CreatedDate
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    public Date createdAt;


}
