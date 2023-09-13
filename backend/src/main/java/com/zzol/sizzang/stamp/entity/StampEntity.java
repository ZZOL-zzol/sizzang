package com.zzol.sizzang.stamp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "stamp")
public class StampEntity {

    @Id
    @Column(name = "stamp_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stampCode;
    @Column(name = "user_code")
    private int userCode;
    @Column(name = "region_code")
    private int regionCode;
    @Column(name = "stamp_count")
    private int stampCount;

}
