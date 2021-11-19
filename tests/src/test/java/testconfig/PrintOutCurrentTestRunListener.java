package testconfig;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class PrintOutCurrentTestRunListener extends RunListener {
    @Override
    public void testRunStarted(Description description) {
        System.out.println("testRunStarted " + description.getClassName() + " " + description.getDisplayName() + " "
                + description.toString());
    }

    public void testStarted(Description description) {
        System.out.println("testStarted "
                + description.toString());
    }

    public void testFinished(Description description) {
        System.out.println("testFinished "
                + description.toString());
    }

    public void testRunFinished(Result result) {
        System.out.println("testRunFinished " + result.toString()
                + " time: "+result.getRunTime()
                +" R: "+result.getRunCount()
                +" F: "+result.getFailureCount()
                +" I: "+result.getIgnoreCount()
                );
    }
}
