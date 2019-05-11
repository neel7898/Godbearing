package com.godbearing.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Legends {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int productId;
	private String legendHeader,legend,description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getLegendHeader() {
		return legendHeader;
	}

	public void setLegendHeader(String legendHeader) {
		this.legendHeader = legendHeader;
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
	
}
