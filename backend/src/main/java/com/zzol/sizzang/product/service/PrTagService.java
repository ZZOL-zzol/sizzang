package com.zzol.sizzang.product.service;

import com.zzol.sizzang.product.dto.response.PrTagFindRes;

import java.util.List;

public interface PrTagService {

    /*
    * 물품에 변경이 생길때 tag_cost값으 변경
    * */
    boolean modifyTagCost (int tagCode);

    /*
     * pcCode로 taglist 찾기
     * */
    List<PrTagFindRes> findTagByCategory (int pcCode);

    List<PrTagFindRes> findAll();
}
