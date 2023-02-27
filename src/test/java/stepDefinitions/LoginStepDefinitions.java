package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.Login;
import tasks.LoginUser;
import utils.DataDrivenExcel;
import utils.Excel;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;



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
        park.should(GivenWhenThen.seeThat("Codigo de respuesta", Login.was(), equalTo(200)));
    }

    //----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------
    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<>();
    
    
    @Given("^that I need to log in to the page (\\d+)$")
    public void thatINeedToLogInToThePage(int row) {
    	Excel excel = new Excel("src/test/resources/datadriven/DatosLogin.xlsx", "Login", true, row);
		data = dataDriverExcel.leerExcel(excel);
    	park.whoCan(CallAnApi.at(resApiUrl));
    }


    @When("^send the data to log in$")
    public void sendTheDataToLogIn() {
        String body = "{\n" +
                "    \"email\": \""+data.get("email")+"\",\n" +
                "    \"password\": \""+data.get("password")+"\"\n" +
                "}";
        System.out.println(data.get("email")+ " " +data.get("password"));
        park.attemptsTo(LoginUser.loginuser(body));
    }

    @Then("^I get a successful response$")
    public void iGetASuccessfulResponse() {
    	park.should(GivenWhenThen.seeThat("Codigo de respuesta", Login.was(), equalTo(200)));
    }

    
}
