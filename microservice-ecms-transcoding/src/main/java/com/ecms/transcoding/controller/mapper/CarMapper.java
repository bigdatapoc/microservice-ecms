package com.ecms.transcoding.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ecms.transcoding.datatransferobject.CarDTO;
import com.ecms.transcoding.domainobject.CarDO;

public class CarMapper
{

    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getLicenseplate(), carDTO.getManufacturer(), carDTO.getEngineType(), carDTO.getSeatcount());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder()
                .setId(carDO.getId())
                .setLicenseplate(carDO.getLicenseplate())
                .setManufacturer(carDO.getManufacturer())
                .setSeatcount(carDO.getSeatcount())
                .setEngineType(carDO.getEngineType());

        return carDTOBuilder.createDriverDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars.stream().map(CarMapper::makeCarDTO).collect(Collectors.toList());
    }
}
