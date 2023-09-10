package com.zzol.sizzang.product.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "Pr_Tag")
@Where(clause = "deleted_at is null")
@Entity
public class PrTagEntity {
    @Id
    @Column(name = "tag_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagCode;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="pc_code", referencedColumnName = "pc_code")
    PdCategoryEntity pdCategoryEntity;

    @Column(name = "tag_name", nullable = false, columnDefinition = "VARCHAR(40)")
    private String tagName;

    @Column(name = "tag_unit", nullable = false, columnDefinition = "VARCHAR(40)")
    private String tagUnit;
}
