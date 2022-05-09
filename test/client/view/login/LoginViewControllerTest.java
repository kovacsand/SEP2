package client.view.login;

import client.model.AMImplementation;
import client.model.AccountModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewControllerTest
{

  private String usernameField;
  private String passwordField;


  @Test public void loginSuccess()
  {
    //arrange
      //usernameField = correct
      //passwordField = correct
    //act
      //loginVM.login()

    //assert
      //
    System.out.println(usernameField + " " + passwordField);
    System.out.println("ViewChange");
  }

 @Test public void usernamePasswordNoMatch()
  {
    //arrange
      //userField = correct(NotNull)
      //passwordField = correct(NotNull)

    //act
      //loginVM.login()

    //Assert
      //assertEquals(errorMessage.getValue().equals("Incorrect username and/or password, or fields can not be empty")
    System.out.println("NoViewChange");
  }

  @Test public void usernameEmptyPasswordFilled()
  {
    //arrange
      //userField = empty/null
     //passwordField = correct

    //act
      //loginVM.login()

    //assert
    System.out.println("NoViewChange");
      //assertEquals(errorMessage.getValue().equals("Incorrect username and/or password, or fields can not be empty")
  }

  @Test public void usernameFilledPasswordEmpty()
  {
    //arrange
      //userField = correct(NotNull)
      //passwordField = empty/null


    //act


    //assert
    System.out.println("NoViewChange");
     //assertEquals(errorMessage.getValue().equals("Incorrect username and/or password, or fields can not be empty"));
  }

  @Test public void usernameEmptyPasswordEmpty()
  {
    //arrange


    //act
      //userField = empty/null
      //passwordField = empty/null

    //assert
      //assertEquals(errorMessage.getValue().equals("Incorrect username and/or password, or fields can not be empty"));
    System.out.println("NoViewChange");
  }
}