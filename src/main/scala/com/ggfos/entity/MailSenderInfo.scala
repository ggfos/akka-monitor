package com.ggfos.entity

/**
 * Created by doubleround on 14-9-12.
 */

/**
 * 发送邮件的服务器的IP和端口
 * private String mailServerHost;
 * private String mailServerPort = "25";
 *
 * 邮件发送者的地址
 * private String fromAddress;
 *
 * 邮件接收者的地址
 * private String toAddress;
 *
 * 登陆邮件发送服务器的用户名和密码
 * private String userName;
 * private String password;
 *
 * 是否需要身份验证
 * private boolean validate = false;
 *
 * 邮件主题
 * private String subject;
 *
 * 邮件的文本内容
 * private String content;
 */
case class MailSenderInfo(serverHost: String, serverPort: String,
                          username: String, password: String, validate: Boolean,
                          fromAddress: String, toAddresses: Iterable[String],
                          subject: String, content: String)
