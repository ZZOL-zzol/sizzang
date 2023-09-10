package com.zzol.sizzang.product.entity;

import com.zzol.sizzang.store.entity.StoreEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "Product")
@Where(clause = "deleted_at is null")
@Entity
public class ProductEntity {
    @Id
    @Column(name = "pd_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pdCode;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="tag_code", referencedColumnName = "tag_code")
    PrTagEntity prTagEntity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="st_code", referencedColumnName = "st_code")
    StoreEntity storeEntity;

    @Column(name = "pd_name", columnDefinition = "VARCHAR(20)")
    private String pdName;

    @Column(name = "pd_cost", columnDefinition = "Integer")
    private int pdCost;

    @Column(name = "pd_intro", columnDefinition = "Text")
    private String pdIntro;

//    @Column(name = "pd_img",columnDefinition = "VARCHAR(200)")
//    private String pdImg;

    @Column // 기본값 null
    private LocalDateTime deletedAt;

    /**
     * 게시글 삭제 시 deletedAt을 현재 시간으로 설정하기 위한 method
     */
    public void deleteTemplate() {
        this.deletedAt = LocalDateTime.now();
    }

    public void modifyProduct(StoreEntity storeEntity, PrTagEntity prTagEntity, String pdName, String pdIntro, int pdCost){
        this.storeEntity = storeEntity;
        this.prTagEntity =prTagEntity;
        this.pdName = pdName;
        this.pdCost = pdCost;
        this.pdIntro = pdIntro;
    }
}
