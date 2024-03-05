package org.api.testing.reto.runners;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(publish = true,
        features = "src/test/resources/features.booking",
        glue = "org.api.testing.reto.steps",
        tags = "@integrationTest",
        snippets = SnippetType.CAMELCASE)

public class TestSuiteRunner {

}


