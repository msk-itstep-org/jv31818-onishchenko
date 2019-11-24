package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.ControllerWiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ControllerWiyRepository extends JpaRepository<ControllerWiy,Integer> {

    Set<ControllerWiy> findByOwner(Integer id);
}
