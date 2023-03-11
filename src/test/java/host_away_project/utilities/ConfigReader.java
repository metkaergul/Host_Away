package host_away_project.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    //create the object from Properties class
    private static Properties properties=new Properties();

    static {

        try {
            //we need to open the file in java memory using file input stream
            FileInputStream file= new FileInputStream("configuration.properties");

            //load the properties object using fileInputStream object
            properties.load(file);
        } catch (IOException e) {

            System.out.println("file is not found in the configuration class");
            e.printStackTrace();
        }

    }
    public static String getProperty(String key){
        return properties.getProperty(key);

    }

}
