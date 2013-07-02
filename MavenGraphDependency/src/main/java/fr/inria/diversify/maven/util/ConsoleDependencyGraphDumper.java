package fr.inria.diversify.maven.util;

import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.graph.DependencyVisitor;

import java.io.PrintStream;

/**
 * User: Simon
 * Date: 6/12/13
 * Time: 3:48 PM
 */
public class ConsoleDependencyGraphDumper implements DependencyVisitor
    {

        private PrintStream out;

        private String currentIndent = "";

        public ConsoleDependencyGraphDumper()
        {
            this( null );
        }

        public ConsoleDependencyGraphDumper(PrintStream out )
        {
            this.out = ( out != null ) ? out : System.out;
        }

    public boolean visitEnter( DependencyNode node )
    {
        out.println( currentIndent + node );
        if ( currentIndent.length() <= 0 )
        {
            currentIndent = "+- ";
        }
        else
        {
            currentIndent = "|  " + currentIndent;
        }
        return true;
    }

    public boolean visitLeave( DependencyNode node )
    {
        currentIndent = currentIndent.substring( 3, currentIndent.length() );
        return true;
    }

}