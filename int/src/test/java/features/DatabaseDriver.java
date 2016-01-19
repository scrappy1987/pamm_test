package features;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Encapsulates DbUnit to provide management of test data for integration testing purposes.
 */
public class DatabaseDriver {
    private IDatabaseTester tester;
    private final String connectionString;
    private final String username;
    private final String password;
    private final String jdbcDriver;

    /**
     * Creates an instance of the JdbcDatabaseDriver using the default configuration.
     * The default configuration should be stored in a file called db.properties located at
     * the root of the project.
     */
    public DatabaseDriver() {
        this("db.properties");
    }

    /**
     * Creates an instance of the JdbcDatabaseDriver using the specified configuration.
     * @param propertiesFile the path to the property file used to configure the driver.
     */
    public DatabaseDriver(String propertiesFile) {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(propertiesFile);
        if (stream == null) {
            throw new RuntimeException(String.format("Failed to load database configuration properties file '%s'", propertiesFile), null);
        }
        try {
            props.load(stream);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not load database driver property file.", e);
        }
        this.connectionString = props.getProperty("connectionString");
        this.password = props.getProperty("password");
        this.username = props.getProperty("username");
        this.jdbcDriver = props.getProperty("jdbcDriver");
        try {
            tester = new JdbcDatabaseTester(jdbcDriver, connectionString, username, password);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not create instance of the JDBC database driver. JDBC driver class not found." +
                    "Add a dependency to the database driver artifact.", e);
        }
    }

    public void setup(String dataFilePath) {
        IDataSet ds = null;
        try {
            FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
            ds = loader.load(dataFilePath);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        setup(ds);
    }

    public void setup(IDataSet dataSet) {
        try {
            tester.setDataSet(dataSet);
            tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            tester.onSetup();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setup(File datafile)  {
        setup(datafile, DatabaseOperation.CLEAN_INSERT);
    }

    private void setup(File datafile, DatabaseOperation operation) {
        try {
            FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
            IDataSet ds = loader.getBuilder().build(datafile);
            tester.setDataSet(ds);
            tester.setSetUpOperation(operation);
            tester.onSetup();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ITable query(String query, Object... params) {
        ITable result = null;
        try {
            QueryDataSet queryDataSet = new QueryDataSet(tester.getConnection());
            queryDataSet.addTable("query", String.format(query, params));
            result = queryDataSet.getTable("query");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void teardown() {
        try {
            tester.onTearDown();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void export(String tableName, String query, File file) {
        try {
            IDatabaseConnection dbConnection = tester.getConnection();
            QueryDataSet partialDataset = new QueryDataSet(dbConnection);
            partialDataset.addTable(tableName, query);
            FlatXmlDataSet.write(partialDataset, new FileOutputStream(file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void export(Map<String, String> tables, File file) {
        try {
            IDatabaseConnection dbConnection = tester.getConnection();
            QueryDataSet partialDataset = new QueryDataSet(dbConnection);
            for (Map.Entry<String, String> table : tables.entrySet()) {
                partialDataset.addTable(table.getKey(), table.getValue());
            }
            FlatXmlDataSet.write(partialDataset, new FileOutputStream(file));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void export(File file, String... tableNameList) {
        Map<String, String> tables = new HashMap<>();
        for(String tableName : tableNameList){
            tables.put(tableName, String.format("SELECT * FROM %s", tableName));
        }
        export(tables, file);
    }

    public void update(String updateStatement, Object... params) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DriverManager.getConnection(connectionString, username, password);
            statement = conn.prepareStatement(String.format(updateStatement, params));
            statement.execute();
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public IDataSet load(String dataFilePath)  {
        try {
            FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
            IDataSet set = loader.load(dataFilePath);
            return set;
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("Failed to load record from file '%s'", dataFilePath), e);
        }
    }

    public void save(IDataSet set) {
        try {
            setup(set);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to save set.", e);
        }
    }
}
