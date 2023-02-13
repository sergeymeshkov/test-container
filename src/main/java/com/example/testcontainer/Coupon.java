package com.example.testcontainer;

import java.math.BigInteger;

public record Coupon(BigInteger id, String value) {

    public Coupon(String value) {
        this(null, value);
    }
}
