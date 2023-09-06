package com.zzol.sizzang.store.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "Store")
@Where(clause = "deleted_at is null")
@Entity
public class StoreEntity {
    @Id
    @Column(name = "st_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stCode;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="sc_code", referencedColumnName = "sc_code")
    StCategoryEntity stCategoryEntity;

    @Column(name = "st_name",nullable = false, columnDefinition = "VARCHAR(40)")
    private String stName;

    @Column(name = "st_phone", columnDefinition = "VARCHAR(15)")
    private String stPhone;

    @Column(name = "st_img",columnDefinition = "VARCHAR(200)")
    private String stImg;
    @Column(name = "st_account",columnDefinition = "VARCHAR(5)")
    private String stAccount;
    @Column(name = "st_account_holder",columnDefinition = "VARCHAR(40)")
    private String stAccountHolder;
    @Column(name = "st_intro",columnDefinition = "TEXT")
    private String stIntro;
    @Column(name = "st_time",columnDefinition = "VARCHAR(100)")
    private String stTime;

    @Column // 기본값 null
    private LocalDateTime deletedAt;

    /**
     * 게시글 삭제 시 deletedAt을 현재 시간으로 설정하기 위한 method
     */
    public void deleteTemplate() {
        this.deletedAt = LocalDateTime.now();
    }
}
