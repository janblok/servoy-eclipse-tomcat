package org.apache.tomcat.starter;

import java.util.Set;

public interface IServicesProvider {
	static final String EXTENSION_ID = "org.apache.tomcat.serviceprovider";
	
	void registerServices();

	Set<Class<?>> getAnnotatedClasses(String context);
}
