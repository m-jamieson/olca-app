package org.openlca.core.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.openlca.core.application.Messages;
import org.openlca.core.model.RootEntity;
import org.openlca.ui.DataBinding;
import org.openlca.ui.UI;

/**
 * This is the general info section that each editor has: name, description,
 * etc.
 */
public class InfoSection {

	private RootEntity entity;
	private DataBinding binding;

	public InfoSection(RootEntity entity, DataBinding binding) {
		this.entity = entity;
		this.binding = binding;
	}

	public void render(Composite body, FormToolkit toolkit) {
		Composite composite = UI.formSection(body, toolkit,
				Messages.Common_GeneralInformation);
		Text nameText = UI.formText(composite, toolkit, Messages.Common_Name);
		binding.onString(entity, "name", nameText);
		Text descriptionText = UI.formMultiText(composite, toolkit,
				Messages.Common_Description);
		binding.onString(entity, "description", descriptionText);

		// TODO: a category link
	}

}
