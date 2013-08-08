package org.openlca.ilcd.network.rcp.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.openlca.core.application.navigation.CategoryNavigationElement;
import org.openlca.core.application.navigation.INavigationElement;
import org.openlca.core.application.navigation.ModelNavigationElement;
import org.openlca.core.model.Category;
import org.openlca.core.model.Process;
import org.openlca.core.model.ProductSystem;

/**
 * The navigation tree-filter for the ILCD network export. Allows the selection
 * of processes and product systems.
 * 
 * TODO: see the class CategoryViewerFilter in io.ui
 */
public class NavigationTreeFilter extends ViewerFilter {

	private final Class<?> PROCESS_CLASS = Process.class;
	private final Class<?> SYSTEM_CLASS = ProductSystem.class;
	private final String PROCESS_CLASS_NAME = Process.class.getCanonicalName();
	private final String SYSTEM_CLASS_NAME = ProductSystem.class
			.getCanonicalName();

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof INavigationElement)
			return select((INavigationElement) element);
		return false;
	}

	private boolean select(INavigationElement element) {
		if (element instanceof CategoryNavigationElement)
			return validCategory((CategoryNavigationElement) element);
		if (element instanceof ModelNavigationElement)
			return validModel((ModelNavigationElement) element);
		return hasModelChilds(element);
	}

	private boolean validCategory(CategoryNavigationElement element) {
		Category category = (Category) element.getData();
		if (category == null)
			return false;
		String modelClass = category.getComponentClass();
		return (modelClass.equals(PROCESS_CLASS_NAME) || modelClass
				.equals(SYSTEM_CLASS_NAME)) && hasModelChilds(element);
	}

	private boolean validModel(ModelNavigationElement element) {
		Object model = element.getData();
		return PROCESS_CLASS.isInstance(model)
				|| SYSTEM_CLASS.isInstance(model);
	}

	private boolean hasModelChilds(INavigationElement element) {
		for (INavigationElement child : element.getChildren(true)) {
			if ((child instanceof ModelNavigationElement)
					&& validModel((ModelNavigationElement) child))
				return true;
			else if (hasModelChilds(child))
				return true;
		}
		return false;
	}

}