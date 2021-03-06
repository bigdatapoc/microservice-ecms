package com.ecms.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecms.domainobject.CarDO;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    List<CarDO> findAllByDeleted(boolean deleted);
}
