import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @When("Click the {string} button on the tip right corner of the screen")
    public void clickTheButtonOnTheTipRightCornerOfTheScreen(String arg0) {
    }

    @And("Click {string} button on the Welcome Back Block")
    public void clickButtonOnTheWelcomeBackBlock(String arg0) {
    }

    @And("Add {int} kits of your choice to the basket")
    public void addKitsOfYourChoiceToTheBasket(int arg0) {
    }

    @Then("Check that {string} block has not appeared on the right")
    public void checkThatBlockHasNotAppearedOnTheRight(String arg0) {
    }
}
