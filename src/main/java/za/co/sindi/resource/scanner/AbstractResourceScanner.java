/**
 * 
 */
package za.co.sindi.resource.scanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import za.co.sindi.resource.Resource;

/**
 * @author Bienfait Sindi
 * @since 08 January 2016
 *
 */
public abstract class AbstractResourceScanner implements ResourceScanner {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private List<ResourceFilter> resourceFilters = new ArrayList<>();
//	private ClassLoader classLoader;
	
	/**
	 * 
	 */
	protected AbstractResourceScanner() {
//		this(null);
		super();
		// TODO Auto-generated constructor stub
	}

//	/**
//	 * @param classLoader
//	 */
//	protected AbstractResourceScanner(ClassLoader classLoader) {
//		super();
//		this.classLoader = classLoader;
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.resource.scanner.ResourceScanner#addResourceFilter(za.co.sindi.resource.scanner.ResourceFilter)
	 */
	@Override
	public void addResourceFilter(ResourceFilter filter) {
		// TODO Auto-generated method stub
		if (filter != null) {
			resourceFilters.add(filter);
		}
	}

//	/* (non-Javadoc)
//	 * @see za.co.sindi.oauth.server.scanner.ResourceScanner#getClassLoader()
//	 */
//	@Override
//	public ClassLoader getClassLoader() {
//		// TODO Auto-generated method stub
//		if (classLoader == null) {
//			classLoader = Classes.getClassLoader();
//		}
//		
//		return classLoader;
//	}

	/* (non-Javadoc)
	 * @see za.co.sindi.resource.scanner.ResourceScanner#scan()
	 */
	@Override
	public Collection<Resource> scan() throws ScanningException {
		// TODO Auto-generated method stub
		if (resourceFilters.isEmpty()) {
			resourceFilters.add(resource -> true);
		}
		
		Collection<Resource> resources = new ArrayList<Resource>();
		scan(resources);
		return Collections.unmodifiableCollection(resources);
	}
	
	protected boolean resourceFilterMatch(Resource resource) {
		for (ResourceFilter resourceFilter : resourceFilters) {
			if (resourceFilter.test(resource)) return true;
		}
		
		return false;
	}
	
	protected abstract void scan(Collection<Resource> resources) throws ScanningException;
}
