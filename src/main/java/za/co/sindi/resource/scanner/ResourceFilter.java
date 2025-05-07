package za.co.sindi.resource.scanner;

import java.util.function.Predicate;

import za.co.sindi.resource.Resource;

/**
 * @author Buhake Sindi
 * @since 12 August 2024
 */
@FunctionalInterface
public interface ResourceFilter extends Predicate<Resource> {

}
