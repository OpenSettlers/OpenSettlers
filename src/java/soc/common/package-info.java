/**
 * All code shared between the client and the server goes into this package.
 * Classes can all be thread-unsafe, thread safety is implemented in server
 * specific classes. When adding a new subpackage in this packages' root, make
 * sure to add a reference to it in the <code>OSCommon.gwt.xml</code> file.<br />
 * 
 * @see soc.common.internationalization
 *      The Internationalization package adds support for translation of
 *      constants and messages. As GWT does not natively support server-side
 *      usage of i18n, gwt_i18n_server is used to support serverside
 *      internationalization.
 */
package soc.common;