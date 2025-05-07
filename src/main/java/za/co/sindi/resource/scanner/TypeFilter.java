package za.co.sindi.resource.scanner;

import java.util.function.Predicate;

/**
 * @author Buhake Sindi
 * @since 13 August 2024
 */
@FunctionalInterface
public interface TypeFilter extends Predicate<Class<?>> {

}
