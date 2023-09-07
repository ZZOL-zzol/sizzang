package com.zzol.sizzang.stamp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity {

    @Id
    @Column(name = "region_code")
    private int regionCode;
    @Column(name = "region_name_first")
    private String regionNameFirst;
    @Column(name = "region_name_second")
    private String regionNameSecond;
    @Column(name = "stamp_url")
    private String stampUrl;
}


