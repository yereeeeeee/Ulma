package com.ssafy11.domain.accounts;

import jakarta.validation.constraints.NotNull;

public record Account(
        @NotNull Integer accountId,
        @NotNull String accountNumber,
        @NotNull Long balance,
        @NotNull String bankCode
) {
}
