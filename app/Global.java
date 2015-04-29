import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import play.*;
import play.db.DB;

public class Global extends GlobalSettings {

    public void onStart(Application app) {
        Logger.info("Application has started.");
    }

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}


