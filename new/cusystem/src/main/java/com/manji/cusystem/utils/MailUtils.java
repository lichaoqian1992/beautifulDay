package com.manji.cusystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 * 发送邮件工具类
 */
public class MailUtils {

    /**
     * 发送文本类的邮件
     * @param to      接收邮件的账号
     * @param content  邮件内容
     * @param theme    邮件主题
     */
    public void sendTextMail(String to,String theme,String content,JavaMailSender javaMailSender){

        try{
            //处理数据
            List<String> receiverList = new ArrayList<String>();
            String[] a = to.split(",");
            for(int i=0;i<a.length;i++){
                receiverList.add(a[i]);
            }
            //true表示需要创建一个multipart message
            System.out.println("开始发送时间："+new DatesUtils().time());

            int len = receiverList.size();
            MimeMessage[] mimeMessageList = new MimeMessage[len];	// 注意：是数组类型
            MimeMessageHelper helper = null;
            for(int i = 0; i < len; ++i) {
                mimeMessageList[i] = javaMailSender.createMimeMessage();
                helper = new MimeMessageHelper(mimeMessageList[i], true, "UTF-8");
                helper.setFrom("1193418905@qq.com");
                helper.setTo(receiverList.get(i));
                helper.setSubject(theme);
                helper.setText(content, true);

            }
            javaMailSender.send(mimeMessageList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送附件类邮件
     * @param to        接收人
     * @param content   内容
     * @param theme     主题
     * @param filePath  附件路径
     */
    public void sendFileMail(String to,String content,String theme,String filePath,JavaMailSender javaMailSender){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("1193418905@qq.com");
            helper.setTo(to);
            helper.setSubject(theme);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            System.out.println(File.separator+"===============");
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            javaMailSender.send(message);
        } catch (MessagingException e) {

        }
    }

    /**
     * 批量发送带附件的邮件
     * @param receiverList     接收人集合
     * @param theme            主题
     * @param content          内容
     * @param filePath         附件地址
     * @param javaMailSender   发送邮件的对象
     */
    public void sendBatchMail(List<String> receiverList,String theme,String content,String filePath,JavaMailSender javaMailSender){

        try{
            System.out.println("开始发送时间："+new DatesUtils().time());

            int len = receiverList.size();
            MimeMessage[] mimeMessageList = new MimeMessage[len];	// 注意：是数组类型
            MimeMessageHelper messageHelper = null;
            for(int i = 0; i < len; ++i) {
                mimeMessageList[i] = javaMailSender.createMimeMessage();
                messageHelper = new MimeMessageHelper(mimeMessageList[i], true, "UTF-8");
                messageHelper.setFrom("1193418905@qq.com");
                messageHelper.setTo(receiverList.get(i));
                messageHelper.setSubject(theme);
                messageHelper.setText(content, true);
                FileSystemResource file = new FileSystemResource(new File(filePath));

                String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                messageHelper.addAttachment(fileName, file);
            }
            javaMailSender.send(mimeMessageList
            );
            System.out.println("结束发送时间："+new DatesUtils().time());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     * @param list
     * @param subject
     * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath 静态资源路径和文件名
     * @param rscId 静态资源id
     */
    public void sendInlineResourceMail(String list, String subject, String content, String rscPath, String rscId,JavaMailSender sender){

        try {
            //处理数据
            List<String> receiverList = new ArrayList<String>();
            String[] a = list.split(",");
            for(int i=0;i<a.length;i++){
                receiverList.add(a[i]);
            }
            //true表示需要创建一个multipart message
            System.out.println("开始发送时间："+new DatesUtils().time());

            int len = receiverList.size();
            MimeMessage[] mimeMessageList = new MimeMessage[len];	// 注意：是数组类型
            MimeMessageHelper helper = null;
            for(int i = 0; i < len; ++i) {
                mimeMessageList[i] = sender.createMimeMessage();
                helper = new MimeMessageHelper(mimeMessageList[i], true, "UTF-8");
                helper.setFrom("1193418905@qq.com");
                helper.setTo(receiverList.get(i));
                helper.setSubject(subject);
                helper.setText(content, true);
                if(rscPath != null && !"".equals(rscPath)){
                    FileSystemResource res = new FileSystemResource(new File(rscPath));
                    helper.addInline(rscId, res);
                }

            }

            sender.send(mimeMessageList);
            System.out.println("嵌入静态资源的邮件已经发送。");
            System.out.println("结束发送时间："+new DatesUtils().time());
        } catch (MessagingException e) {
            System.out.println("发送嵌入静态资源的邮件时发生异常！");
        }
    }

    /*public void main(String[] args){

        String to = "376775994@qq.com";
        String content = "测试邮件。。。。。。。。";
        String theme = "有检测室你不要管";
        sendFileMail(to,content,theme,"C:\\Users\\Administrator\\Desktop\\Microsoft Excel 工作表 (.xlsx)");
        sendTextMail(to,content,theme);
    }*/

}
