package com.learn.ldap.dao;

import com.learn.ldap.domain.Person;

import java.util.List;

public interface PersonDao {
   void create(Person person);

   void update(Person person);

   void delete(Person person);

   List<String> getAllPersonNames();

   List<Person> findAll();

   Person findByPrimaryKey(String country, String company, String fullname);
}
