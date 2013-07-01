package p3;

import org.kevoree.annotation.ComponentType;

import junit.framework.TestCase;
import fr.irisa.diversify.annotations.MemoryLeak;
import fr.irisa.diversify.annotations.Perforable;
import fr.irisa.diversify.annotations.WhileTrueThread;

@ComponentType
public class A {

	public A() {
		
	}

	public static void main(String[] args) {
		new A().foo();
	}
	
	TestCase a;

	@Perforable(step = 5)
	public void foo() {
		int j = 0;
		for (int i = 0; i < 100; i++) {
			j++;
		}
		System.err.println(j);

	}

	@MemoryLeak
	@WhileTrueThread
	public void foo1() {
		int j = 0;
		while (true) {
			for (int i = 0; i < 100; i++) {
				j++;
			}
			System.err.println(j);
		}

	}

	
}
