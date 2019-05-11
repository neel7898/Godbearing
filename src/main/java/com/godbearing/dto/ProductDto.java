package com.godbearing.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ProductDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String productName;
	private String availability;
	@Column(columnDefinition="text")
	private String shortDescription;
	@Column(columnDefinition="text")
	private String longDescription;
	private boolean innerDia, outerDia, width, smallThickness, thickness, shaftDia,centreHeight,innerLength,outerLength,flank;
	
	@ElementCollection
	private List<String> productImages = new ArrayList<String>();
	
	@ElementCollection
	private List<String> legendHeader = new ArrayList<String>();
	
	private String defaultImage;
	
	private String catalougeImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public boolean isInnerDia() {
		return innerDia;
	}

	public void setInnerDia(boolean innerDia) {
		this.innerDia = innerDia;
	}

	public boolean isOuterDia() {
		return outerDia;
	}

	public void setOuterDia(boolean outerDia) {
		this.outerDia = outerDia;
	}

	public boolean isWidth() {
		return width;
	}

	public void setWidth(boolean width) {
		this.width = width;
	}

	public boolean isSmallThickness() {
		return smallThickness;
	}

	public void setSmallThickness(boolean smallThickness) {
		this.smallThickness = smallThickness;
	}

	public boolean isThickness() {
		return thickness;
	}

	public void setThickness(boolean thickness) {
		this.thickness = thickness;
	}

	public List<String> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<String> productImages) {
		this.productImages = productImages;
	}

	public boolean isShaftDia() {
		return shaftDia;
	}

	public void setShaftDia(boolean shaftDia) {
		this.shaftDia = shaftDia;
	}

	public boolean isCentreHeight() {
		return centreHeight;
	}

	public void setCentreHeight(boolean centreHeight) {
		this.centreHeight = centreHeight;
	}

	public boolean isInnerLength() {
		return innerLength;
	}

	public void setInnerLength(boolean innerLength) {
		this.innerLength = innerLength;
	}

	public boolean isOuterLength() {
		return outerLength;
	}

	public void setOuterLength(boolean outerLength) {
		this.outerLength = outerLength;
	}

	public boolean isFlank() {
		return flank;
	}

	public void setFlank(boolean flank) {
		this.flank = flank;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public List<String> getLegendHeader() {
		return legendHeader;
	}

	public void setLegendHeader(List<String> legendHeader) {
		this.legendHeader = legendHeader;
	}

	public String getCatalougeImage() {
		return catalougeImage;
	}

	public void setCatalougeImage(String catalougeImage) {
		this.catalougeImage = catalougeImage;
	}

}
