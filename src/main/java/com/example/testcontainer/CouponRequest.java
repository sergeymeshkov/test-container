package com.example.testcontainer;

import jakarta.validation.constraints.NotEmpty;

public record CouponRequest(@NotEmpty(message = "Value cannot be empty") String value) {
}
