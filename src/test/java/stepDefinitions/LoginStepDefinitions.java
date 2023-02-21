package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.Login;
import tasks.LoginUser;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {

    private static final String resApiUrl = "https://reqres.in/api";
    Actor park = Actor.named("Park");

    @Given("^Park necesita loguearse en la pagina$")
    public void parkNecesitaLoguearseEnLaPagina() {
        park.whoCan(CallAnApi.at(resApiUrl));
    }

    @When("^el envia los datos para loguearse$")
    public void elEnviaLosDatosParaLoguearse() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        park.attemptsTo(LoginUser.loginuser(body));

    }

    @Then("^el obtiene una respuesta exitosa$")
    public void elObtieneUnaRespuestaExitosa() {
        park.should(GivenWhenThen.seeThat("Código de respuesta", Login.was(), equalTo(200)));
    }

    //----------------------------------------------------------------------------------------------
    
    @Given("^that I need to log in to the page$")
    public void thatINeedToLogInToThePage() {
    	park.whoCan(CallAnApi.at(resApiUrl));
    }


    @When("^send the data to log in$")
    public void sendTheDataToLogIn() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        park.attemptsTo(LoginUser.loginuser(body));
    }

    @Then("^I get a successful response$")
    public void iGetASuccessfulResponse() {
    	park.should(GivenWhenThen.seeThat("Código de respuesta", Login.was(), equalTo(200)));
    }

    
}
