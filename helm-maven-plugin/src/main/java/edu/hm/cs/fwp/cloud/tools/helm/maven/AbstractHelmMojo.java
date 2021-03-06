/*
 * at41-tools-kubectl-maven-plugin:AbstractHelmMojo.java
 * (c) Copyright msg systems ag Automotive Technology 2019
 */
package edu.hm.cs.fwp.cloud.tools.helm.maven;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;

/**
 * Common base implementation of all Mojos running Helm.
 * 
 * @author theism
 * @version 1.0
 * @since 15.02.2019
 */
public abstract class AbstractHelmMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project.build.directory}", readonly = true, required=false)
	protected File target;

	@Parameter(defaultValue="${project}",required=true, readonly=true)
	protected MavenProject project;

	@Parameter( defaultValue = "${session}", required = true, readonly = true )
	protected MavenSession session;

	@Parameter(property = "helm.releaseName", readonly = true, required=false)
	protected String helmReleaseName;

	protected void info(String msg){
		getLog().info(msg);
	}

	protected void warn(String msg) { getLog().warn(msg); }

	protected void error(String msg){
		getLog().error(msg);
	}

	protected void error(String msg,Throwable e){
		getLog().error(msg,e);
	}
}
