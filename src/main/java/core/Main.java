package core;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        EntryPoint entryPoint = new EntryPoint();
        entryPoint.start();

    }
}
