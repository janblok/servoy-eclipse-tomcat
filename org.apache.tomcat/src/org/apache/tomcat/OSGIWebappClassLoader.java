package org.apache.tomcat;

import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

public class OSGIWebappClassLoader extends ClassLoader {
	private final CopyOnWriteArrayList<Bundle> usedBundles = new CopyOnWriteArrayList<Bundle>();
	
	public OSGIWebappClassLoader() {
		super();
	}
	
	public OSGIWebappClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			return super.findClass(name);
		} catch (ClassNotFoundException e) {
			ServiceReference<?> serviceReference = Activator.getActivator().getContext().getServiceReference(name);
			if (serviceReference != null) {
				usedBundles.add(serviceReference.getBundle());
				return serviceReference.getBundle().loadClass(name);
			}
			else {
				// now try to resolve it through the bundles that did provide a service.
				for (Bundle bundle : usedBundles) {
					try {
						return bundle.loadClass(name);
					} catch(ClassNotFoundException ex) {
						// ignore
					}
				}
			}
		}
		throw new ClassNotFoundException(name);
	}
}
