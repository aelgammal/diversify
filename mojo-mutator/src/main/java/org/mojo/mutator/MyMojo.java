package org.mojo.mutator;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import main.Test;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal diversify
 * @phase generate-sources
 * @requiresDependencyResolution compile
 * @requiresDependencyCollection compile
 */
public class MyMojo extends AbstractMojo {
	/**
	 * The directory root under which processor-generated source files will be
	 * placed; files are placed in subdirectories based on package namespace.
	 * This is equivalent to the <code>-s</code> argument for apt.
	 * 
	 * @parameter 
	 *            default-value="${project.build.directory}/generated-sources/kevoreeMut"
	 */
	private File sourceOutputDirectory;

	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 */
	private MavenProject project;
	

    /**
     * Controls whether or not the output directory is added to compilation
     * @parameter expression="${addOutputDirectoryToCompilationSources}"
     * default-value="true"
     */
    private Boolean addOutputDirectoryToCompilationSources;

	
    private void addOutputToSourcesIfNeeded() {
        final Boolean add = addOutputDirectoryToCompilationSources;
        if (add == null || add.booleanValue()) {
            getLog().info("Source directory: " + sourceOutputDirectory + " added");
            addCompileSourceRoot(project, sourceOutputDirectory.getAbsolutePath());
        }
    }
    protected void addCompileSourceRoot(MavenProject project, String dir) {
        project.addCompileSourceRoot(dir);
    }

    private void ensureOutputDirectoryExists() {
        final File f = sourceOutputDirectory;
        if (!f.exists()) {
            f.mkdirs();
        }
        if (!sourceOutputDirectory.exists()) {
        	sourceOutputDirectory.mkdirs();
        }
    }
	
	private void delete(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}
		} else {
			file.delete();
		}
	}
    

	public void execute() throws MojoExecutionException {

		List<File> files = new ArrayList<File>();
		for (String s : project.getCompileSourceRoots()) {
			files.add(new File(s));
		}
		ensureOutputDirectoryExists();
		try {
			delete(sourceOutputDirectory);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ensureOutputDirectoryExists();
		addOutputToSourcesIfNeeded();
		
		ClassLoader old = Thread.currentThread().getContextClassLoader();
		Collection<URL> urls = new ArrayList<URL>();
		String old_classpath = System.getProperty("java.class.path");
		StringBuffer newclasspath = new StringBuffer();
		int i = 0;		
		
			try {
				for (String artifact : project.getCompileClasspathElements()){
					
					try {
						urls.add(new File(artifact).toURI().toURL());
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					newclasspath.append(artifact);
					i++;
					if (i<project.getDependencyArtifacts().size())
						newclasspath.append(File.pathSeparatorChar);
					}
			} catch (DependencyResolutionRequiredException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try {
			urls.add(new File(project.getBuild().getOutputDirectory()).toURI()
					.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
			
		System.setProperty("java.class.path",newclasspath.toString());
		Thread.currentThread()
				.setContextClassLoader(
						new URLClassLoader( urls.toArray(new java.net.URL[urls.size()]),
								old));
		new Test(files, sourceOutputDirectory);
		System.setProperty("java.class.path",old_classpath);
		Thread.currentThread().setContextClassLoader(old);
		
		project.addCompileSourceRoot(sourceOutputDirectory.getAbsolutePath());
	}
}
