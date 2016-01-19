package controllers;

import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.Dao;

import javax.inject.Inject;

/**
 * Created by a585493 on 26/10/2015.
 */
public class SQLExecutionController extends Controller
{
    private static final Logger.ALogger logger = Logger.of(SQLExecutionController.class);

    private Dao dao;

    @Inject public SQLExecutionController(Dao dao)
    {
        this.dao = dao;
    }

    @Transactional public Result execute(Long id)
    {
        logger.info("executing script SQLExecutionController id: " + id);

        String returnValue = null;

        try
        {
            returnValue = dao.executeQuery(id);
        }
        catch (Exception e)
        {
            logger.info("Exception executing query - " + e.getMessage());
        }

        logger.info("executed script SQLExecutionController return value: " + returnValue);

        return ok(returnValue);
    }
}
