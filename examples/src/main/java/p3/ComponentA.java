package p3;

import fr.irisa.diversify.annotations.MemoryLeak;
import fr.irisa.diversify.annotations.WhileTrueThread;
import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@Library(name = "DIVERSIFY")
@ComponentType
@Provides({
        @ProvidedPort(name = "sayHello", type = PortType.MESSAGE),
        @ProvidedPort(name = "saludar", type = PortType.MESSAGE)
})
@Requires({
        @RequiredPort(name = "askWorld", type = PortType.MESSAGE, optional = true)
})
public class ComponentA extends AbstractComponentType {

	public ComponentA() {
		
	}

    @Start
    public void start() {
        System.out.println("Starting component" + getName());
    }

    @Stop
    public void stop() {
        System.out.println("Stopping component" + getName());
    }

    @Port(name = "sayHello")
    @WhileTrueThread
    public void sayHello(Object text) {
        System.out.print("Hello ");
        if(isPortBinded("askWorld")) {
            getPortByName("askWorld", MessagePort.class).process(text);
        }
    }

    @Port(name = "saludar")
    @MemoryLeak
    public void saludar(Object text) {
        System.out.print("Hola ");
        if(isPortBinded("askWorld")) {
            getPortByName("askWorld", MessagePort.class).process(text);
        }
    }
}
