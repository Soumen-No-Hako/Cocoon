package Cocoon_Java.ccn_env;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class CcnLangSetupCheck{
    public static void main(String[] args) {
        Properties osProps = System.getProperties();
//        System.out.println("loki");
//        osProps.forEach((key, value) -> {
//            System.out.println("Key :"+key+" => value: "+value);
//        });
//        System.out.println(osProps.getProperty("os.name"));
        System.out.println("==============================");
//        System.getenv().forEach((k,v)->{
//            System.out.println(k+" : "+v);
//        });
        if(System.getenv().get("OS").contains("Windows"))
        {
            //Check Java specific set ups
            String path = System.getenv().get("Path");
            if (path.contains("jdk"))
            {
                String jdkLocation = findJdkLocation(path).get();
                File f = new File(jdkLocation);
                if (!f.exists()) System.out.println("path is present but not the directory.");
                else System.out.println("JDK exists!");
            }
            if(path.contains("MinGW"))
            {
                String CLocation = findCLocation(path).get();
                File f = new File(CLocation);
                if (!f.exists()) System.out.println("path is present but not the directory.");
                else System.out.println("C compiler exists!");
            }
        }
        else
        {
            System.out.println("Sorry Code in progress for this OS.");
        }
    }

    private static Optional<String> findCLocation(String path) {
        var paths = path.split(";");
        return Arrays.stream(paths).filter(filepath -> filepath.contains("MinGW")).findFirst();
    }

    private static Optional<String> findJdkLocation(String path) {
        var paths = path.split(";");
        return Arrays.stream(paths).filter(k -> k.contains("jdk")).findFirst();
    }
}