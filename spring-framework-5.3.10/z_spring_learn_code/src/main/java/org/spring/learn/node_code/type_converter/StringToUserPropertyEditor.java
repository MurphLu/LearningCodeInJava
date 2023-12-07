package org.spring.learn.node_code.type_converter;

import org.spring.learn.config.AppConfig;
import org.spring.learn.service.UserService;
import org.spring.learn.pojo.User;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * provided by JDK
 * It used for @Value() annotation for User fields
 * @Value() will provide a String value for User->name
 * with the CustomEditorConfigurer bean in AppConfig
 * when spring context initial,
 * it will call this function to init a user
 * and string in @Value() will be set to its name
 * define:
 * @see AppConfig#customEditorConfigurer()
 * usage:
 * {@link org.spring.learn.service.typeConverter.TypeConverterCode#user}
 */
public class StringToUserPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User user = new User();
		user.setName(text);
		this.setValue(user);
	}
}
