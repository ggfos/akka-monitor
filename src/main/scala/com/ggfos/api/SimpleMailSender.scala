package com.ggfos.api

import java.util.Date
import java.util.Properties
import javax.mail.internet.{MimeBodyPart, MimeMultipart, InternetAddress, MimeMessage}
import javax.mail._
import com.ggfos.entity.MailSenderInfo

/**
 * Created by doubleround on 14-9-12.
 *
 */
object SimpleMailSender {

  def apply(mailInfo: MailSenderInfo) {
    this.sendTextMail(mailInfo)
  }

  private def getProperties(mailInfo: MailSenderInfo) = {
    val p = new Properties();
    p.put("mail.smtp.host", mailInfo.serverHost)
    p.put("mail.smtp.port", mailInfo.serverPort)
    p.put("mail.smtp.auth", if (mailInfo.validate) "true" else "false")
    p
  }

  /**
   * 以文本格式发送邮件
   * @param mailInfo 待发送的邮件的信息
   **/
  def sendTextMail(mailInfo: MailSenderInfo) {
    val properties = this.getProperties(mailInfo)
    val authenticator = MyAuthenticator(mailInfo.username, mailInfo.password)
    val sendMailSession = Session.getDefaultInstance(properties, authenticator)

    // 根据session创建一个邮件消息
    val mailMessage = new MimeMessage(sendMailSession)

    // 创建邮件发送者地址
    val from = new InternetAddress(mailInfo.fromAddress)
    // 设置邮件消息的发送者
    mailMessage.setFrom(from)
    // 创建邮件的接收者地址，并设置到邮件消息中
    mailInfo.toAddresses.foreach {
      case to =>
        val toAddress = new InternetAddress(to)
        mailMessage.addRecipient(Message.RecipientType.TO, toAddress)
    }
    // 设置邮件消息的主题
    mailMessage.setSubject(mailInfo.subject)
    // 设置邮件消息发送的时间
    mailMessage.setSentDate(new Date())
    // 设置邮件消息的主要内容
    mailMessage.setText(mailInfo.content)

    // 发送邮件
    Transport.send(mailMessage)
  }

  /**
   * 以HTML格式发送邮件
   * @param mailInfo 待发送的邮件信息
   */
  def sendHtmlMail(mailInfo: MailSenderInfo) {
    val properties = this.getProperties(mailInfo)
    val authenticator = MyAuthenticator(mailInfo.username, mailInfo.password)
    val sendMailSession = Session.getDefaultInstance(properties, authenticator)

    // 根据session创建一个邮件消息
    val mailMessage = new MimeMessage(sendMailSession)
    // 创建邮件发送者地址
    val from = new InternetAddress(mailInfo.fromAddress)
    // 设置邮件消息的发送者
    mailMessage.setFrom(from)
    // 创建邮件的接收者地址，并设置到邮件消息中
    mailInfo.toAddresses.foreach {
      case to =>
        val toAddress = new InternetAddress(to)
        mailMessage.addRecipient(Message.RecipientType.TO, toAddress)
    }
    // 设置邮件消息的主题
    mailMessage.setSubject(mailInfo.subject)
    // 设置邮件消息发送的时间
    mailMessage.setSentDate(new Date())

    // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
    val mainPart = new MimeMultipart()
    // 创建一个包含HTML内容的MimeBodyPart
    val html = new MimeBodyPart()
    // 设置HTML内容
    html.setContent(mailInfo.content, "text/html; charset=utf-8")
    mainPart.addBodyPart(html)
    // 将MiniMultipart对象设置为邮件内容
    mailMessage.setContent(mainPart)

    // 发送邮件
    Transport.send(mailMessage)
  }

}
