package org.cilantro.runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

//@Suite
//@IncludeEngines("cucumber")

//@SelectClasspathResource("org/cilantro")

@CucumberOptions(
        features = "src/test/resources/features", // Path to the feature files
        glue = "steps", // Package containing the step definition classes
        plugin = {"pretty", "html:target/cucumber-reports"}, // Plugin for generating reports
        monochrome = true, // Makes the console output more readable
        tags = "@smoke" // Optional: Run only tests with specific tags
)

public class RunCucumberTest {
}