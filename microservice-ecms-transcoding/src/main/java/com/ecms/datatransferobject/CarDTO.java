package com.ecms.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{

    @JsonIgnore
    private Long id;

    @NotNull(message = "licenseplate can not be null!")
    private String licenseplate;

    @NotNull(message = "manufacturer can not be null!")
    private String manufacturer;

    @NotNull(message = "engineType can not be null!")
    private String engineType;

    @NotNull(message = "seatcount can not be null!")
    private int seatcount;


    private CarDTO()
    {}


    private CarDTO(Long id, String licenseplate, String manufacturer, String engineType, int seatcount)
    {
        this.id = id;
        this.licenseplate = licenseplate;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.seatcount = seatcount;

    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    private CarDTO(Long id)
    {
        this.id = id;
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getLicenseplate()
    {
        return licenseplate;
    }


    public void setLicenseplate(String licenseplate)
    {
        this.licenseplate = licenseplate;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }


    public String getEngineType()
    {
        return engineType;
    }


    public void setEngineType(String engineType)
    {
        this.engineType = engineType;
    }


    public int getSeatcount()
    {
        return seatcount;
    }


    public void setSeatcount(int seatcount)
    {
        this.seatcount = seatcount;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licenseplate;
        private String manufacturer;
        private String engineType;
        private int seatcount;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setLicenseplate(String licenseplate)
        {
            this.licenseplate = licenseplate;
            return this;
        }


        public CarDTOBuilder setManufacturer(String manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public CarDTOBuilder setSeatcount(int seatcount)
        {
            this.seatcount = seatcount;
            return this;
        }


        public CarDTOBuilder setEngineType(String engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTO createDriverDTO()
        {
            return new CarDTO(id, licenseplate, manufacturer, engineType, seatcount);
        }

    }

}
