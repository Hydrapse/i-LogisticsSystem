package com.tcsquad.ilogistics.listener;

import com.tcsquad.ilogistics.config.SSHConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Component
@WebListener
public class SSHWebListener implements ServletContextListener {

    @Autowired
    private SSHConnection sshconnection;

    Logger logger = LoggerFactory.getLogger(SSHWebListener.class);
    /**
     * 监听Servlet初始化事件
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // 建立连接
        try {
            sshconnection.createSSH();
            logger.info("成功建立SSH连接！");
        } catch (Throwable e) {
            logger.info("SSH连接失败！");
            e.printStackTrace(); // error connecting SSH server
        }
    }

    /**
     * 监听Servlet终止事件
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // 断开连接
        try {
            sshconnection.closeSSH(); // disconnect
            logger.info("成功断开SSH连接!");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("断开SSH连接出错！");
        }
    }

}
