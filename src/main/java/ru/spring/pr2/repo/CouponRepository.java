package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
