package com.zzol.sizzang.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zzol.sizzang.review.repository.ReviewRepository;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import com.zzol.sizzang.store.entity.StoreEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.zzol.sizzang.store.entity.QStoreEntity.storeEntity;


@Slf4j
@Repository
public class QStoreRepositoryImpl implements QStoreRepository{

    private final JPAQueryFactory queryFactory;
    private final ReviewRepository reviewRepository;

    public QStoreRepositoryImpl(EntityManager em, ReviewRepository reviewRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.reviewRepository = reviewRepository;
    }

    /**
     * 게시글 검색어로 검색 쿼리 템플릿
     *
     * @param findByConditionGetReq : 제목 검색어
     */
    @Override
    public List<StoreFindRes> findByCondition(FindByConditionGetReq findByConditionGetReq) {

        log.info("StoreRepositoryImpl_findByCondition_start: "
                + findByConditionGetReq.toString());
        return queryFactory
                .select(Projections.constructor(StoreFindRes.class,
                        storeEntity.stCode.as("stCode"),
                        storeEntity.stName.as("stName"),
                        storeEntity.stImg.as("stImg"),
                        storeEntity.stLatitude.as("stLatitude"),
                        storeEntity.stLongtitude.as("stLongtitude"),
                        storeEntity.stIntro.as("stIntro"),
                        storeEntity.marketEntity.as("mkCode"),
                        storeEntity.stCategoryEntity.as("pcName")))
                .from(storeEntity)
                .where(keywordSearch(findByConditionGetReq.getKeyword()))
                .fetch();
    }


    /**
     * keyword 로 제목 검색
     * TODO : 검색
     */
    private BooleanExpression keywordSearch(String keyword){
        return keyword == null ? null : storeEntity.stName.contains(keyword);
    }
    // TODO : 물품으로 검색
}
