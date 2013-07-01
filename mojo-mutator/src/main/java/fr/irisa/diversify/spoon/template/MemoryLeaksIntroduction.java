package fr.irisa.diversify.spoon.template;

import java.util.ArrayList;
import java.util.List;

import spoon.reflect.declaration.CtExecutable;
import spoon.template.Local;
import spoon.template.StatementListTemplateParameter;
import spoon.template.Template;

/**
 * This statement list template defines a simple tracing code. This tracing is
 * generic and dynamically achieved. To see more optimized solutions, refer to
 * {@link TracingProcessorOptimized}.
 */
public class MemoryLeaksIntroduction extends StatementListTemplateParameter
		implements Template {
	
	/**
	 * Creates this template for a given executable.
	 */
	@Local
	public MemoryLeaksIntroduction(CtExecutable<?> e) {
	}

	
	List<Object> cache = new ArrayList<Object>();

	
	/**
	 * This is the code to be inserted at the begining of the logged method.
	 */
	@Local
	public void statements() {
		cache.add(new byte[1000000]);
	}
}
