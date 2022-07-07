package bca.pricing.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.bca.aini.config.AINIConfig;

@Configuration
public class AiniProperties extends BasePropertiesConstants {
    public static String ainiCertLocation;
    public static String ainiCertName;
    public static String ainiCertKey;
    public static String ainiApigwTokenUrl;

    public static String ainiApiKey;
    public static String ainiApiSecret;
    public static String ainiClientId;
    public static String ainiClientSecret;
    
    public static String ainiAccessAddress;
    public static String ainiAccessUrl;
    public static String ainiAppAccessKey;
    public static String ainiAppSource;

    @Autowired
    AINIConfig ainiConfig;

//    public void setAiniReloadableProperties(PropertiesConfiguration propertiesConfiguration) {
    public void setAiniReloadableProperties() {
//        ainiApiKey = propertiesConfiguration.getProperty("aini.api.key").toString();
//        ainiApiSecret = propertiesConfiguration.getProperty("aini.api.secret").toString();
//        ainiClientId = propertiesConfiguration.getProperty("aini.client.id").toString();
//        ainiClientSecret = propertiesConfiguration.getProperty("aini.client.secret").toString();
//
//        ainiAccessAddress = (String) propertiesConfiguration.getProperty("aini.access.address");
//        ainiAccessUrl = (String) propertiesConfiguration.getProperty("aini.access.url");
//        ainiAppAccessKey = (String) propertiesConfiguration.getProperty("aini.app.access.key");
//        ainiAppSource = (String) propertiesConfiguration.getProperty("aini.app.source");
//
//        ainiConfig.setAINIProperties(
//            propertiesConfiguration.getProperty("aini.apigw.token.url").toString(), propertiesConfiguration.getProperty("aini.cert.name").toString(), 
//            propertiesConfiguration.getProperty("aini.cert.location").toString(), propertiesConfiguration.getProperty("aini.cert.key").toString());
    	
    	ainiApiKey = "39af7eb6-3434-4598-8797-f0d331976235";
        ainiApiSecret = "ae9d66e1-636e-4314-b4ef-a07b3c266be1";
        ainiClientId = "af591b94";
        ainiClientSecret = "9885cbb9518f2a89c29215882f0271e7";

        ainiAccessAddress = "10.27.2.106:50077";
        ainiAccessUrl = "/aini/apis/";
        ainiAppAccessKey = "BwPuYKxlC5qJfwO0";
        ainiAppSource = "NLO2AINI";

		ainiConfig.setAINIProperties(
				"https://sso-apigw-int.dti.co.id/auth/realms/3scale-dev/protocol/openid-connect/token", "apigw-dev.jks",
				"C:/Users/U066233/Documents", "cwa-aini-2021");
    }
}
