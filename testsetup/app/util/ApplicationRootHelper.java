package util;

import play.Logger;
import play.Play;

import java.io.File;

/**
 * Created by a585493 on 26/10/2015.
 */
public class ApplicationRootHelper
{
    private final Logger.ALogger logger = Logger.of(this.getClass());

    public String getApplicationRootPath()
    {
        File applicationRoot = Play.application().path();

        return applicationRoot.getAbsolutePath();
    }
}
