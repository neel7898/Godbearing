package com.godbearing.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.CatalougeItem;
import com.godbearing.dto.Legends;

@Repository
public interface CatalougeDao extends CrudRepository<CatalougeItem,Integer>{
	
	List<CatalougeItem> findByProductId(int id);
	void deleteByProductId(int id);
}
