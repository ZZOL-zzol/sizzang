package com.zzol.sizzang.review.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {

        @Id
        @Column(name = "re_code")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long reCode;
        @Column(name = "user_code")
        private Long userCode;
        @Column(name = "st_code")
        private Long stCode;
        @Column(name = "pu_code")
        private Long puCode;

        @Column(name = "re_title")
        private String reTitle;
        @Column(name = "reContent")
        private String reContent;
        @Column(name = "re_img")
        private String reImg;
        @Column(name = "re_score")
        private int reScore;

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP) // 옵션: 시간까지 저장할 경우 필요
        @Column(name = "create_time")
        private Date createTime;

}
