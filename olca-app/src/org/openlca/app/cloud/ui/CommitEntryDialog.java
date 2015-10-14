package org.openlca.app.cloud.ui;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.openlca.app.util.UI;

import com.greendelta.cloud.model.data.CommitDescriptor;

public class CommitEntryDialog extends FormDialog {

	private final List<CommitDescriptor> commits;
	private CommitEntryViewer viewer;

	public CommitEntryDialog(List<CommitDescriptor> commits) {
		super(UI.shell());
		this.commits = commits;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 600);
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		ScrolledForm form = UI.formHeader(mform, "#Fetched changes");
		FormToolkit toolkit = mform.getToolkit();
		Composite body = form.getBody();
		body.setLayout(new GridLayout());
		toolkit.paintBordersFor(body);
		UI.gridData(body, true, true);
		createCommitViewer(body, toolkit);
		form.reflow(true);
		viewer.setInput(commits);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}

	private void createCommitViewer(Composite parent, FormToolkit toolkit) {
		viewer = new CommitEntryViewer(parent);
	}

}
