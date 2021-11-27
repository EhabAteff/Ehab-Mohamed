package credentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	protected Properties prop = null;
	protected FileInputStream loadFile = null;


	public String getUrl(String option) throws IOException {
		this.readConfigFile();
		return prop.getProperty(option);
	}

	public String getG_Locator(String locator) throws IOException {
		this.readG_LocatorsFile();
		return prop.getProperty(locator);
	}


	public void readConfigFile() throws IOException
	{
		prop = new Properties();
		loadFile = new FileInputStream("./src/main/java/Credentials/Configuration.properties");
		prop.load(loadFile);
	}

	public void readG_LocatorsFile() throws IOException
	{
		prop = new Properties();
		loadFile = new FileInputStream("./src/main/java/Credentials/GoogleLocators.properties");
		prop.load(loadFile);
	}
}