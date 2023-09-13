package com.zzol.sizzang.review.repository;

import com.zzol.sizzang.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByStCode(long stCode);

    @Query(value = "SELECT AVG(re.reScore) " +
            "FROM ReviewEntity re " +
            "WHERE re.stCode = :stCode")
    double getReviewScore(@Param("stCode") long stCode);

}
