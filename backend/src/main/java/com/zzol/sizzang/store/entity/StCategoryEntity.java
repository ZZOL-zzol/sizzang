package com.zzol.sizzang.store.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "St_Category")
@Entity
public class StCategoryEntity {
    @Id
    @Column(name = "sc_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scCode;

    @Column(name = "sc_name", nullable = false, columnDefinition = "VARCHAR(40)")
    private String scName;
}
