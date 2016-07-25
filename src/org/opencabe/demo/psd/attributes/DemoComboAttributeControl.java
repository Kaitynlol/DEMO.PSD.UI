package org.opencabe.demo.psd.attributes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.opencabe.attributes.ui.AttributeControl;
import org.opencabe.attributes.ui.AttributesContext;
import org.scopio.attributes.AttributesProvider;
import org.scopio.demo.psd.ActorBoundary;
import org.scopio.demo.psd.DemoPSDDiagram;
import org.scopio.demo.psd.TR;
import org.scopio.demo.psd.impl.ActorBoundaryImpl;
import org.scopio.entities.EntityCollection;
import org.scopio.project.Project;

public class DemoComboAttributeControl extends AttributeControl {
	protected Project project;
	private DemoPSDDiagram psd;
	protected EntityCollection collection;
	protected Combo combo;
	protected List<ActorBoundary> contentActors = new ArrayList<ActorBoundary>();
	AttributesContext context;

	public DemoComboAttributeControl(AttributesContext context, Project project) {
		super(context,
				DemoPsdControlFactory.AttributeTypes.DEMO_COMBO_ATTRIBUTE);
		this.context = context;
		this.project = project;
		this.psd = (DemoPSDDiagram) context.getTargets().get(0).eContainer();
	}

	@Override
	public void createControl(Composite parent,
			List<? extends AttributesProvider> providers) {
		super.createControl(parent, providers);

		Label label = new Label(parent, SWT.NONE);
		label.setText(getAttribute().getLabel());

		collection = project.getContent().getEntityCollection();
		combo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		context.getTargets().get(0);
		fillComboBox();
		setComboSelection();

		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onNewSelection();
			}
		});

	}

	protected void setComboSelection() {
		if (getOldValue() == null)
			combo.select(-1);
		else
			combo.select(getValueIndex((ActorBoundary) getOldValue()));
	}

	private int getValueIndex(ActorBoundary value) {
		for (int i = 0; i < contentActors.size(); i++) {
			String type = contentActors.get(i).getID();

			if (type.equals(value.getID())) {
				return i;
			}
		}

		return -1; // The value is not listed in attribute's possible values.
	}

	private void onNewSelection() {

		changeAttributesValue(contentActors.get(combo.getSelectionIndex()));
	}

	private void fillComboBox() {
		for (int i = 0; i < psd.getChildren().size(); i++) {
			if (psd.getChildren().get(i) instanceof ActorBoundary) {
				combo.add(((ActorBoundary) psd.getChildren().get(i)).getID());
				contentActors.add((ActorBoundary) psd.getChildren().get(i));
			}
		}
	}
	//
	// protected void setBoxValue() {
	// if (getOldValue() == null)
	// box.setText("");
	// else
	// box.setText(getOldValue().toString());
	// }
	//
	// @Override
	// public void attributeChanged(Attribute attribute, Object oldValue,
	// Object newValue) {
	// super.attributeChanged(attribute, oldValue, newValue);
	//
	// setBoxValue();
	// }
}
