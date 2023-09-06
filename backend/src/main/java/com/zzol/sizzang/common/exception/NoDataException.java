package com.zzol.sizzang.common.exception;

import com.zzol.sizzang.common.model.BaseException;

public class NoDataException extends BaseException {

    public NoDataException() {
        super(ErrorCode.NO_SUCH_DATA);
    }
}
