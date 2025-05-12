package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Utils {
    public static int generateRandomNum(int min,int max){
        double random= Math.random()*(max-min)+min;
        return (int)random;
    }
    public static void setEnvVar(String key,String value) throws ConfigurationException {
        PropertiesConfiguration config =new PropertiesConfiguration("src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();
    }
    public static String getEnvVar(String key) throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("src/test/resources/config.properties");
        return config.getString(key);
    }


}
