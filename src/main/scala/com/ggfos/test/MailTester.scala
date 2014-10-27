package com.ggfos.test

import com.ggfos.entity.MailSenderInfo
import com.ggfos.api.SimpleMailSender

/**
 * Created by doubleround on 14-9-12.
 */
object MailTester extends App {

  val mailInfo = MailSenderInfo(
    "smtp.qq.com", "25", //邮件服务器及端口号
    "954155714@qq.com", "***********", true, //账号密码,及是否要经过验证
    "954155714@qq.com", Iterable("214185383@qq.com", "954155714@qq.com"), //发送者及接收者
    "测试邮件!", "我是测试邮件!") //主题及内容

  SimpleMailSender.sendHtmlMail(mailInfo)
}
