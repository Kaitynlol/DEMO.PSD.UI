package org.opencabe.demo.psd.workbench;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.opencabe.demo.psd.editparts.DemoPSDEditPartFactory;
import org.opencabe.diagrams.editparts.ComposedAdapterEditPartFactory;
import org.opencabe.diagrams.editparts.DiagramsEditPartFactory;
import org.opencabe.workbench.app.ModelProvider;
import org.opencabe.workbench.editors.ActivateToolAction;
import org.opencabe.workbench.editors.DiagramEditor;
import org.opencabe.workbench.editors.EObjectCreationFactory;
import org.opencabe.workbench.editors.PersistantCreationTool;
import org.opencabe.workbench.editors.RepresentantCreationFactory;
import org.scopio.demo.psd.DemoPSDPackage;
import org.scopio.demo.psd.provider.DemoPSDImageLocator;
import org.scopio.diagrams.Diagram;
import org.scopio.project.Project;

public class DemoPSDDiagramEditor extends DiagramEditor {

	private static final String DEMO_PSD_PLUGIN_ID = "org.scopio.demo.psd";

	private ToolEntry TransactionBoundary;
	private ToolEntry actorEntry;
	private ToolEntry startEntry;
	private ToolEntry startingStepEntry;
	private ToolEntry endingStepEntry;
	private ToolEntry executeEntry;
	private ToolEntry responceEntry;
	private ToolEntry waitingEntry;

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		ComposedAdapterEditPartFactory factory = new ComposedAdapterEditPartFactory();
		factory.addFactory(new DiagramsEditPartFactory());
		factory.addFactory(new DemoPSDEditPartFactory());

		GraphicalViewer viewer = this.getGraphicalViewer();
		viewer.setEditPartFactory(factory);
	}

	@Override
	protected PaletteRoot initPalette(PaletteRoot root) {
		Diagram<?> diagram = getDiagram();
		Project project = ModelProvider.locate(getSite()).getProject();

		PaletteGroup diagramGroup = new PaletteGroup("DEMO Diagrams");

		root.add(diagramGroup);

		TransactionBoundary = new CreationToolEntry("Transaction name",
				"Transaction", new RepresentantCreationFactory(
						DemoPSDPackage.Literals.TR, project.getEntitiesScope(),
						diagram.getScope()), null, null);
		TransactionBoundary.setToolClass(PersistantCreationTool.class);
		TransactionBoundary
				.setSmallIcon(AbstractUIPlugin
						.imageDescriptorFromPlugin(
								DEMO_PSD_PLUGIN_ID,
								DemoPSDImageLocator.ICONS_FOLDER
										+ DemoPSDImageLocator.ICON_TRANSACTION_BOUNDARY));
		TransactionBoundary
				.setLargeIcon(AbstractUIPlugin
						.imageDescriptorFromPlugin(
								DEMO_PSD_PLUGIN_ID,
								DemoPSDImageLocator.ICONS_FOLDER
										+ DemoPSDImageLocator.ICON_TRANSACTION_BOUNDARY));
		diagramGroup.add(TransactionBoundary);

		actorEntry = new CreationToolEntry("Actor", "Actor",
				new RepresentantCreationFactory(
						DemoPSDPackage.Literals.ACTOR_BOUNDARY, project
								.getEntitiesScope(), diagram.getScope()), null,
				null);
		actorEntry.setToolClass(PersistantCreationTool.class);
		actorEntry.setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_ACTOR_BOUNDARY));
		actorEntry.setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_ACTOR_BOUNDARY));
		diagramGroup.add(actorEntry);

		// startEntry = new CreationToolEntry("Start", "Start",
		// new RepresentantCreationFactory(DemoPSDPackage.Literals.START,
		// project.getEntitiesScope(), diagram.getScope()), null,
		// null);
		// startEntry.setToolClass(PersistantCreationTool.class);
		// startEntry.setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_START));
		// startEntry.setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_START));
		// diagramGroup.add(startEntry);
		//
		// startingStepEntry = new CreationToolEntry("StartingStep",
		// "StartingStep", new RepresentantCreationFactory(
		// DemoPSDPackage.Literals.STARTING_PROCESS_STEP,
		// project.getEntitiesScope(), diagram.getScope()), null,
		// null);
		// startingStepEntry.setToolClass(PersistantCreationTool.class);
		// startingStepEntry
		// .setSmallIcon(AbstractUIPlugin
		// .imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID,
		// DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_STARTING_PROCESS_STEP));
		// startingStepEntry
		// .setLargeIcon(AbstractUIPlugin
		// .imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID,
		// DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_STARTING_PROCESS_STEP));
		// diagramGroup.add(startingStepEntry);
		//
		// endingStepEntry = new CreationToolEntry("EndingStep", "EndingStep",
		// new RepresentantCreationFactory(
		// DemoPSDPackage.Literals.ENDING_PROCESS_STEP, project
		// .getEntitiesScope(), diagram.getScope()), null,
		// null);
		// endingStepEntry.setToolClass(PersistantCreationTool.class);
		// endingStepEntry
		// .setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_ENDING_PROCESS_STEP));
		// endingStepEntry
		// .setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_ENDING_PROCESS_STEP));
		// diagramGroup.add(endingStepEntry);
		//
		// executeEntry = new CreationToolEntry("ExecuteTransaction",
		// "ExecuteTransaction", new RepresentantCreationFactory(
		// DemoPSDPackage.Literals.EXECUTE_TRANSACTION,
		// project.getEntitiesScope(), diagram.getScope()), null,
		// null);
		// executeEntry.setToolClass(PersistantCreationTool.class);
		// executeEntry.setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_EXECUTE_TRANSACTION));
		// executeEntry.setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
		// DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
		// + DemoPSDImageLocator.ICON_EXECUTE_TRANSACTION));
		// diagramGroup.add(executeEntry);

		responceEntry = new ConnectionCreationToolEntry("ResponseLink", "Link",
				new EObjectCreationFactory(
						DemoPSDPackage.Literals.RESPONCE_LINK), null, null);
		responceEntry.setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_RESPONSE_LINK));
		responceEntry.setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_RESPONSE_LINK));
		diagramGroup.add(responceEntry);

		waitingEntry = new ConnectionCreationToolEntry(
				"WaitingLink",
				"Link",
				new EObjectCreationFactory(DemoPSDPackage.Literals.WAITING_LINK),
				null, null);
		waitingEntry.setSmallIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_WAITING_LINK));
		waitingEntry.setLargeIcon(AbstractUIPlugin.imageDescriptorFromPlugin(
				DEMO_PSD_PLUGIN_ID, DemoPSDImageLocator.ICONS_FOLDER
						+ DemoPSDImageLocator.ICON_WAITING_LINK));
		diagramGroup.add(waitingEntry);

		return root;
	}

}
