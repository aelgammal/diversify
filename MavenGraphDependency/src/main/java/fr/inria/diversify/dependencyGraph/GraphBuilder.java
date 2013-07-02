package fr.inria.diversify.dependencyGraph;

import fr.inria.diversify.maven.DependencyTree;
import fr.inria.diversify.maven.util.ConsoleDependencyGraphDumper;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.graph.DependencyNode;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: Simon
 * Date: 6/17/13
 * Time: 2:22 PM
 */
public class GraphBuilder {



//    public MavenDependencyGraph buildGraphDependency(List<ArtifactInfo> artifacts) {
//        Set<String> mark = new HashSet<String>();
//        MavenDependencyGraph graph = new MavenDependencyGraph();
//        int i =0;
//        for(ArtifactInfo ai : artifacts) {
//            System.out.println("artifact nb: "+i++ +" MavenDependencyGraph size: "+graph.nodes.size());
//            if(!mark.contains(ai.groupId+":"+ai.artifactId+":"+ai.version)) {
//                DependencyTree dt = new DependencyTree();
//                try {
//                    DependencyNode tree = dt.getDependencyTree(ai);
//                    tree.accept(new ConsoleDependencyGraphDumper());
//                    tree.accept(new BuildGraphVisitor(graph, true));
//                    mark.addAll(allChildren(tree));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return graph;
//    }

    public MavenDependencyGraph buildGraphDependency(List<String> artifacts) {
        Set<String> mark = new HashSet<String>();
        MavenDependencyGraph graph = new MavenDependencyGraph();
        int i =0;
        for(String ai : artifacts) {
            if(!mark.contains(ai)) {
                DependencyTree dt = new DependencyTree();
                try {
                    DependencyNode tree = dt.getDependencyTree(ai);
                    tree.accept(new ConsoleDependencyGraphDumper());
                    tree.accept(new BuildGraphVisitor(graph, true));
                    mark.addAll(allChildren(tree));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return graph;
    }


    public MavenDependencyGraph forJSONFiles(String dir) throws JSONException, IOException {
        File file = new File(dir);
        MavenDependencyGraph g = new MavenDependencyGraph();
        for (File f : file.listFiles())  {

            if(f.getName().endsWith(".json")) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                StringBuffer sb = new StringBuffer();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                if (sb.length() != 0)
                    g.addGraph(new JSONArray(sb.toString()));
            }
        }
        return g;
    }

    protected Set<String> allChildren(DependencyNode node) {

        Set<String> children = new HashSet<String>();
        for(DependencyNode n : node.getChildren()) {
            Artifact a = n.getArtifact();
            children.add(a.getGroupId()+":"+a.getArtifactId()+":"+a.getVersion());
            children.addAll(allChildren(n));
        }
        return children;
    }
}
