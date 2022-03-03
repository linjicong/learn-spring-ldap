package com.learn.ldap.controller;

import cn.hutool.core.util.RandomUtil;
import com.learn.ldap.dao.PersonDao;
import com.learn.ldap.domain.Person;
import com.learn.ldap.util.HtmlRowLdapTreeVisitor;
import com.learn.ldap.util.LdapTree;
import com.learn.ldap.util.LdapTreeBuilder;
import com.learn.ldap.util.PersonLinkHtmlRowLdapTreeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/odm")
public class OdmController {
    @Autowired
    private LdapTreeBuilder ldapTreeBuilder;

    @Resource(name = "odmPersonDaoImpl")
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
        Person person = new Person();
        person.setFullName(RandomUtil.randomString(8));
        person.setLastName(RandomUtil.randomString(4));
        person.setCompany("company1");
        person.setCountry("CN");
        person.setDescription(RandomUtil.randomString(12));
        person.setUserPassword(new LdapShaPasswordEncoder().encode(person.getFullName()));
        personDao.create(person);
    }

    @GetMapping("updatePerson")
    public void updatePerson(String country, String company, String fullName) {
        Person person = new Person(country,company,fullName);
        person.setPhone(person.getPhone()+"666");
        personDao.update(person);
    }

    @GetMapping("removePerson")
    public void removePerson(String country, String company, String fullName) {
        Person person = new Person(country,company,fullName);
        personDao.delete(person);
    }
}
