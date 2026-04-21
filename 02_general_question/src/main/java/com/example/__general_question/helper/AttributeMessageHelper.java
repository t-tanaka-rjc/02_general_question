package com.example.__general_question.helper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * messages.propertiesからメッセージを取得するヘルパークラス.
 * 
 * @author towa_tanaka
 */
@Component
public class AttributeMessageHelper {

	@Autowired
	private MessageSource messageSource;

	/**
	 * messages.propertiesの値を返す.
	 * 
	 * @param message messages.propertiesのキー
	 * @return 引数で渡ってきたmessages.propertiesのキーに紐づく値
	 */
	public String getPropertieMessage(String message) {
		return messageSource.getMessage(message, null, Locale.JAPAN);
	}

}
