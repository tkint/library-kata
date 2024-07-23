package com.thomaskint.library.domain;

import java.util.UUID;

public record BorrowInput(
        UUID borrowerId,
        int days
) {
}
