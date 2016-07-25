package org.opencabe.demo.psd.attributes;

import org.opencabe.attributes.ui.AbstractControlFactory;
import org.opencabe.attributes.ui.AttributeControl;

public class DemoPsdControlFactory extends AbstractControlFactory {

	public interface AttributeTypes {
		static public final String DEMO_COMBO_ATTRIBUTE = "DemoCombo";
	}

	protected AttributeControl caseDemoComboAttribute() {
		return new DemoComboAttributeControl(context, context.getProject());
	}

	protected AttributeControl doSwitch(String typeName) {
		switch (typeName) {
		case AttributeTypes.DEMO_COMBO_ATTRIBUTE:
			return caseDemoComboAttribute();
		}

		return null;
	}
}