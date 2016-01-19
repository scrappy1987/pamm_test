package controllers;

import com.typesafe.config.ConfigFactory;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import repository.FileAccessObject;
import util.FileReaderUtility;
import util.FileWriterUtility;
import util.MoveFileUtility;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by a585493 on 27/10/2015.
 */
public class FileExecutionController extends Controller
{
    private static final Logger.ALogger logger = Logger.of(FileExecutionController.class);

    private FileAccessObject fao;

    @Inject public FileExecutionController(FileAccessObject fao)
    {
        this.fao = fao;
    }

    public Result execute(Long id)
    {
        String sourceLocation = fao.getFileLocation(id);

        MoveFileUtility fileMover = fao.getFileMover();

        String destLocation = ConfigFactory.load().getString("test.file.destination") + id + ".txt";

        String status = fileMover.moveFileToLocation(sourceLocation, destLocation);

        return ok(status);
    }
}
