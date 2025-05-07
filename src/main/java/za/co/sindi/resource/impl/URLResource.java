/**
 * 
 */
package za.co.sindi.resource.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.resource.AbstractResource;
import za.co.sindi.resource.utils.ResourceUtils;

/**
 * @author Bienfait Sindi
 * @since 28 October 2014
 *
 */
public class URLResource extends AbstractResource {

	private final URL resource;
	
	/**
	 * @param resource
	 */
	public URLResource(URL resource) {
		super();
		Preconditions.checkArgument(resource != null, "No resource provided.");
		
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getFile()
	 */
	@Override
	public File getFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return ResourceUtils.getFile(resource);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getPath()
	 */
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return resource.toExternalForm();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		URLConnection connection = resource.openConnection();
		connection.setUseCaches(false);
		return connection.getInputStream();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getURL()
	 */
	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		return resource;
	}
}
