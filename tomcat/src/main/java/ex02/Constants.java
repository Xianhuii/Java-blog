package ex02;

import java.io.File;

public class Constants {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "tomcat" + File.separator + "webroot";

    public static final String WEB_TARGET =
            System.getProperty("user.dir") + "/tomcat/target/classes/";
}