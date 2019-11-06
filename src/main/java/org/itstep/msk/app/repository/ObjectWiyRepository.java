package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.ObjectWiy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectWiyRepository extends CrudRepository<ObjectWiy,Integer> {
}
