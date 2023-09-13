package com.zzol.sizzang.common.exception.Template;

import com.zzol.sizzang.common.exception.ErrorCode;
import com.zzol.sizzang.common.model.BaseException;

public class TemplatePossessionFailException extends BaseException {

    public TemplatePossessionFailException() {
        super(ErrorCode.OUT_OF_POSSESSION);
    }
}
