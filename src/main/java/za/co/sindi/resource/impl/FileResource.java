/**
 * 
 */
package za.co.sindi.resource.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.resource.AbstractResource;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public class FileResource extends AbstractResource {

	private File file;
	
	/**
	 * @param file
	 */
	public FileResource(File file) {
		super();
		Preconditions.checkArgument(file != null, "File not provided.");
		
		this.file = file;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getFile()
	 */
	@Override
	public File getFile() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return file;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getPath()
	 */
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return file.getAbsolutePath();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return new FileInputStream(file);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getURL()
	 */
	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
