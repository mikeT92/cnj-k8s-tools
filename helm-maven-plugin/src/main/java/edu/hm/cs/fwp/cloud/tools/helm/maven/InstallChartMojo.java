/*
 * at41-tools-kubectl-maven-plugin:InstallChartMojo.java
 * (c) Copyright msg systems ag Automotive Technology 2017
 */
package edu.hm.cs.fwp.cloud.tools.helm.maven;

import edu.hm.cs.fwp.cloud.tools.helm.core.command.InstallCommand;
import edu.hm.cs.fwp.cloud.tools.helm.core.command.InstallCommandResult;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * {@code Mojo} that installs the specified chart.
 */
@Mojo(name = "install", requiresProject = true)
public final class InstallChartMojo extends AbstractHelmChartMojo {

	/**
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		InstallCommand install = new InstallCommand(new Slf4jMavenLogAdapter(getLog()));
		install.setChartDirectory(helmChartDirectory);
		install.setReleaseName(helmReleaseName);
		install.setAtomic(true);
		install.setWait(true);
		try {
			InstallCommandResult installResult = install.call();
		} catch (Exception ex) {
			throw new MojoExecutionException(String.format("Failed to install helm release %s", helmReleaseName), ex);
		}

	}
}
