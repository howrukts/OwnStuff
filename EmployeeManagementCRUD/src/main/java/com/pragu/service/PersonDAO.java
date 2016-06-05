package com.pragu.service;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.pragu.service.model.Person;

@Transactional
public interface PersonDAO extends CrudRepository<Person, Integer> {

  public Person findById(int id);

} 