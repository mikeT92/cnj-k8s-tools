/*
 * at41-tools-kubectl-maven-plugin:CreateResourcesMojo.java
 * (c) Copyright msg systems ag Automotive Technology 2017
 */
package edu.hm.cs.fwp.cloud.tools.helm.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * {@code Mojo} that creates all resources specified by the given YAML files.
 *
 * @author theism
 * @version 1.0
 * @since 15.02.2019
 */
@Mojo(name = "create", requiresProject = true)
public final class CreateResourcesMojo extends AbstractHelmMojo {

	/**
	 * Pushes the specified application to the specified PCF environment.
	 * <p>
	 * Assumes that the applicationDescriptor file is located on project root
	 * and the
	 * application binary has been pushed as a Docker image to AWS ECR.
	 * </p>
	 * <p>
	 * All heavy-lifting is actually done by the AT.41 AWS ECS command line
	 * tools.
	 * </p>
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
	}
}
