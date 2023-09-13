package com.zzol.sizzang.review.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {

        @Id
        @Column(name = "re_code")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long reCode;
        @Column(name = "user_code")
        private long userCode;
        @Column(name = "st_code")
        private long stCode;
        @Column(name = "pu_code")
        private long puCode;

        @Column(name = "re_title")
        private String reTitle;
        @Column(name = "reContent")
        private String reContent;
        @Column(name = "re_img")
        private String reImg;
        @Column(name = "re_score")
        private int reScore;

}
