/**
 * 
 */
package za.co.sindi.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Bienfait Sindi
 * @since 26 October 2014
 *
 */
public interface Resource {

	public File getFile() throws FileNotFoundException;
	public String getPath();
	public InputStream getInputStream() throws IOException;
	public URI getURI() throws URISyntaxException;
	public URL getURL();
	
}
