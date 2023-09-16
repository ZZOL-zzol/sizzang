package com.zzol.sizzang.product.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "Purchase")
@Entity
public class PurchaseEntity {

    @Id
    @Column(name = "pu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long puCode;

    @Column(name = "re_registed")
    private Boolean reRegisted;

    @Column(name = "st_code")
    private Long stCode;

    @Column(name = "pu_cost")
    private int puCost;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP) // 옵션: 시간까지 저장할 경우 필요
    @Column(name = "pu_date")
    private Date puDate;

    @Column(name = "account_number")
    private String accountNumber;


}
