package com.tomcat;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.*;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.webresources.DirResourceSet;
import java.io.File;


public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setHostname("localhost");
        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();

        Context ctx = tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/")
        );
        ctx.setResources(resources);
        tomcat.start();
        System.out.println("Start the server......");
        tomcat.getServer().await();
    }
}
