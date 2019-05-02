package edu.hm.cs.fwp.cloud.tools.helm.core.command;

import edu.hm.cs.fwp.cloud.tools.helm.core.ExecutableRunner;
import org.slf4j.Logger;

import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

public class InstallCommand extends AbstractChartCommand<InstallCommandResult> {

    private boolean atomic;
    private boolean depUp;
    private String description;
    private boolean devel;
    private boolean dryRun;
    private String releaseName;
    private String releaseNameTemplate;
    private String namespace;
    private boolean noCrdHook;
    private boolean noHooks;
    private URL chartRepoUrl;
    private String chartRepoUser;
    private String chartRepoPassword;
    private boolean renderSubChartNotes;
    private boolean replace;
    private Map<String, Object> values = new LinkedHashMap<>();
    /*
          --set stringArray          set values on the comma>d line (can specify multiple or separate values with commas: key1=val1,key2=val2)
          --set-file stringArray     set values from respective files specified via the command line (can specify multiple or separate values with commas: key1=path1,key2=path2)
          --set-string stringArray   set STRING values on the command line (can specify multiple or separate values with commas: key1=val1,key2=val2)
     */
    private int timeout;
    /*
          --tls                      enable TLS for request
          --tls-ca-cert string       path to TLS CA certificate file (default "$HELM_HOME/ca.pem")
          --tls-cert string          path to TLS certificate file (default "$HELM_HOME/cert.pem")
          --tls-hostname string      the server name used to verify the hostname on the returned certificates from the server
          --tls-key string           path to TLS key file (default "$HELM_HOME/key.pem")
          --tls-verify               enable TLS for request and verify remote

     */
/*
  -f, --values valueFiles        specify values in a YAML file or a URL(can specify multiple) (default [])

 */
    private boolean verify;
    private String chartVersion;
    private boolean wait;

    public InstallCommand() {
        super();
    }

    public InstallCommand(Logger logger) {
        super(logger);
    }

    /**
     * If set, installation process purges chart on fail, also sets {@code wait} flag
     */
    public boolean isAtomic() {
        return this.atomic;
    }

    public void setAtomic(boolean atomic) {
        this.atomic = atomic;
    }

    /**
     * If set, runs an update on all chart dependencies before installing the chart.
     */
    public boolean isDepUp() {
        return depUp;
    }

    public void setDepUp(boolean depUp) {
        this.depUp = depUp;
    }

    /**
     * Optional description of this release.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * If set, use development versions, too (equivalent to version ">0.0.0-0").
     */
    public boolean isDevel() {
        return devel;
    }

    public void setDevel(boolean devel) {
        this.devel = devel;
    }

    /**
     * If set, only simulates an install.
     */
    public boolean isDryRun() {
        return dryRun;
    }

    public void setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
    }

    /**
     * Optional custom release name (default: auto-generated release name)
     */
    public Optional<String> getReleaseName() {
        return Optional.ofNullable(this.releaseName);
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    /**
     * Optional release name template.
     */
    public Optional<String> getReleaseNameTemplate() {
        return Optional.ofNullable(this.releaseNameTemplate);
    }

    public void setReleaseNameTemplate(String releaseNameTemplate) {
        this.releaseNameTemplate = releaseNameTemplate;
    }

    /**
     * Optional namespace to install to (default: current kubeconfig namespace).
     */
    public Optional<String> getNamespace() {
        return Optional.ofNullable(this.namespace);
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * If set, prevent CRD hooks from running, but run other hooks.
     */
    public boolean isNoCrdHook() {
        return noCrdHook;
    }

    public void setNoCrdHook(boolean noCrdHook) {
        this.noCrdHook = noCrdHook;
    }

    /**
     * If set, prevent all hooks from running.
     */
    public boolean isNoHooks() {
        return noHooks;
    }

    public void setNoHooks(boolean noHooks) {
        this.noHooks = noHooks;
    }

    /**
     * Optional URL to connect to a chart repository containing the chart to install.
     */
    public Optional<URL> getChartRepoUrl() {
        return Optional.ofNullable(this.chartRepoUrl);
    }

    public void setChartRepoUrl(URL chartRepoUrl) {
        this.chartRepoUrl = chartRepoUrl;
    }

    /**
     * Optional user to connect to a chart repository containing the chart to install.
     */
    public Optional<String> getChartRepoUser() {
        return Optional.ofNullable(this.chartRepoUser);
    }

    public void setChartRepoUser(String chartRepoUser) {
        this.chartRepoUser = chartRepoUser;
    }

    /**
     * Optional password to connect to a chart repository containing the chart to install.
     */
    public Optional<String> getChartRepoPassword() {
        return Optional.ofNullable(this.chartRepoPassword);
    }

    public void setChartRepoPassword(String chartRepoPassword) {
        this.chartRepoPassword = chartRepoPassword;
    }

    /**
     * If set, renders subchart notes along with the parent (default: false)
     */
    public boolean isRenderSubChartNotes() {
        return renderSubChartNotes;
    }

    public void setRenderSubChartNotes(boolean renderSubChartNotes) {
        this.renderSubChartNotes = renderSubChartNotes;
    }

    /**
     * If set, re-use the given release name, even if that release name is already used (default: {@code false}).
     * <p>
     * <strong>Unsafe in production!!!</strong>
     * </p>
     */
    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public void addValue(String key, Object value) {
        Objects.requireNonNull(key, "key must not be null");
        values.put(key, value);
    }

    public Map<String, Object> getValues() {
        return values;
    }

    protected void collectCommandLineValues(List<String> arguments) {
/*
        StringBuilder regularValuesBuilder = new StringBuilder();
        StringBuilder stringValuesBuilder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Object> current : values.entrySet()) {
            if (!first) {

            }
        }
 */
    }

    /**
     * Optional duration in seconds helm will wait for any individual Kubernetes operation (like Jobs for hooks) (default: 300)
     */
    public final Optional<Integer> getTimeout() {
        return timeout != 0 ? Optional.of(this.timeout) : Optional.empty();
    }

    /**
     * Sets the duration in seconds Helm will wait to establish a connection to tiller.
     */
    public final void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * If set, verifies the package before installing it (default: {@code false}).
     */
    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    /**
     * Optional exact chart version to install. If this is not specified, the latest version is installed.
     */
    public Optional<String> getChartVersion() {
        return Optional.ofNullable(this.chartVersion);
    }

    public void setChartVersion(String chartVersion) {
        this.chartVersion = chartVersion;
    }

    /**
     * If set, will wait for {@link #timeout} seconds until all Pods, PVCs, Services, and minimum number of Pods of a Deployment are in a ready
     * state before marking the release as successful.
     */
    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    protected void collectCommandLineArguments(List<String> arguments) {
        arguments.add(getChartDirectory().getAbsolutePath());
        super.collectCommandLineArguments(arguments);
        if (isAtomic()) {
            arguments.add("--atomic");
        }
        if (isDepUp()) {
            arguments.add("--dep-up");
        }
        getDescription().ifPresent(description -> {
            arguments.add("--description");
            arguments.add("\"" + description + "\"");
        });
        if (isDevel()) {
            arguments.add("--devel");
        }
        if (isDryRun()) {
            arguments.add("--dry-run");
        }
        getReleaseName().ifPresent(name -> {
            arguments.add("--name");
            arguments.add(name);
        });
        getReleaseNameTemplate().ifPresent(template -> {
            arguments.add("--name-template");
            arguments.add(template);
        });
        getNamespace().ifPresent(namespace -> {
            arguments.add("--namespace");
            arguments.add(namespace);
        });
        if (isNoCrdHook()) {
            arguments.add("--no-crd-hook");
        }
        if (isNoHooks()) {
            arguments.add("--no-hooks");
        }
        getChartRepoUrl().ifPresent(url -> {
            arguments.add("--repo");
            arguments.add(url.toString());
        });
        getChartRepoUser().ifPresent(user -> {
            arguments.add("--username");
            arguments.add(user);
        });
        getChartRepoPassword().ifPresent(password -> {
            arguments.add("--password");
            arguments.add(password);
        });
        if (isRenderSubChartNotes()) {
            arguments.add("--render-subchart-notes");
        }
        if (isReplace()) {
            arguments.add("--replace");
        }
        getTimeout().ifPresent(timeout -> {
            arguments.add("--timeout");
            arguments.add(Integer.toString(timeout));
        });
        getChartVersion().ifPresent(version -> {
            arguments.add("--version");
            arguments.add(version);
        });
        if (isWait()) {
            arguments.add("--wait");
        }
    }

    @Override
    public InstallCommandResult internalCall() throws Exception {
        ExecutableRunner runner = new ExecutableRunner();
        Consumer<String> loggingConsumer = s -> this.logger.info(s);
        Consumer<String> consumer = loggingConsumer;
        List<String> arguments = new ArrayList<>();
        arguments.add("helm");
        arguments.add("install");
        collectCommandLineArguments(arguments);
        this.logger.info("running command: " + String.join(" ", arguments));
        runner.run(getCurrentDirectory(), consumer, arguments.toArray(new String[arguments.size()]));
        return new InstallCommandResult();
    }
}
