package util;

import play.Logger;

import java.io.*;

public class MoveFileUtility
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    public String moveFileToLocation(String sourceLocation, String destLocation)
    {
        InputStream inStream = null;

        OutputStream outStream = null;

        try
        {
            File afile = new File(sourceLocation);

            File bfile = new File(destLocation);

            inStream = new FileInputStream(afile);

            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = inStream.read(buffer)) > 0)
            {
                outStream.write(buffer, 0, length);
            }

            inStream.close();

            outStream.close();

            return "File moved succesfully";
        }
        catch (Exception e)
        {
            return "You have encountered a problem -> You most likely have input an invalid file id";
        }
    }
}
