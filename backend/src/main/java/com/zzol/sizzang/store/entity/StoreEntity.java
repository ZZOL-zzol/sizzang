package com.zzol.sizzang.store.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Store")
@Entity
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stCode;

    private String stName;
    private String stPhone;
    private String stImg;
    private String stAccount;
    private String stAccountHolder;
    private String stIntro;
    private String stTime;
}
