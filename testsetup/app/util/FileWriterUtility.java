package util;

import play.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by a585493 on 27/10/2015.
 */
public class FileWriterUtility
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    public void writeToFile(String fileLocation, String id, String fileContent)
    {
        File file = new File(fileLocation);

        String content = id + ": " + fileContent;

        try (FileOutputStream fop = new FileOutputStream(file))
        {
            // if file doesn't exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);

            fop.flush();

            fop.close();

            logger.info("Done");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
