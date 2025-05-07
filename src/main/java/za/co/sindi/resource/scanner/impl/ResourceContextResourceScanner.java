/**
 * 
 */
package za.co.sindi.resource.scanner.impl;

import java.util.Collection;
import java.util.Set;

import za.co.sindi.resource.Resource;
import za.co.sindi.resource.context.ResourceContext;
import za.co.sindi.resource.impl.ResourceContextResource;
import za.co.sindi.resource.scanner.PathAwareResourceScanner;
import za.co.sindi.resource.scanner.ScanningException;

/**
 * @author Bienfait Sindi
 * @since 29 October 2014
 *
 */
public class ResourceContextResourceScanner extends PathAwareResourceScanner {

	private final ResourceContext context;
	
	/**
	 * @param context
	 */
	public ResourceContextResourceScanner(ResourceContext context) {
		super();
		if (context == null) {
			throw new IllegalArgumentException("No ResourceContext was provided.");
		}
		
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.PathAwareResourceScanner#scan(java.lang.String, java.util.Collection)
	 */
	@Override
	protected void scan(String rootPath, Collection<Resource> resources) throws ScanningException {
		// TODO Auto-generated method stub
		Set<String> dirs = context.getResourcePaths(rootPath);
		if (dirs != null) {
			for (String path : dirs) {
				if (path.endsWith("/")) {
					scan(path, resources);
				} else {
					Resource resource = new ResourceContextResource(path, context);
					if (resourceFilterMatch(resource))
						resources.add(resource);
//					resources.add(new ResourceContextResource(path, context));
				}
			}
		}
	}
}
