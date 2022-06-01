package com.allyouplay.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
                "rerun:target/rerun.txt",
                "pretty"        },
        stepNotifications = true,
        features = { "src/test/resources/features/" },
        glue = {"com/allyouplay/stepDefinitions"},
        dryRun = false,
        tags = "@login"
)
 public class cukesRunner {
}
