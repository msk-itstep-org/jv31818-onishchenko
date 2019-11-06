package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.ControllerWiy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerWiyRepository extends CrudRepository<ControllerWiy,Integer> {
}
