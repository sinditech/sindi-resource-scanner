/**
 * 
 */
package za.co.sindi.resource.context;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * @author Buhake Sindi
 * @since 11 April 2012
 *
 */
public interface ResourceContext {

	public String getRealPath(String path);
	public URL getResource(String path) throws MalformedURLException;
	public InputStream getResourceAsStream(String path);
	public Set<String> getResourcePaths(String path);
}
