package org.apache.tomcat;

import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.starter.IServiceProvider;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static Activator self;
	
	private final  Set<IServiceProvider> serviceProviders = new HashSet<IServiceProvider>();
	private BundleContext context;

	public static Activator getActivator() {
		return self;
	}
	
	public BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		self = this;
		context = bundleContext;
		
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint ep = reg.getExtensionPoint(IServiceProvider.EXTENSION_ID);
		IExtension[] extensions = ep.getExtensions();
		if (extensions != null && extensions.length > 0)
		{
			for (IExtension extension : extensions)
			{
				IServiceProvider provider = (IServiceProvider)extension.getConfigurationElements()[0].createExecutableExtension("class");
				provider.registerServices();
				serviceProviders.add(provider);
			}
		}
	}
	
	public Set<Class<?>> getAnnotatedClasses(String context) {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (IServiceProvider serviceProvider : serviceProviders) {
			Set<Class<?>> annotatedClasses = serviceProvider.getAnnotatedClasses(context);
			if (annotatedClasses != null) classes.addAll(annotatedClasses);
		}
		return classes;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		context = null;
	}

}
