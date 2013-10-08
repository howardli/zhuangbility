/**
 * Zhuangbility.com Inc.
 * Copyright (c) 2013-2013 All Rights Reserved.
 */
package com.zhuangbility;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 启动器，直接运行，启动整个环境，仅在开发时使用
 * 
 * @author Howard.Li
 */
public class Starter {

    /**
     * web应用路径
     */
    private static final String DEFAULT_WEBAPP_PATH   = "src/main/webapp";

    /**
     * web应用默认配置路径
     */
    private static final String DEFAULT_WEBAPP_CONFIG = "jetty/webdefault.xml";

    /**
     * 主函数
     *
     * @param args       参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws Exception {
        System.setProperty("spring.profiles.active", "development");
        Server server = createServer();
        try {
            server.start();
            while (true) {
                char c = (char) System.in.read();
                if (c == '\n') {
                    reload(server);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * 创建服务器
     *
     * @return 服务器
     */
    public static Server createServer() {
        Server server = new Server();
        server.setStopAtShutdown(true);
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] { connector });
        WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, "/");
        webContext.setDefaultsDescriptor(DEFAULT_WEBAPP_CONFIG);
        server.setHandler(webContext);
        return server;
    }

    /**
     * 重启服务器
     *
     * @param server     服务器
     * @throws Exception 异常
     */
    public static void reload(Server server) throws Exception {
        WebAppContext context = (WebAppContext) server.getHandler();
        System.out.println("开始重启");
        context.stop();
        WebAppClassLoader classLoader = new WebAppClassLoader(context);
        classLoader.addClassPath("target/classes");
        classLoader.addClassPath("target/test-classes");
        context.setClassLoader(classLoader);
        context.start();
        System.out.println("重启完成");
    }
}
