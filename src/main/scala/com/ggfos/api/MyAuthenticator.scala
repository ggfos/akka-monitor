package com.ggfos.api

import javax.mail.{PasswordAuthentication, Authenticator}

/**
 * Created by doubleround on 14-9-12.
 **/
object MyAuthenticator extends Authenticator {

  private var username: String = ""
  private var password: String = ""

  def apply(username: String, password: String) = {
    this.username = username
    this.password = password
    this
  }

  override protected def getPasswordAuthentication() = {
    new PasswordAuthentication(username, password)
  }

}

