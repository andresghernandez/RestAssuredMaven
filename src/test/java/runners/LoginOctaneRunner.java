package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/Login_1001.feature",
        glue = "stepDefinitions",
        snippets = SnippetType.CAMELCASE
)
public class LoginOctaneRunner {

}
