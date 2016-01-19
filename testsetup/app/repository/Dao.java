package repository;

import com.typesafe.config.ConfigFactory;
import play.Logger;
import util.ApplicationRootHelper;
import util.FileReaderUtility;
import util.PropertiesHelper;

import javax.inject.Inject;
import java.io.File;
import java.util.Properties;

/**
 * Created by a585493 on 26/10/2015.
 */
public class Dao
{
    private static final Logger.ALogger logger = Logger.of(Dao.class);

    private EntityManagerProvider emp;

    private FileReaderUtility sqlReader;

    private ApplicationRootHelper appRootHelper;

    private PropertiesHelper propertiesHelper;

    @Inject public Dao(EntityManagerProvider emp, FileReaderUtility sqlReader, ApplicationRootHelper appRootHelper,
            PropertiesHelper propertiesHelper)
    {
        this.emp = emp;

        this.sqlReader = sqlReader;

        this.appRootHelper = appRootHelper;

        this.propertiesHelper = propertiesHelper;
    }

    public final String executeQuery(Long id)
    {
        String sqlString = null;

        String sqlPropertiesFileLocation = getPropertiesFilePath();

        logger.info("SQL Properties File Location " + sqlPropertiesFileLocation);

        Properties sqlFileProperties = propertiesHelper.getProperties(sqlPropertiesFileLocation);

        logger.info("Script id " + id);

        logger.info("Number of Properties in sqlFileProperties " + sqlFileProperties.size());

        String sqlFilePath = appRootHelper.getApplicationRootPath() + File.separatorChar + sqlFileProperties.get(id.toString());

        try
        {
            logger.info("SQL File Path: " + sqlFilePath);

            sqlString = sqlReader.readFile(sqlFilePath);
        }
        catch (Exception e)
        {
            return "Error reading SQL file " + sqlFilePath + " -> exception: " + e.getMessage();
        }

        emp.getEntityManager().createNativeQuery(sqlString).executeUpdate();

        return "Query executed succesfully";
    }

    private String getPropertiesFilePath()
    {
        return appRootHelper.getApplicationRootPath() + File.separatorChar + ConfigFactory.load()
                .getString("sql.scripts");
    }
}
