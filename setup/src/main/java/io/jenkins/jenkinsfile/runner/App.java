package io.jenkins.jenkinsfile.runner;

import io.jenkins.jenkinsfile.runner.bootstrap.IApp;
import io.jenkins.jenkinsfile.runner.bootstrap.commands.JenkinsLauncherCommand;
import io.jenkins.jenkinsfile.runner.bootstrap.commands.RunJenkinsfileCommand;

/**
 * App handler for {@link RunJenkinsfileCommand}.
 * This code runs after Jetty and Jenkins classloaders are set up correctly.
 */
public class App implements IApp {

    @Override
    public int run(JenkinsLauncherCommand command) throws Throwable {
        if (!(command instanceof RunJenkinsfileCommand)) {
            throw new IllegalStateException(
                    String.format("%s is invoked with a wrong class type. Required=%s, got=%s",
                            App.class, RunJenkinsfileCommand.class, command.getClass()));
        }
        JenkinsfileRunnerLauncher launcher = new JenkinsfileRunnerLauncher((RunJenkinsfileCommand) command);
        return launcher.launch();
    }
}
