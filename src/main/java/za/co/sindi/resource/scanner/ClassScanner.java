package za.co.sindi.resource.scanner;

import java.util.Set;

import za.co.sindi.resource.Resource;

/**
 * author Buhake Sindi
 * @since 13 August 2024
 */
public interface ClassScanner {

	public void addTypeFilter(TypeFilter typeFilter);
	public Set<Class<?>> scan(Resource resource);
}
