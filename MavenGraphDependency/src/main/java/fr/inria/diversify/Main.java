package fr.inria.diversify;

import fr.inria.diversify.dependencyGraph.MavenDependencyGraph;
import fr.inria.diversify.dependencyGraph.GraphBuilder;
import fr.inria.diversify.stat.Stat;

import java.io.*;
import java.util.*;

/**
 * User: Simon
 * Date: 6/13/13
 * Time: 10:23 AM
 */
public class Main {

    public static void main( String[] args ) throws Exception
    {
        MavenDependencyGraph g;
        if(args.length < 2) {
//            "/Users/Simon/Documents/code/MiningMaven/result"
            g = (new GraphBuilder()).forJSONFiles(args[0]);
        }
        else {
            int bMin = Integer.parseInt(args[0]);
            int bMax = Integer.parseInt(args[1]);
            g = (new GraphBuilder()).buildGraphDependency(allArtifact("allArtifact").subList(bMin, bMax));
        }

//        final CentralIndex app = new CentralIndex();
//        app.buildCentralIndex();

        writeDot(g);
        writeStat(g);
       System.out.print(g.info());
    }

    public static void writeDot(MavenDependencyGraph g) throws IOException {
        String dot = g.toDot();
        FileWriter fw = new FileWriter("mgd.dot");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(dot);
        bw.close();
    }

    public static List<String> allArtifact(String fileName) throws IOException {
        List<String> artifacts = new ArrayList<String>();
        File f  = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();
        while (line != null) {
            artifacts.add(line);
            line = br.readLine();
        }
        return  artifacts;
    }

   public static void writeStat(MavenDependencyGraph g) throws IOException {
       Stat stat = new Stat(g);

       stat.writeGeneralStat("stat");
       stat.writeDependencyStat("org.jvnet.hudson.main", "hudson-core");
       stat.writeDependencyStat("org.mortbay.jetty", "jsp-2.0");
       stat.writeDependencyStat("org.mule", "mule-core");
       stat.writeDependencyStat("org.ow2.jonas", "jonas-services-api");
       stat.writeDependencyStat("dom4j", "dom4j");
   }

}
