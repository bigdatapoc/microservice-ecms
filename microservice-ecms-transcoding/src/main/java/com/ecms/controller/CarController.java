package com.ecms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecms.controller.mapper.CarMapper;
import com.ecms.datatransferobject.CarDTO;
import com.ecms.domainobject.CarDO;
import com.ecms.exception.ConstraintsViolationException;
import com.ecms.exception.EntityNotFoundException;
import com.ecms.service.car.CarService;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

	private final CarService carService;

	@Autowired
	public CarController(final CarService carService) {
		this.carService = carService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
		CarDO carDO = CarMapper.makeCarDO(carDTO);
		return CarMapper.makeCarDTO(carService.create(carDO));
	}

	@GetMapping
	public List<CarDTO> findCars() throws ConstraintsViolationException, EntityNotFoundException {
		return CarMapper.makeCarDTOList(carService.findAll());
	}

	@PutMapping("/{carId}")
	public CarDTO updateCar(@PathVariable(value = "carId") Long carId, @Valid @RequestBody CarDTO carDTO)
			throws ConstraintsViolationException, EntityNotFoundException {
		CarDO carDO = CarMapper.makeCarDO(carDTO);
		return CarMapper.makeCarDTO(carService.update(carId, carDO));
	}

	@DeleteMapping("/{carId}")
	public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		carService.delete(carId);
	}

}
