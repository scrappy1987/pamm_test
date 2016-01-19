package util;

import play.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by a585493 on 26/10/2015.
 */
public class FileReaderUtility
{

    private final Logger.ALogger logger = Logger.of(this.getClass());

    public String readFile(String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try
        {
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);

                sb.append("\n");

                line = br.readLine();
            }

            return sb.toString();
        }
        finally
        {
            br.close();
        }
    }
}
