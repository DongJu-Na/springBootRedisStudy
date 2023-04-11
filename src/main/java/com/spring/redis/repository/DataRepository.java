package com.spring.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.redis.model.Data;

@Repository
public interface DataRepository extends CrudRepository<Data, String> {

}
