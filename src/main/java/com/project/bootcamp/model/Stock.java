package com.project.bootcamp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.model.mapper.StockMapper;

@Entity
@Table(name="stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Double price;

	private LocalDate date;

	private Double variation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getVariation() {
		return variation;
	}

	public void setVariation(Double variation) {
		this.variation = variation;
	}

	public StockDTO getDTO() {
		StockMapper stockMapper = StockMapper.INSTANCE;
		return stockMapper.toDTO(this);
	}
	
	@Override
	public String toString() {
		return "Stock [id=" + id + ", name=" + name + ", price=" + price + ", date=" + date + ", variation=" + variation
				+ "]";
	}

}
