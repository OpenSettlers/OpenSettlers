/**
 * 
 */
package org.soc.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Stub annotation to tag code for CitiesKnights ruleset
 * 
 * @see <a
 *      href="http://www.ibm.com/developerworks/library/j-annotate2.html">Annotations
 *      in Tiger, Part 2: Custom annotations</a>
 */

// it may be put onto any element. Parent elements tag all child elements
// cumulatively
@Target(
    { ElementType.TYPE, // Class, interface, or enum (but not annotation)
            ElementType.FIELD, // Field (including enumerated values)
            ElementType.METHOD, // Method (does not include constructors)
            ElementType.PARAMETER, // Method parameter
            ElementType.CONSTRUCTOR, // Constructor
            ElementType.LOCAL_VARIABLE, // Local variable or catch clause
            ElementType.ANNOTATION_TYPE, // Annotation Types (meta-annotations)
            ElementType.PACKAGE })
// Java package
// No need to keep this annotation at runtime
@Retention(RetentionPolicy.SOURCE)
// Mirror kitteh looks in mirror
@Standard
public @interface Standard
{

}
