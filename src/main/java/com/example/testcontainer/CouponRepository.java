package com.example.testcontainer;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import static org.jooq.generated.Tables.COUPONS;

@Repository
@AllArgsConstructor
public class CouponRepository {

    private final DSLContext context;
    private final DataSource dataSource;

    public Coupon save(Coupon coupon) {
        final var dslContext = DSL.using(getConnection(), getSettings());
        return dslContext.insertInto(COUPONS, COUPONS.VALUE)
                .values(coupon.value())
                .returning(COUPONS.ID, COUPONS.VALUE)
                .fetchOne(r -> new Coupon(BigInteger.valueOf(r.getValue(COUPONS.ID)), r.getValue(COUPONS.VALUE)));
    }

    @SneakyThrows
    private Settings getSettings() {
       return new Settings().withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED);
    }

    @SneakyThrows
    private Connection getConnection() {
        return dataSource.getConnection();
    }

}
