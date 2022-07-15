package com.example.demo.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
public class OperateLinuxCommand {
    private static final Logger logger = Logger.getLogger(OperateLinuxCommand.class);

    private static final String DEFAULTCHARTSET = "UTF-8";
    private static Connection conn;

    /**
     * 用户名密码方式  远程登录linux服务器
     *
     * @Title: login
     * @return: Boolean
     */
    public static Boolean login(final String ip, final String userName, final String password) {
        boolean flag = false;
        try {
            conn = new Connection(ip);
            conn.connect();
            // 认证
            flag = conn.authenticateWithPassword(userName, password);
            if (flag) {
                logger.info("认证成功！");
            } else {
                logger.info("认证失败！");
                conn.close();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 秘钥方式  远程登录linux服务器
     *
     * @param ip
     * @param userName
     * @param keyFile 一个文件对象指向一个文件，该文件包含OpenSSH密钥格式的用户的DSA或RSA私钥(PEM，不能丢失"-----BEGIN DSA PRIVATE KEY-----" or "-----BEGIN RSA PRIVATE KEY-----"标签
     * @param keyfilePass 如果秘钥文件加密 需要用该参数解密，如果没有加密可以为null
     * @return Boolean
     * @Title: loginByKey
     */
    public static Boolean loginByFileKey(final String ip, final String userName, final File keyFile, final String keyfilePass) {
        boolean flag = false;
        // 输入密钥所在路径
        // File keyfile = new File("C:\\temp\\private");
        try {
            conn = new Connection(ip);
            conn.connect();
            // 登录认证
            flag = conn.authenticateWithPublicKey(userName, keyFile, keyfilePass);
            if (flag) {
                logger.info("认证成功！");
            } else {
                logger.info("认证失败！");
                conn.close();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 秘钥方式  远程登录linux服务器
     *
     * @param ip
     * @param userName
     * @param keys 一个字符[]，其中包含用户的DSA或RSA私钥(OpenSSH密匙格式，您不能丢失“----- begin DSA私钥-----”或“-----BEGIN RSA PRIVATE KEY-----“标签。char数组可以包含换行符/换行符。
     * @param keyPass 如果秘钥字符数组加密  需要用该字段解密  否则不需要可以为null
     * @return Boolean
     * @Title: loginByCharsKey
     */
    public static Boolean loginByCharsKey(final String ip, final String userName, final char[] keys, final String keyPass) {

        boolean flag = false;
        // 输入密钥所在路径
        // File keyfile = new File("C:\\temp\\private");
        try {
            conn = new Connection(ip);
            conn.connect();
            // 登录认证
            flag = conn.authenticateWithPublicKey(userName, keys, keyPass);
            if (flag) {
                logger.info("认证成功！");
            } else {
                logger.info("认证失败！");
                conn.close();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *  远程执行shll脚本或者命令
     *
     * @param cmd 脚本命令
     * @Title: execute
     * @return: result 命令执行完毕返回结果
     */
    public static String execute(final String cmd) {
        String result = "";
        try {
            final Session session = conn.openSession();// 打开一个会话
            session.execCommand(cmd);// 执行命令
            result = processStdout(session.getStdout(), DEFAULTCHARTSET);
            // 如果为得到标准输出为空，说明脚本执行出错了
            if (StringUtils.isBlank(result)) {
                result = processStdout(session.getStderr(), DEFAULTCHARTSET);
            }
            session.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 远程执行shell脚本或者命令
     *
     * @param cmd shell脚本或者命令
     * @return String 命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     * @Title: executeSuccess
     */
    public static String executeSuccess(final String cmd) {
        String result = "";
        try {
            final Session session = conn.openSession();// 打开一个会话
            session.execCommand(cmd);// 执行命令
            result = processStdout(session.getStdout(), DEFAULTCHARTSET);
            conn.close();
            session.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析脚本执行的返回结果
     *
     * @param in 输入流对象
     * @param charset 编码
     * @return String 以纯文本的格式返回
     * @Title: processStdout
     */
    public static String processStdout(final InputStream in, final String charset) {
        final InputStream stdout = new StreamGobbler(in);
        final StringBuffer buffer = new StringBuffer();
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 通过用户名和密码关联linux服务器
     *
     * @return String
     * @Title: ConnectLinux
     */
    public static boolean connectLinux(final String ip, final String userName, final String password, final String commandStr) {

        logger.info("connectLinux  scpGet===" + "ip:" + ip + "  userName:" + userName + "  commandStr:"
                + commandStr);
        String returnStr = "";
        boolean result = true;
        try {
            if (login(ip, userName, password)) {
                returnStr = execute(commandStr);
                System.out.println(result);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isBlank(returnStr)) {
            result = false;
        }
        return result;
    }

}