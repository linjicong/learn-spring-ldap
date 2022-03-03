package com.learn.ldap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.odm.core.ObjectDirectoryMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

import javax.naming.Name;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@SpringBootTest
class LearnSpringLdapApplicationTests {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    public void findAll() throws NamingException {
        ContextSource contextSource=ldapTemplate.getContextSource();
        //List<String> str=ldapTemplate.listBindings("cn=admin,dc=pub,dc=org");
        //System.out.println(str);
        //ObjectDirectoryMapper objectDirectoryMapper=ldapTemplate.getObjectDirectoryMapper();
        //List<String> dc=ldapTemplate.list("objectClass=*,dc=pub,dc=org");
        //LdapQuery query = query().base("dc=pub,dc=org").where("objectclass").is("*");
        //List<Object> obj=ldapTemplate.search(query, (AttributesMapper<Object>) attributes -> attributes);
        //System.out.println(dc);
        //DirContext dirContext=contextSource.getReadOnlyContext();
        //Hashtable hashtable=dirContext.getEnvironment();
        //System.out.println(hashtable);
        //ldapTemplate.bind();
        //DirContext readWriteContext = contextSource.getReadWriteContext();
        //SearchControls controls = new SearchControls();
        //controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //NamingEnumeration<SearchResult> search = readWriteContext.search("", "(objectclass=person)", controls);
        //ldapTemplate.search("ou=persons", "&(objectclass=inetOrgPerson)(cn=linjicong)", new AttributesMapper<Object>() {
        //    @Override
        //    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        //        return attributes.get("cn").get();
        //    }
        //});

        //List<String> cns=ldapTemplate.search("", "objectclass=inetOrgPerson", (AttributesMapper<String>) attributes -> attributes.get("cn").get().toString());
        //ldapTemplate.listBindings(LdapUtils.emptyLdapName(),
        //        new AbstractContextMapper<Object>() {
        //            @Override
        //            protected Object doMapFromContext(DirContextOperations ctx) {
        //                Name dn = ctx.getDn();
        //                System.out.println(dn);
        //                return null;
        //            }
        //        });
        System.out.println(new LdapShaPasswordEncoder().encode("John Doe"));
    }
}
