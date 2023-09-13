package com.zzol.sizzang.store.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_Category")
@Entity
public class StCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stCode;

    private String stName;
}
