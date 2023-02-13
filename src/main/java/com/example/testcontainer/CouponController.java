package com.example.testcontainer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping("/coupons")
@Validated
@AllArgsConstructor
@Slf4j
public class CouponController {

    private final CouponRepository couponRepository;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CouponRequest couponRequest) {
        final var coupon = couponRepository.save(new Coupon(couponRequest.value()));
        log.info("Coupon has been saved. {}", coupon);
        final var location = create(format("/couponse/%s", coupon.id()));
        return ResponseEntity.ok().location(location).build();
    }

}
