package com.learn.ldap.util;

import org.springframework.ldap.core.DirContextOperations;

public interface LdapTreeVisitor {

	void visit(DirContextOperations node, int currentDepth);
}
