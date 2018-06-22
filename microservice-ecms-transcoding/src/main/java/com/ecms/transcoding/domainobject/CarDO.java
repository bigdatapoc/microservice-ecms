package com.ecms.transcoding.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(name = "uc_licenseplate", columnNames = { "licenseplate" }))
public class CarDO {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	private Boolean deleted = false;

	@Column(nullable = false)
	@NotNull(message = "licenseplate can not be null!")
	private String licenseplate;

	@Column(nullable = false)
	@NotNull(message = "seatcount can not be null!")
	private int seatcount;

	@Column
	private Boolean convertible = false;

	@Column
	private String rating;

	@Column(nullable = false)
	@NotNull(message = "engine_type can not be null!")
	private String engineType;

	@Column(nullable = false)
	@NotNull(message = "manufacturer can not be null!")
	private String manufacturer;

	public CarDO() {
	}

	public CarDO(String licenseplate, String manufacturer, String engineType, int seatcount) {
		this.licenseplate = licenseplate;
		this.manufacturer = manufacturer;
		this.engineType = engineType;
		this.deleted = false;
		this.seatcount = seatcount;
		this.convertible = false;
		this.rating = null;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public int getSeatcount() {
		return seatcount;
	}

	public void setSeatcount(int seatcount) {
		this.seatcount = seatcount;
	}

	public boolean getConvertible() {
		return convertible;
	}

	public void setConvertible(boolean convertible) {
		this.convertible = convertible;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}

}
