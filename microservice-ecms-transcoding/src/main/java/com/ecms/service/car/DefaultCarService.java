package com.ecms.service.car;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecms.dataaccessobject.CarRepository;
import com.ecms.domainobject.CarDO;
import com.ecms.exception.ConstraintsViolationException;
import com.ecms.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

	private final CarRepository carRepository;

	public DefaultCarService(final CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public CarDO find(Long carId) throws EntityNotFoundException {
		return findCarChecked(carId);
	}

	/**
	 * Creates a new car.
	 *
	 * @param carDO
	 * @return
	 * @throws ConstraintsViolationException
	 *             if a car already exists with the given licenseplate.
	 */
	@Override
	public CarDO create(CarDO carDO) throws ConstraintsViolationException {
		CarDO car;
		try {
			car = carRepository.save(carDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to car creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return car;
	}

	/**
	 * Deletes an existing car by id.
	 *
	 * @param carId
	 * @throws EntityNotFoundException
	 *             if no car with the given id was found.
	 */
	@Override
	@Transactional
	public void delete(Long carId) throws EntityNotFoundException {
		CarDO carDO = findCarChecked(carId);
		carDO.setDeleted(true);
	}

	@Override
	public List<CarDO> findAll() {
		boolean deleted = false;
		return this.carRepository.findAllByDeleted(deleted);
	}

	private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
		CarDO carDO = carRepository.findOne(carId);
		if (carDO == null) {
			throw new EntityNotFoundException("Could not find entity with id: " + carId);
		}
		return carDO;
	}

	/**
	 * Update the car.
	 *
	 * @param carId
	 * @param carDO
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public CarDO update(long carId, CarDO carDO) throws ConstraintsViolationException, EntityNotFoundException {
		CarDO car = carRepository.findOne(carId);
		if (car == null) {
			throw new EntityNotFoundException("Could not find entity with id: " + carId);
		}

		if (carDO.getLicenseplate() != null)
			car.setLicenseplate(carDO.getLicenseplate());

		if (carDO.getManufacturer() != null)
			car.setManufacturer(carDO.getManufacturer());

		if (carDO.getEngineType() != null)
			car.setEngineType(carDO.getEngineType());

		if (carDO.getSeatcount() != 0)
			car.setSeatcount(carDO.getSeatcount());

		return carRepository.save(car);
	}
}
