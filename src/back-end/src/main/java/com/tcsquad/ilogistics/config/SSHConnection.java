package com.tcsquad.ilogistics.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Configuration
public class SSHConnection {

    // 自定义的中转接口，需要和数据源接口设置一样
    @Value("${sshconfig.local-port}")
    private Integer localPort;
    // 服务端的数据库端口
    @Value("${sshconfig.remote-port}")
    private Integer remotePort;
    // 服务器端SSH端口 默认是22
    @Value("${sshconfig.ssh.remote-port}")
    private Integer sshRemotePort;
    // SSH用户名
    @Value("${sshconfig.ssh.user}")
    private String sshUser;
    // SSH使用密码
    @Value("${sshconfig.ssh.password}")
    private String sshPassword;
    // 连接到哪个服务端的SSH
    @Value("${sshconfig.ssh.remote-server}")
    private String sshRemoteServer;
    // 服务端的本地mysql服务
    @Value("${sshconfig.mysql.remote-server}")
    private String mysqlRemoteServer;

    //represents each ssh session
    private Session session;

    /**
     * 创建SSH连接
     */
    public void createSSH() throws Throwable {

        JSch jsch = new JSch();
        // 需要用到了开启
        session = jsch.getSession(sshUser, sshRemoteServer, sshRemotePort);
        session.setPassword(sshPassword);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        // 去连接
        session.connect(); //ssh connection established!
        //  设置转发
        session.setPortForwardingL(localPort, mysqlRemoteServer, remotePort);
    }

    /**
     * 关闭SSH连接
     */
    public void closeSSH() {
        session.disconnect();
    }

}