package com.zzol.sizzang.market.repository;

import com.zzol.sizzang.market.dto.response.MarketSearchRes;
import com.zzol.sizzang.market.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, Long> {

    //시장 상세
    Optional<MarketEntity> findByMkCode(int mkCode);

//    @Query(value = "SELECT mk_code, mk_name, mk_address, mk_toilet, mk_parking, mk_latitude,mk_longtitude " +
//            "FROM market WHERE mk_name LIKE %?1% OR mk_address LIKE %?1% " +
//            "ORDER BY " +
//                "CASE WHEN mk_name LIKE ?1% THEN 3" +
//                    "WHEN mk_address LIKE %?1% THEN 2" +
//                    "ELSE 1 END DESC LIMIT ?2 OFFSET ?3", nativeQuery = true)

    @Query(value = "SELECT mk_code, mk_name, mk_address, mk_toilet, mk_parking, mk_latitude, mk_longtitude " +
                    "FROM market WHERE mk_name LIKE '%'|| ?1 || '%' " +
                    "ORDER BY (CASE WHEN mk_name LIKE %?1% THEN 2 " +
                    "ELSE 1 END), " +
                    "DESC LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<MarketSearchRes> searchMarketByName(String marketName, int limit, int offset);

}
