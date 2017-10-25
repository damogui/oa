package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.DecimalBox;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryDecimalBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		NumberQueryControl control = new NumberQueryControl();
		control.setQueryItem(queryItem);
		control.controlType = DecimalBox.class;
		control.jsControlType = "NumberBoxQueryItem";
		return control;
	}

}
