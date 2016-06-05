package com.pragu.service;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.pragu.service.model.Sensor;

@Transactional
public interface SensorDAO extends CrudRepository<Sensor, String> {

	Sensor findBySuid(String suid);

	Sensor findByEuid(String euid);

} 