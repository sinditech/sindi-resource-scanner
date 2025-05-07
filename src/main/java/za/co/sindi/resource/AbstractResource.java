/**
 * 
 */
package za.co.sindi.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public abstract class AbstractResource implements Resource {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.resource.Resource#getURI()
	 */
	@Override
	public URI getURI() throws URISyntaxException {
		// TODO Auto-generated method stub
		URL url = getURL();
		return (url == null) ? null : new URI(url.toExternalForm());
	}
}
