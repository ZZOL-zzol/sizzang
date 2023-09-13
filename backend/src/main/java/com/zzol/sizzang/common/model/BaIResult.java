package com.zzol.sizzang.common.model;

import lombok.Data;

@Data
public class BaIResult {
    private boolean booleanValue;
    private int intValue;

    public BaIResult(boolean booleanValue, int intValue) {
        this.booleanValue = booleanValue;
        this.intValue = intValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public int getIntValue() {
        return intValue;
    }
}