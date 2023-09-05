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
    @Column(name = "st_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stCode;

    @Column(name = "st_name", nullable = false, columnDefinition = "VARCHAR(40)")
    private String stName;
}
