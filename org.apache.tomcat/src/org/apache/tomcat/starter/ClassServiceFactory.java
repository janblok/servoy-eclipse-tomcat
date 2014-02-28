/*
 This file belongs to the Servoy development and deployment environment, Copyright (C) 1997-2014 Servoy BV

 This program is free software; you can redistribute it and/or modify it under
 the terms of the GNU Affero General Public License as published by the Free
 Software Foundation; either version 3 of the License, or (at your option) any
 later version.

 This program is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License along
 with this program; if not, see http://www.gnu.org/licenses or write to the Free
 Software Foundation,Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301
 */

package org.apache.tomcat.starter;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

/**
 * @author jcompagner
 *
 */
public class ClassServiceFactory implements ServiceFactory<Class< ? >>
{
	private final Class< ? > cls;

	public ClassServiceFactory(Class< ? > cls)
	{
		this.cls = cls;
	}

	@Override
	public Class< ? > getService(Bundle bundle, ServiceRegistration<Class< ? >> registration)
	{
		return cls;
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration<Class< ? >> registration, Class< ? > service)
	{
	}
}
