package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findByStreet(String street);
}