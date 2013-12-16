package com.goparty.data.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.goparty.data.model.Event;

public interface UserRepository extends CrudRepository<Event, String>{

}
