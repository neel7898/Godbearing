package com.godbearing.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godbearing.dao.CatalougeDao;
import com.godbearing.dao.LegendDao;
import com.godbearing.dao.ProductDao;
import com.godbearing.dto.CatalougeItem;
import com.godbearing.dto.Legends;
import com.godbearing.dto.ProductDto;

@RestController
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	LegendDao legendDao;
	
	@Autowired
	CatalougeDao catalougeDao;
	
	@GetMapping("/getAllProducts")
	public List<ProductDto> getAllProducts(){
		List<ProductDto> list = new ArrayList<ProductDto>();
		this.productDao.findAll().forEach(obj -> list.add(obj));
		return list;
	}
	
	@GetMapping("/init")
	public ProductDto initProducts() {
		ProductDto dt = new  ProductDto();
		dt.setProductName("Ball Bearing");
		dt.setInnerDia(true);
		dt.setAvailability("In Stock");
		dt.setOuterDia(true);
		dt.setWidth(true);
		dt.setShortDescription("We offer deep groove ball bearings, capable of operating at high speeds and are widely used radial bearings. These non-separable bearings are available in a wide variety of seal, shield snapring arrangements. It requires little attention or maintenance in service.");
		dt.setLongDescription("We offer deep groove ball bearings, capable of operating at high speeds and are widely used radial bearings. These non-separable bearings are available in a wide variety of seal, shield snapring arrangements. It requires little attention or maintenance in service.\r\n" + 
				"\r\n" + 
				"Physical characteristics:\r\n" + 
				"\r\n" + 
				"• Deep groove ball bearings have deep uninterrupted raceways. This allows them to carry axial loads in both directions at reasonably high speeds.\r\n" + 
				"• The bearing ring grooves are circular arcs made slightly larger than the radius of the ball.\r\n" + 
				"• The balls make point contact with the raceways (elliptical contact when loaded).\r\n" + 
				"• The inner ring shoulders are of equal height (as the outer ring shoulders).\r\n" + 
				"\r\n" + 
				"Advantages:\r\n" + 
				"\r\n" + 
				"• Can sustain radial, axial, or composite loads.\r\n" + 
				"• Can take the place of high speed angular contact ball bearings.\r\n" + 
				"• Simple design.\r\n" + 
				"• Maintenance free.\r\n" + 
				"• Longer service life.\r\n" + 
				"");
		return this.productDao.save(dt);
	}
	
	@PostMapping("/addProduct")
	public ProductDto addSingleProduct(@RequestBody ProductDto dto) {
		return this.productDao.save(dto);
	}
	
	@GetMapping("/getProduct/{id}")
	public ProductDto getProductByProductId(@PathVariable("id") int id) {
		return this.productDao.findById(id).get();
	}
	
	@PostMapping("/deleteProduct")
	@Transactional
	public String deleteProductFromProductId(@RequestBody int id) {
		this.legendDao.deleteByProductId(id);
		this.catalougeDao.deleteByProductId(id);
		this.productDao.deleteById(id);
		return "{ \"message\" : \"Deleted Successfully\"}";
	}
	
	@GetMapping("/getLegends/{productId}")
	public List<Legends> getLegendsByProductId(@PathVariable("productId") int productId){
		return this.legendDao.findByProductId(productId);
	}
	
	@PostMapping("/addLegend")
	public Legends addLegend(@RequestBody Legends legend) {
		return this.legendDao.save(legend);
	}
	
	@PostMapping("/deleteLegend/{id}")
	public String deleteLegend(@PathVariable("id") int id) {
		this.legendDao.deleteById(id);
		return "{ \"message\" : \"Deleted Successfully\"}";
	}
	
	@GetMapping("/getCatalouge/{productId}")
	public List<CatalougeItem> getCatalougeByProductId(@PathVariable("productId") int productId){
		return this.catalougeDao.findByProductId(productId);
	}
	
	@PostMapping("/addCatalouge")
	public CatalougeItem addCatalouge(@RequestBody CatalougeItem legend) {
		return this.catalougeDao.save(legend);
	}
	
	@PostMapping("/deleteCatalouge/{id}")
	public String deleteCatalouge(@PathVariable("id") int id) {
		this.catalougeDao.deleteById(id);
		return "{ \"message\" : \"Deleted Successfully\"}";
	}
	
	
}
