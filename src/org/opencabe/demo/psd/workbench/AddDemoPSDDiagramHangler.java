package org.opencabe.demo.psd.workbench;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.ui.handlers.HandlerUtil;
import org.opencabe.workbench.app.ModelProvider;
import org.scopio.demo.psd.commands.DemoPSDCommandFactory;

public class AddDemoPSDDiagramHangler extends AbstractHandler {

	  @Override
	  public Object execute(ExecutionEvent event) throws ExecutionException {
	    ModelProvider modelProvider = ModelProvider.locate(HandlerUtil.getActiveWorkbenchWindow(event));
	    DemoPSDCommandFactory factory = new DemoPSDCommandFactory(modelProvider.getProject());
	    
	    Command cmd = factory.createAddDemoPSDDiagramCommand();
	    modelProvider.getProject().getEditingDomain().getCommandStack().execute(cmd);
	    
	    return null;
	  }

}

