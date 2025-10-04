/**
 * 
 */
package za.co.sindi.resource.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import za.co.sindi.commons.utils.Classes;
import za.co.sindi.resource.Resource;

/**
 * Various utility methods. Some copied from Spring <code>org.springframework.util.ResourceUtils</code> class. 
 * @author Bienfait Sindi
 * @since 28 October 2014
 *
 */
public final class ResourceUtils {
	
	public static final String URL_PROTOCOL_FILE = "file";
	
	public static File getFile(URL resourceUrl) throws FileNotFoundException {
		return getFile(resourceUrl, "URL");
	}

	public static File getFile(URL resourceUrl, String description) throws FileNotFoundException {
		if (resourceUrl == null) {
			throw new IllegalArgumentException("Resource URL must not be null");
		}
		
		if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
			throw new FileNotFoundException(description + " cannot be resolved to absolute file path " +
					"because it does not reside in the file system: " + resourceUrl);
		}
		
		try {
			return new File(toURI(resourceUrl).getSchemeSpecificPart());
		}
		
		catch (URISyntaxException ex) {
			// Fallback for URLs that are not valid URIs (should hardly ever happen).
			return new File(resourceUrl.getFile());
		}
	}
	
	public static URI toURI(URL url) throws URISyntaxException {
		return toURI(url.toString());
	}
	
	public static URI toURI(String location) throws URISyntaxException {
		return new URI(location.replace(" ", "%20"));
	}
	
	/**
     * Builds a URLClassLoader with all JAR files in the collection.
     */
    public static ClassLoader buildClassLoader(Collection<Resource> resources) throws IOException {
        List<URL> urls = new ArrayList<>();
        for (Resource resource : resources) {
        	String path = resource.getPath();
			if (path.endsWith(".jar")) {
				urls.add(new URL("jar", "", -1, resource.getFile().toURI().toURL().toString() + "!/"));
			}
        }
               
        return new URLClassLoader(
            urls.toArray(new URL[urls.size()]),
            Classes.getClassLoader()
        );
    }
}
