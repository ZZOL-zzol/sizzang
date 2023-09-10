package com.zzol.sizzang.review.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@ToString
@Getter
@Setter
@Entity
@Table(name = "review")
public class ReviewEntity {

        @Id
        @Column(name = "re_code")
        private int reCode;
        @Column(name = "user_code")
        private int userCode;
        @Column(name = "st_code")
        private int stCode;

        @Column(name = "re_title")
        private String reTitle;
        @Column(name = "reContent")
        private String reContent;
        @Column(name = "re_img")
        private String reImg;
        @Column(name = "re_score")
        private int reScore;

}
