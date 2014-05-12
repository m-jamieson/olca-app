package org.openlca.app.editors.graphical.command;

import org.eclipse.gef.commands.Command;
import org.openlca.app.Messages;
import org.openlca.app.editors.graphical.model.ConnectionLink;
import org.openlca.app.editors.graphical.model.ProcessNode;
import org.openlca.app.editors.graphical.model.ProductSystemNode;
import org.openlca.core.model.ProcessLink;
import org.openlca.core.model.ProductSystem;

public class CreateLinkCommand extends Command {

	private ConnectionLink link;
	private ProcessLink processLink;
	private ProcessNode sourceNode;
	private ProcessNode targetNode;
	private boolean startedFromSource;
	private long flowId;

	CreateLinkCommand() {

	}

	public void setStartedFromSource(boolean startedFromSource) {
		this.startedFromSource = startedFromSource;
	}

	public boolean isStartedFromSource() {
		return startedFromSource;
	}

	@Override
	public boolean canExecute() {
		if (sourceNode == null)
			return false;
		if (targetNode == null)
			return false;
		return flowId != 0;
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void execute() {
		ProductSystemNode systemNode = sourceNode.getParent();
		ProductSystem system = systemNode.getProductSystem();
		processLink = getProcessLink();
		system.getProcessLinks().add(processLink);
		systemNode.reindexLinks();
		link = getLink();
		link.link();
		systemNode.getEditor().setDirty(true);
	}

	private ProcessLink getProcessLink() {
		if (processLink == null)
			processLink = new ProcessLink();
		if (targetNode != null)
			processLink.setRecipientId(targetNode.getProcess().getId());
		if (sourceNode != null)
			processLink.setProviderId(sourceNode.getProcess().getId());
		processLink.setFlowId(flowId);
		return processLink;
	}

	@Override
	public String getLabel() {
		return Messages.Systems_ProcessLinkCreateCommand_Text;
	}

	@Override
	public void redo() {
		ProductSystemNode systemNode = sourceNode.getParent();
		link.link();
		ProductSystem system = systemNode.getProductSystem();
		system.getProcessLinks().add(processLink);
		systemNode.getEditor().setDirty(true);
		systemNode.reindexLinks();
	}

	@Override
	public void undo() {
		ProductSystemNode systemNode = sourceNode.getParent();
		ProductSystem system = systemNode.getProductSystem();
		system.getProcessLinks().remove(processLink);
		link.unlink();
		systemNode.getEditor().setDirty(true);
		systemNode.reindexLinks();
	}

	public void setSourceNode(ProcessNode sourceNode) {
		this.sourceNode = sourceNode;
	}

	public void setTargetNode(ProcessNode targetNode) {
		this.targetNode = targetNode;
	}

	void setFlowId(long flowId) {
		this.flowId = flowId;
	}

	public ProcessNode getSourceNode() {
		return sourceNode;
	}

	public ProcessNode getTargetNode() {
		return targetNode;
	}

	public long getFlowId() {
		return flowId;
	}

	public ConnectionLink getLink() {
		if (link == null)
			link = new ConnectionLink();
		link.setProcessLink(getProcessLink());
		link.setSourceNode(sourceNode);
		link.setTargetNode(targetNode);
		return link;
	}

}
