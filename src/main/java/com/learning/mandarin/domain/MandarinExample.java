package com.learning.mandarin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mandarin_example")
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public class MandarinExample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "vocabulary_id")
    public Integer vocabularyId;

    @Column(name = "sentence")
    public String sentence;

    @Column(name = "translation")
    public String translation;

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
