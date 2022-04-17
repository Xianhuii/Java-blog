package jar;

import java.util.prefs.Preferences;

public class JarDemo {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.home"));
//        System.getProperties().forEach((key, val) -> {
//            System.out.println(key + ": " + val);
//        });
        Preferences preferences = Preferences.userRoot();
        System.out.println(preferences);
    }
}
