package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
}