package org.openlca.app.util;

import org.openlca.app.resources.ImageType;

/**
 * A pop-up for information messages.
 * 
 * @author Georg Koester
 * 
 */
public class InformationPopup extends Popup {

	public InformationPopup(String message) {
		this(null, message);
	}

	public InformationPopup(String title, String message) {
		super(title, message);
		defaultTitle("Notification");
		popupShellImage(ImageType.INFO_ICON);
	}

	public static void show(final String message) {
		show(null, message);
	}

	public static void show(final String title, final String message) {
		new InformationPopup(title, message).show();
	}

}