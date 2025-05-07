/**
 * 
 */
package za.co.sindi.resource.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import za.co.sindi.commons.utils.Classes;
import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.resource.AbstractResource;
import za.co.sindi.resource.utils.ResourceUtils;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public class ClassLoaderResource extends AbstractResource {

	private final ClassLoader classLoader;
	private final String path;
	
	/**
	 * @param path
	 */
	public ClassLoaderResource(final String path) {
		this(path, null);
	}

	/**
	 * @param path
	 * @param classLoader
	 */
	public ClassLoaderResource(final String path, final ClassLoader classLoader) {
		super();
		Preconditions.checkArgument(path != null && !path.isEmpty(), "Path may not be null nor empty.");
		
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : Classes.getClassLoader());
		if (this.classLoader == null) {
			throw new IllegalStateException("Unable to instantiate ClassLoader.");
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getFile()
	 */
	@Override
	public File getFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return ResourceUtils.getFile(getURL());
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getPath()
	 */
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return classLoader.getResourceAsStream(path);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getURL()
	 */
	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		return classLoader.getResource(path);
	}
}
