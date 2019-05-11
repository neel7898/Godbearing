package com.godbearing.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.Legends;

@Repository
public interface LegendDao extends CrudRepository<Legends,Integer>{
	
	List<Legends> findByProductId(int id);
	void deleteByProductId(int id);
	
}
