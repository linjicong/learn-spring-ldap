package com.learn.ldap.controller;

import com.learn.ldap.dao.PersonDao;
import com.learn.ldap.domain.Person;
import com.learn.ldap.util.HtmlRowLdapTreeVisitor;
import com.learn.ldap.util.LdapTree;
import com.learn.ldap.util.LdapTreeBuilder;
import com.learn.ldap.util.PersonLinkHtmlRowLdapTreeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlainController {
    @Autowired
    private LdapTreeBuilder ldapTreeBuilder;
    @Autowired
    private PersonDao personDao;

    @GetMapping("/showTree")
    public List<String> showTree() {
        LdapTree ldapTree = ldapTreeBuilder.getLdapTree(LdapUtils.emptyLdapName());
        HtmlRowLdapTreeVisitor visitor = new PersonLinkHtmlRowLdapTreeVisitor();
        ldapTree.traverse(visitor);
        return visitor.getRows();
    }

    @GetMapping("showPerson")
    public Person showPerson(String country, String company, String fullName) {
        return personDao.findByPrimaryKey(country, company, fullName);
    }

    @GetMapping("addPerson")
    public void addPerson() {
        Person person = getPerson();
        personDao.create(person);
    }

    @GetMapping("updatePerson")
    public void updatePerson() {
        Person person = personDao.findByPrimaryKey("CN", "company1", "John Doe");
        person.setPhone(person.getPhone()+"666");
        personDao.update(person);
    }

    @GetMapping("removePerson")
    public void removePerson() {
        Person person = personDao.findByPrimaryKey("CN", "company1", "John Doe");
        person.setPhone(person.getPhone()+"666");
        personDao.delete(person);
    }

    private Person getPerson() {
        Person person = new Person();
        person.setFullName("John Doe");
        person.setLastName("Doe");
        person.setCompany("company1");
        person.setCountry("CN");
        person.setDescription("Test user");
        person.setUserPassword("Test");
        return person;
    }
}
