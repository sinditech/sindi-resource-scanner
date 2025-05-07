/**
 * 
 */
package za.co.sindi.resource.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.resource.AbstractResource;
import za.co.sindi.resource.context.ResourceContext;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public class ResourceContextResource extends AbstractResource {

	private final ResourceContext resourceContext;
	private final String path;
	
	/**
	 * @param path
	 * @param resourceContext
	 */
	public ResourceContextResource(final String path, final ResourceContext resourceContext) {
		super();
		Preconditions.checkArgument(path != null && !path.isEmpty(), "Path may not be null nor empty.");
		Preconditions.checkArgument(resourceContext != null, "No ResourceContext has been provided.");
		
		this.path = path;
		this.resourceContext = resourceContext;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getFile()
	 */
	@Override
	public File getFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new File(resourceContext.getRealPath(path));
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
		return resourceContext.getResourceAsStream(path);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getURL()
	 */
	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		try {
			return resourceContext.getResource(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
