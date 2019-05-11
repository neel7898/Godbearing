package com.godbearing.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.EnquiryDto;

@Repository
public interface EnquiryDao extends CrudRepository<EnquiryDto,Integer>{

}
