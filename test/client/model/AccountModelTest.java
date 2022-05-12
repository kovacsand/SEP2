package client.model;

import client.networking.Client;
import client.networking.RMIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountModelTest
{

  //login()
  @Test public void usernamePasswordReceivedAsStrings()
  {
    //arrange
    //act
      //login()
    //assert
  }

  @Test public void usernameNullPasswordNull()
  {
    //arrange
    //act
    //assert
  }

  @Test public void usernameEnteredPasswordNull()
  {
    //arrange
    //act
    //assert
  }

  @Test public void usernameNullPasswordEntered()
  {
    //arrange
    //act
      //login()
    //assert
  }

  //loginReply(Boolean, User)
  @Test public void usernamePasswordMatch()
  {
    //arrange
    //act
      //loginReply(True,"James")
    //assert
  }

  @Test public void usernamePasswordNoMatch()
  {
    //arrange
    //act
      //loginReply(False,null)
    //assert
  }

  @Test public void loginReplyFalseNotNull()
  {
    //arrange
    //act
      //loginReply(False,"James")
    //assert
  }

  @Test public void loginReplyTrueNull()
  {
    //arrange
    //act
      //loginReply(True,null)
    //assert
  }
}