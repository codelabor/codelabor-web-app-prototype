package org.codelabor.system.security.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class SimpleGrantedAuthoritiesPropertiesEditor extends
		PropertyEditorSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.propertyeditors.PropertiesEditor#setAsText(
	 * java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(new SimpleGrantedAuthority(text));
	}

}
