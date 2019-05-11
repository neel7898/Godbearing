package com.godbearing.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.MessageDto;

@Repository
public interface MessageDao extends CrudRepository<MessageDto, Integer> {

}
