package com.zzol.sizzang.product.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@ToString
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "purchase_detail")
@Entity
public class PurchaseDetailEntity {
    @Id
    @Column(name = "detail_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailCode;

    @Column(name = "pu_Code")
    private Long puCode;

    @Column(name = "pu_cnt")
    private int puCnt;

    @Column(name = "pr_price")
    private int prPrice;

    @Column(name = "pr_Name")
    private String prName;
}
