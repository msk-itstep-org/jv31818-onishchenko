package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.ControllerWiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControllerWiyRepository extends JpaRepository<ControllerWiy,Integer> {
}
