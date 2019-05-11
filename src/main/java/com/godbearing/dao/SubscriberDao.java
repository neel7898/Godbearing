package com.godbearing.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.godbearing.dto.Subscriber;

@Repository
public interface SubscriberDao extends CrudRepository<Subscriber,Integer>{

}
