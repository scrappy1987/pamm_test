package util;

import play.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by a585493 on 26/10/2015.
 */
public class PropertiesHelper
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    public Properties getProperties(String propertiesFilePath)
    {
        Properties props = new Properties();

        FileInputStream fis = null;

        try
        {
            logger.info("Getting properties for Path " + propertiesFilePath);

            fis = new FileInputStream(propertiesFilePath);

            logger.info("Got File input Stream ");

            props.load(fis);

            logger.info("Got Properties size is " + props.size());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }

        return props;
    }
}
