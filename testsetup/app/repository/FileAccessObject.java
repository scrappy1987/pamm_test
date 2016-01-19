package repository;

import com.typesafe.config.ConfigFactory;
import play.Logger;
import util.*;

import javax.inject.Inject;
import java.io.File;
import java.util.Properties;

/**
 * Created by a585493 on 27/10/2015.
 */
public class FileAccessObject
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    private FileReaderUtility reader;

    private FileWriterUtility writer;

    private MoveFileUtility mover;

    private PropertiesHelper propertiesHelper;

    private ApplicationRootHelper appRootHelper;

    @Inject public FileAccessObject(FileReaderUtility reader, FileWriterUtility writer, MoveFileUtility mover, PropertiesHelper propertiesHelper, ApplicationRootHelper appRootHelper)
    {
        this.reader = reader;

        this.writer = writer;

        this.mover = mover;

        this.propertiesHelper = propertiesHelper;

        this.appRootHelper = appRootHelper;
    }

    public FileWriterUtility getWriter()
    {
        return writer;
    }

    public FileReaderUtility getReader()
    {
        return reader;
    }

    public MoveFileUtility getFileMover()
    {
        return mover;
    }

    public final String getFileLocation(Long id)
    {
        Properties props = propertiesHelper.getProperties(getPropertiesFilePath());

        return (String) props.get(id);
    }

    private String getPropertiesFilePath()
    {
        return appRootHelper.getApplicationRootPath() + File.separatorChar + ConfigFactory.load()
                .getString("test.file.source");
    }
}
