package com.learn.ldap.dao;

import com.learn.ldap.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
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
public class PlainPersonDaoImpl implements PersonDao {

	@Autowired
	private LdapTemplate ldapTemplate;

    @Override
	public void create(Person person) {
		Name dn = buildDn(person);
		DirContextAdapter context = new DirContextAdapter(dn);
		mapToContext(person, context);
		ldapTemplate.bind(dn, context, null);
	}

    @Override
	public void update(Person person) {
		Name dn = buildDn(person);
		DirContextAdapter context = (DirContextAdapter) ldapTemplate.lookup(dn);
		mapToContext(person, context);
		ldapTemplate.modifyAttributes(dn, context.getModificationItems());
	}

    @Override
	public void delete(Person person) {
		ldapTemplate.unbind(buildDn(person));
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
		return ldapTemplate.search(query()
                .where("objectclass").is("person"),
                PERSON_CONTEXT_MAPPER);
	}

    @Override
	public Person findByPrimaryKey(String country, String company, String fullname) {
		LdapName dn = buildDn(country, company, fullname);
		return ldapTemplate.lookup(dn, PERSON_CONTEXT_MAPPER);
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

	/**
	 * Maps from Person objects to DirContextAdapter.
	 * 从Java对象转换到LDAP的上下文
	 */
	private void mapToContext(Person person, DirContextAdapter context) {
		context.setAttributeValues("objectclass", new String[] { "top", "person","organizationalPerson","inetOrgPerson" });
		context.setAttributeValue("cn", person.getFullName());
		context.setAttributeValue("sn", person.getLastName());
		context.setAttributeValue("userPassword", person.getUserPassword());
		context.setAttributeValue("description", person.getDescription());
		context.setAttributeValue("telephoneNumber", person.getPhone());
	}

	/**
	 * Maps from DirContextAdapter to Person objects.
	 * 从LDAP的DN转换到Java对象
	 */
	private final static ContextMapper<Person> PERSON_CONTEXT_MAPPER = new AbstractContextMapper<Person>() {
        @Override
		public Person doMapFromContext(DirContextOperations context) {
			Person person = new Person();

            LdapName dn = LdapUtils.newLdapName(context.getDn());
			person.setCountry(LdapUtils.getStringValue(dn, 0));
			person.setCompany(LdapUtils.getStringValue(dn, 1));
			person.setFullName(context.getStringAttribute("cn"));
			person.setLastName(context.getStringAttribute("sn"));
			person.setUserPassword(new String((byte[])context.getObjectAttribute("userPassword"), StandardCharsets.UTF_8));
			person.setDescription(context.getStringAttribute("description"));
			person.setPhone(context.getStringAttribute("telephoneNumber"));

			return person;
		}
	};
}
