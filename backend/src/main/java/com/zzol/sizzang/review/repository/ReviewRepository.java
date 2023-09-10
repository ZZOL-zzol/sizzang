package com.zzol.sizzang.review.repository;

import com.zzol.sizzang.review.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository {

    List<ReviewEntity> findByStCode(int stCode);
}
