package uz.epam.rentbikee.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final Lock locker = new ReentrantLock();
    private static final String DATA_SOURCE_URL = "jdbc:postgresql://localhost:5432/Bike";
    private static final String DATA_SOURCE_USERNAME = "postgres";
    private static final String DATA_SOURCE_PASSWORD = "root123";
    private static final int POOL_SIZE = 8;

    private final BlockingQueue<Connection> free = new LinkedBlockingQueue<>(POOL_SIZE);
    private final BlockingQueue<Connection> used = new LinkedBlockingQueue<>(POOL_SIZE);

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
//TODO log
        }
    }

    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection;
            try {
                connection = new ProxyConnection(DriverManager.getConnection(DATA_SOURCE_URL, DATA_SOURCE_USERNAME, DATA_SOURCE_PASSWORD));
                free.add(connection);
            } catch (SQLException e) {
                //TODO log
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
               isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = (ProxyConnection) free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {

            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                free.take().close();
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
