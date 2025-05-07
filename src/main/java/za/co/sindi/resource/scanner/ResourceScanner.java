/**
 * 
 */
package za.co.sindi.resource.scanner;

import java.util.Collection;

import za.co.sindi.resource.Resource;

/**
 * @author Bienfait Sindi
 * @since 27 October 2014
 *
 */
public interface ResourceScanner {

	public void addResourceFilter(ResourceFilter filter);
	public Collection<Resource> scan() throws ScanningException;
	public ClassLoader getClassLoader();
}
