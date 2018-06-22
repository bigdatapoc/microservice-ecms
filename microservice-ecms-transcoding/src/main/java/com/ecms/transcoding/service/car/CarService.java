package com.ecms.transcoding.service.car;

import java.util.List;

import com.ecms.transcoding.domainobject.CarDO;
import com.ecms.transcoding.exception.ConstraintsViolationException;
import com.ecms.transcoding.exception.EntityNotFoundException;

public interface CarService {
	CarDO find(Long driverId) throws EntityNotFoundException;

	CarDO create(CarDO carDO) throws ConstraintsViolationException;

	List<CarDO> findAll();

	CarDO update(long carId, CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException;

	void delete(Long carId) throws EntityNotFoundException;

}
