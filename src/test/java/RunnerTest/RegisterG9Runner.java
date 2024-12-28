package RunnerTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
        "src/test/resources/Feature/register.feature" }, glue = "stepdefinition")
  

public class RegisterG9Runner {

}
