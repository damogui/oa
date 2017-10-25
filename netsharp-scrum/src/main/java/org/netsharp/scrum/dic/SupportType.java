package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*支持类型*/
public enum SupportType implements IEnum{
	
	bug(1,"BUG"), product(2,"产品变更"), operation(3,"运维"), dayliy(4,"日常运营");

	private int value;
	private String text;

	SupportType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SupportType getItem(int value) {

		for (SupportType item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {

		return this.value;
	}
}
