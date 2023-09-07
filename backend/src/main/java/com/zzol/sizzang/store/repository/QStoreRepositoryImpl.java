package com.zzol.sizzang.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zzol.sizzang.store.dto.request.FindByConditionGetReq;
import com.zzol.sizzang.store.dto.response.StoreFindRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.zzol.sizzang.store.entity.QStoreEntity.storeEntity;


@Slf4j
@Repository
public class QStoreRepositoryImpl implements QStoreRepository{

    private final JPAQueryFactory queryFactory;

    public QStoreRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 게시글 검색어로 검색 쿼리 템플릿
     *
     * @param findByConditionGetReq : 제목 검색어
     */
    @Override
    public List<StoreFindRes> findByCondition(FindByConditionGetReq findByConditionGetReq) {

        log.info("QTemplateArticleRepositoryImpl_findByConditionTemplate_start: "
                + findByConditionGetReq.toString());

        return queryFactory
                .select(Projections.constructor(StoreFindRes.class,
                        storeEntity.stName.as("stName"),
                        storeEntity.stImg.as("stImg"),
                        storeEntity.stIntro.as("stIntro")))
                .from(storeEntity)
                .where(keywordSearch(findByConditionGetReq.getKeyword()))
                .fetch();
    }

    /**
     * keyword 로 제목 검색
     */
    private BooleanExpression keywordSearch(String keyword) {
        return keyword == null ? null : storeEntity.stName.contains(keyword);
    }

}
