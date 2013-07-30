package org.openlca.core.editors.analyze;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.openlca.app.UI;
import org.openlca.core.application.Messages;

/** Table viewer for process contributions. */
class ProcessContributionViewer extends TableViewer {

	/** Index of the contribution column. */
	static final int CONTRIBUTION = 0;

	/** Index of the column with the process names. */
	static final int NAME = 1;

	/** Index of the column with the total amounts. */
	static final int TOTAL_AMOUNT = 2;

	/** Index of the column with the single amounts. */
	static final int SINGLE_AMOUNT = 3;

	/** Index of the column with the units. */
	static final int UNIT = 4;

	private String[] columnLabels = { Messages.Analyze_Contribution,
			Messages.Common_Process, Messages.Analyze_TotalAmount,
			Messages.Analyze_SingleAmount, Messages.Common_Unit };

	private double[] columnWidths = { 0.17, 0.37, 0.17, 0.17, 0.10 };

	public ProcessContributionViewer(Composite parent) {
		super(parent);
		createColumns();
		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		setContentProvider(ArrayContentProvider.getInstance());
		setLabelProvider(new ProcessContributionLabel());
		UI.bindColumnWidths(table, columnWidths);
	}

	private void createColumns() {
		for (int i = 0; i < columnLabels.length; i++) {
			TableViewerColumn column = new TableViewerColumn(this, SWT.NONE);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			column.getColumn().setText(columnLabels[i]);
		}
	}

}