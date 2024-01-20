package model.DAO;

import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Initialize the data source by loading the database driver class and creating the data source object.
 * @throws ClassNotFoundException if the database driver class is not found
 */
public class DAOConnection {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DAOConnection.class);

    private static DataSource dataSource;

    static void setDataSource(DataSource ds) {
        dataSource = ds;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataSource = createDataSource();
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found: " + e.getMessage());
        }
    }

    private static DataSource createDataSource() {
        Properties props = new Properties();
        try (InputStream input = DAOConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("Error loading configuration file: " + e.getMessage());
        }

        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(
                        props.getProperty("db.url"),
                        props.getProperty("db.username"),
                        props.getProperty("db.password")
                );
            }


            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection(props.getProperty("db.url"), username, password);
            }

            // Other DataSource interface methods (not used in this example)

            @Override
            public <T> T unwrap(Class<T> iface) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) {
                throw new UnsupportedOperationException();
            }

            @Override
            public java.io.PrintWriter getLogWriter() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLoginTimeout(int seconds) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int getLoginTimeout() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Logger getParentLogger() {
                return null;
            }
        };
    }

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Error getting connection", e);
        }
        return con;
    }

    public static void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}