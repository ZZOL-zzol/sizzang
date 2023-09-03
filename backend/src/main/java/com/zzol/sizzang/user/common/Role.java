package com.zzol.sizzang.user.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    CUSTOMER("ROLE_CUSTOMER"), SELLER("ROLE_SELLER"), ADMIN("ROLE_ADMIN");

    private final String key;
}