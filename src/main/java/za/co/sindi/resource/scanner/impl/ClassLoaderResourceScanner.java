/**
 * 
 */
package za.co.sindi.resource.scanner.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;

import za.co.sindi.resource.Resource;
import za.co.sindi.resource.impl.FileResource;
import za.co.sindi.resource.scanner.PathAwareResourceScanner;
import za.co.sindi.resource.scanner.ScanningException;

/**
 * @author Bienfait Sindi
 * @since 28 October 2014
 *
 */
public class ClassLoaderResourceScanner extends PathAwareResourceScanner {
	
	private ClassLoader classLoader;

	/**
	 * 
	 */
	public ClassLoaderResourceScanner() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param classLoader
	 */
	public ClassLoaderResourceScanner(ClassLoader classLoader) {
		super();
		this.classLoader = classLoader;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.server.scanner.PathAwareResourceScanner#scan(java.lang.String, java.util.Collection)
	 */
	@Override
	protected void scan(String path, Collection<Resource> resources) throws ScanningException {
		// TODO Auto-generated method stub
		try {
			Enumeration<URL> enumResources = classLoader.getResources(path);
			if (enumResources != null) {
				while (enumResources.hasMoreElements()) {
					URL resource = enumResources.nextElement();
					File file = new File(resource.getPath());
					if (file.isDirectory()) {
						scan(file.getAbsolutePath(), resources);
					} else {
						Resource fileResource = new FileResource(file);
						if (resourceFilterMatch(fileResource))
							resources.add(fileResource);
//						resources.add(new FileResource(file));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ScanningException("Error while scanning for resources.", e);
		}
	}
}
