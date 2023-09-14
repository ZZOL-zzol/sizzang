package com.zzol.sizzang.market.repository;

import com.zzol.sizzang.market.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, Integer> {

    //시장 상세
    Optional<MarketEntity> findByMkCode(int mkCode);

//    @Query(value = "SELECT mk_code, mk_name, mk_address, mk_toilet, mk_parking, mk_latitude,mk_longtitude " +
//            "FROM market WHERE mk_name LIKE %?1% OR mk_address LIKE %?1% " +
//            "ORDER BY " +
//                "CASE WHEN mk_name LIKE ?1% THEN 3" +
//                    "WHEN mk_address LIKE %?1% THEN 2" +
//                    "ELSE 1 END DESC LIMIT ?2 OFFSET ?3", nativeQuery = true)

    @Query(value = "SELECT * " +
                    "FROM market WHERE mk_name LIKE '%'|| ?1 || '%' " +
                    "ORDER BY (CASE WHEN mk_name LIKE %?1% THEN 2 " +
                    "ELSE 1 END) " +
                    "DESC LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<MarketEntity> searchMarketByName(String marketName, int limit, int offset);

    @Query(value = "SELECT * " +
            "FROM market ORDER BY region_code DESC LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<MarketEntity> getAllMarket(int limit, int offset);

}
