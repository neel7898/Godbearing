package com.godbearing.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.ProductDto;

@Repository
public interface ProductDao extends CrudRepository<ProductDto,Integer>{

}
