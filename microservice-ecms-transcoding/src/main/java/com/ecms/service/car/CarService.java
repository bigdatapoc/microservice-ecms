package com.ecms.service.car;

import java.util.List;

import com.ecms.domainobject.CarDO;
import com.ecms.exception.ConstraintsViolationException;
import com.ecms.exception.EntityNotFoundException;

public interface CarService {
	CarDO find(Long driverId) throws EntityNotFoundException;

	CarDO create(CarDO carDO) throws ConstraintsViolationException;

	List<CarDO> findAll();

	CarDO update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException;

	void delete(Long carId) throws EntityNotFoundException;

}
