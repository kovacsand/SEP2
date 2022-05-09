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

  private LoginViewModel loginVM;
  private LoginViewController loginVC;

  @BeforeEach public void setUp()
  {
  }

  @Test public void loginSuccess()
  {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    loginVM.usernameProperty.bindBidirectional(username);
    loginVM.passwordProperty.bindBidirectional(password);

    //act
    username.setValue("James");
    password.setValue("123");
    vm.login();


    //assert
    System.out.println("ViewChange");
  }

  @Test public void usernamePasswordNoMatch()
  {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    loginVM.usernameProperty.bindBidirectional(username);
    loginVM.passwordProperty.bindBidirectional(password);

    //act
    username.setValue("James");
    password.setValue("124");
    vm.login();

    //Assert
    System.out.println("NoViewChange");
    System.out.println("Incorrect username and/or password, or fields can not be empty");
  }

  @Test public void usernameEmptyPasswordFilled()
  {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    loginVM.usernameProperty.bindBidirectional(username);
    loginVM.passwordProperty.bindBidirectional(password);

    //act
    username.setValue("");
    password.setValue("124");
    vm.login();

    //assert
    System.out.println("NoViewChange");
    System.out.println("Incorrect username and/or password, or fields can not be empty");
  }

  @Test public void usernameFilledPasswordEmpty()
  {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    loginVM.usernameProperty.bindBidirectional(username);
    loginVM.passwordProperty.bindBidirectional(password);

    //act
    username.setValue("James");
    password.setValue("");
    vm.login();

    //assert
    System.out.println("NoViewChange");
    System.out.println("Incorrect username and/or password, or fields can not be empty");
  }

  @Test public void usernameEmptyPasswordEmpty()
  {
    //arrange
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    loginVM.usernameProperty.bindBidirectional(username);
    loginVM.passwordProperty.bindBidirectional(password);

    //act
    username.setValue("");
    password.setValue("");
    vm.login();

    //assert
    System.out.println("NoViewChange");
    System.out.println("Incorrect username and/or password, or fields can not be empty");
  }
}