package org.apache.tomcat.starter;

import java.util.Set;

public interface IServiceProvider {
	static final String EXTENSION_ID = "org.apache.tomcat.serviceprovider"; //$NON-NLS-1$
	
	void registerServices();

	Set<Class<?>> getAnnotatedClasses(String context);
}
