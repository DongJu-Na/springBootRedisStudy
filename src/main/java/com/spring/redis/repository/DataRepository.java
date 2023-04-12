package com.spring.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.redis.model.Data;

/*
 * @deprecated RedisTemplate 주로 사용 예정 그러므로 Repository 사용x 23.04.12
 */
@Deprecated
@Repository
public interface DataRepository extends CrudRepository<Data, String> {

}
