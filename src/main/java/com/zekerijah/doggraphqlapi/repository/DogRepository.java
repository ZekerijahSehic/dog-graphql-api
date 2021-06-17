package com.zekerijah.doggraphqlapi.repository;

import com.zekerijah.doggraphqlapi.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
