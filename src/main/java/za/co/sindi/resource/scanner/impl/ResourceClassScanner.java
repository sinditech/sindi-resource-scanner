package za.co.sindi.resource.scanner.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.commons.utils.Classes;
import za.co.sindi.resource.Resource;
import za.co.sindi.resource.scanner.ClassScanner;
import za.co.sindi.resource.scanner.TypeFilter;

/**
 * author Buhake Sindi
 * @since 13 August 2024
 */
public class ResourceClassScanner implements ClassScanner {
	
	private final Logger LOGGER = Logger.getLogger(ResourceClassScanner.class.getName());
	private static final String JAVA_JAR_SUFFIX = ".jar";
	private static final String JAVA_CLASS_SUFFIX = ".class";
	private List<TypeFilter> typeFilters = new ArrayList<>();
	private ClassLoader classLoader;
	
//	/**
//	 * 
//	 */
//	public ResourceClassScanner() {
//		this(Classes.getClassLoader());
//	}

	/**
	 * @param classLoader
	 */
	public ResourceClassScanner(ClassLoader classLoader) {
		super();
		this.classLoader = Objects.requireNonNull(classLoader, "A ClassLoader is required.");
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.resource.scanner.ClassScanner#addTypeFilter(za.co.sindi.resource.scanner.TypeFilter)
	 */
	@Override
	public void addTypeFilter(TypeFilter typeFilter) {
		// TODO Auto-generated method stub
		if (typeFilter != null) {
			typeFilters.add(typeFilter);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.resource.scanner.ClassScanner#scan(za.co.sindi.resource.Resource)
	 */
	@Override
	public Set<Class<?>> scan(Resource resource) {
		// TODO Auto-generated method stub
		if (typeFilters.isEmpty()) 
			typeFilters.add(filter -> true);
		
		Set<Class<?>> classes = new LinkedHashSet<>();
		try {
			String path = resource.getPath();
			if (path.endsWith(JAVA_JAR_SUFFIX)) {
				if (LOGGER.isLoggable(Level.INFO)) {
					LOGGER.info("Scanning JAR " + resource.getPath());
				}
				
				scanJarEntries(new URL("jar", "", -1, resource.getFile().toURI().toURL().toString() + "!/"), classes);
			} else if (path.endsWith(JAVA_CLASS_SUFFIX)) {
				String className = null;
				
				if (path.contains("WEB-INF/classes/")) {
					className = path.substring(path.indexOf("WEB-INF/classes/") + 16);
				} else if (path.contains("classes/")) {
					className = path.substring(path.indexOf("classes/") + 8);
				} else {
					className = path;
				}
				
				filterMatchAndSetClass(className, classLoader, classes);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new UncheckedIOException(e);
		}
		
		return Collections.unmodifiableSet(classes);
	}
		
	private void scanJarEntries(URL jarURL, Set<Class<?>> classes) throws IOException {
		JarURLConnection jarConn = (JarURLConnection) jarURL.openConnection();
		JarFile jarFile = jarConn.getJarFile();
		Enumeration<JarEntry> entries = jarFile.entries();
//		URLClassLoader classLoader = new URLClassLoader(new URL[] {jarURL});
		while (entries != null && entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if (entry.isDirectory()) continue;
			
			String name = entry.getName();
//			if (name.contains("/")) continue;
			
			if (name.endsWith(JAVA_CLASS_SUFFIX)) {
				filterMatchAndSetClass(name, classLoader, classes);
			}
		}
		
		//Finally
//		classLoader.close();
	}
	
	private void filterMatchAndSetClass(final String className, final ClassLoader classLoader, final Set<Class<?>> classes) {
		String _className = cleanClassName(className, JAVA_CLASS_SUFFIX);
		if (shouldScanClass(_className)) {
			Class<?> clazz;
			try {
				clazz = Classes.getClass(classLoader, _className, false);
				if (typeFilterMatch(clazz)) {
					classes.add(clazz);
					LOGGER.info("Found matching class '" + _className + "'.");
				}
			} catch (ClassNotFoundException | LinkageError e) {
				// TODO Auto-generated catch block
				LOGGER.warning("Failed to find class of type '" + _className + "' (reason, " + e.getClass().getName() + ": " + e.getLocalizedMessage() + ").");
			}
		}
	}
	
	private String cleanClassName(String className, String endSuffix) {
		return className.substring(0, className.length() - endSuffix.length()).replace("\\", ".").replace("/", ".");
	}
	
	private boolean shouldScanClass(String className) {
//		LOGGER.info("Class name: " + className);
		return (!className.startsWith("java.") && 
				!className.startsWith("javax.") && 
				!className.startsWith("jdk.") && 
				!className.startsWith("com.sun.") && 
				!className.startsWith("sun.") && 
				!className.startsWith("jakarta.") && 
				!className.startsWith("META-INF.") && 
				!className.endsWith("package-info") && 
				!className.endsWith("module-info"));
	}
	
	private boolean typeFilterMatch(Class<?> clazz) {
		for (TypeFilter typeFilter : typeFilters) {
			if (typeFilter.test(clazz)) return true;
		}
		
		return false;
	}
}
