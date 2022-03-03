package com.learn.ldap.dao;

import com.learn.ldap.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.*;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
public class OdmPersonDaoImpl implements PersonDao {

	@Autowired
	private LdapTemplate ldapTemplate;

    @Override
	public void create(Person person) {
		ldapTemplate.create(person);
	}

    @Override
	public void update(Person person) {
		ldapTemplate.update(person);
	}

    @Override
	public void delete(Person person) {
		ldapTemplate.delete(ldapTemplate.findByDn(buildDn(person), Person.class));
	}

    @Override
	public List<String> getAllPersonNames() {
        return ldapTemplate.search(query()
                .attributes("cn")
                .where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs) throws NamingException {
                        return attrs.get("cn").get().toString();
                    }
                });
    }

    @Override
	public List<Person> findAll() {
		return ldapTemplate.findAll(Person.class);
	}

    @Override
	public Person findByPrimaryKey(String country, String company, String fullname) {
		LdapName dn = buildDn(country, company, fullname);
		return ldapTemplate.findByDn(dn, Person.class);
	}

	private LdapName buildDn(Person person) {
		return buildDn(person.getCountry(), person.getCompany(), person.getFullName());
	}
	private LdapName buildDn(String country, String company, String fullName) {
		return LdapNameBuilder.newInstance()
				.add("c", country)
				.add("ou", company)
				.add("cn", fullName)
				.build();
	}
}
