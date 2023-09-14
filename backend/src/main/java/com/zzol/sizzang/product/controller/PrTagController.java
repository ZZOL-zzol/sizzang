package com.zzol.sizzang.product.controller;

import com.zzol.sizzang.common.exception.Template.TemplateNoResultException;
import com.zzol.sizzang.common.model.CommonResponse;
import com.zzol.sizzang.product.dto.response.PrTagFindRes;
import com.zzol.sizzang.product.service.PrTagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/prtag")
public class PrTagController {
    private static final String SUCCESS = "success"; // API 성공 시 return

    private final PrTagService prTagService;

    public PrTagController(PrTagService prTagService) {
        this.prTagService = prTagService;
    }

    /**
     * 카테고리별 tag List 조회
     *
     * @return
     */
    @Operation(description = "태그 전체 조회 메서드입니다.")
    @GetMapping("/{pcCode}")
    public CommonResponse<List<PrTagFindRes>> findAll(@PathVariable int pcCode) {
        log.info("PrTagController_findAll_start: ");

        Optional<List<PrTagFindRes>> findRes = Optional.ofNullable(
                prTagService.findTagByCategory(pcCode));

        log.info("PrTagController_findAll_end: " + findRes);
        return CommonResponse.success(findRes.orElseThrow(TemplateNoResultException::new));
    }
}
