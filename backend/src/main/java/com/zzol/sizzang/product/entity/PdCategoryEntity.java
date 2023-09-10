package com.zzol.sizzang.product.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pd_Category")
@Entity
public class PdCategoryEntity {
    @Id
    @Column(name = "pc_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pcCode;

    @Column(name = "pc_name", nullable = false, columnDefinition = "VARCHAR(40)")
    private String pcName;
}
