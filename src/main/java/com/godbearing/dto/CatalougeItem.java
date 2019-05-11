package com.godbearing.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CatalougeItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int productId;
	private String bearingNo1, bearingNo2;
	private double innerDia, outerDia, width, smallThickness, thickness, weight, shaftDia,centreHeight,innerLength,outerLength,flank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getBearingNo1() {
		return bearingNo1;
	}

	public void setBearingNo1(String bearingNo1) {
		this.bearingNo1 = bearingNo1;
	}

	public String getBearingNo2() {
		return bearingNo2;
	}

	public void setBearingNo2(String bearingNo2) {
		this.bearingNo2 = bearingNo2;
	}

	public double getInnerDia() {
		return innerDia;
	}

	public void setInnerDia(double innerDia) {
		this.innerDia = innerDia;
	}

	public double getOuterDia() {
		return outerDia;
	}

	public void setOuterDia(double outerDia) {
		this.outerDia = outerDia;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getSmallThickness() {
		return smallThickness;
	}

	public void setSmallThickness(double smallThickness) {
		this.smallThickness = smallThickness;
	}

	public double getThickness() {
		return thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getShaftDia() {
		return shaftDia;
	}

	public void setShaftDia(double shaftDia) {
		this.shaftDia = shaftDia;
	}

	public double getCentreHeight() {
		return centreHeight;
	}

	public void setCentreHeight(double centreHeight) {
		this.centreHeight = centreHeight;
	}

	public double getInnerLength() {
		return innerLength;
	}

	public void setInnerLength(double innerLength) {
		this.innerLength = innerLength;
	}

	public double getOuterLength() {
		return outerLength;
	}

	public void setOuterLength(double outerLength) {
		this.outerLength = outerLength;
	}

	public double getFlank() {
		return flank;
	}

	public void setFlank(double flank) {
		this.flank = flank;
	}

}
