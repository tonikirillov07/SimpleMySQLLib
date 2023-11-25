package com.ds.mySQLLib;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.logging.Logger;

public class DataBase {
    String databasePath;
    String tableName;
    ShowExceptions showExs = new ShowExceptions();
    Logger logger = Logger.getLogger(getClass().getName());
    boolean doLogs = false;
    public Connection anotherConnection = null;

    public void init(String databasePath, String tableName, boolean doLogs){
        this.databasePath = databasePath;
        this.tableName = tableName;
        this.doLogs = doLogs;

        if(doLogs) logger.info("Database was initialized");
    }

    public void useAnotherConnection(Connection connection){
        this.anotherConnection = connection;
    }

    private Connection getDBConnection(){
        try {
            if(anotherConnection != null) {
                Class.forName("org.sqlite.JDBC");
                return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            }else{
                return anotherConnection;
            }
        }catch (Exception e){
            showExs.show(e);
        }
        return null;
    }

    public Object getElementFromColumn(String columnName, String addColumnNameForIdentification, String valueInSecondColumn){
        try {
            String get = "SELECT " + columnName + " FROM " + tableName + " WHERE " + addColumnNameForIdentification + "=" + "'" + valueInSecondColumn + "'";
            Object returnValue;

            ResultSet resultSet;
            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(get);
            resultSet = preparedStatement.executeQuery();
            returnValue = resultSet.getString(1);
            resultSet.close();

            return returnValue;
        }catch (Exception e){
            showExs.show(e);
        }

        return null;
    }

    public void changeValue(String columnName, String newValue, String addColumnNameForIdentification, String valueInSecondColumn){
        try {
            String change = "UPDATE " + tableName + " set " + columnName + "=" + "'" + newValue + "'" + " WHERE " + addColumnNameForIdentification + "=" + "'" + valueInSecondColumn + "'";

            PreparedStatement preparedStatement = getDBConnection().prepareStatement(change);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Value was changed to " + newValue + " in " + columnName);
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public boolean valueIsExists(String columnName, String value){
        try {
            String check = "SELECT * FROM " + tableName + " WHERE " + columnName + "=" + "'" + value + "'";

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(check);
            ResultSet resultSet = preparedStatement.executeQuery();

            int counter = resultSet.next() ? 1 : 0;

            preparedStatement.close();
            resultSet.close();

            return counter == 1;
        }catch (Exception e){
            showExs.show(e);
        }

        return false;
    }

    public void addRecord(String[] columns, String[] values){
        try {
            int questionMarksCounter = 0;

            StringBuilder write = new StringBuilder();
            write.append("INSERT INTO ").append(tableName).append(" (");

            for (String column : columns) {
                write.append(column).append(", ");
                questionMarksCounter++;
            }
            write.deleteCharAt(write.length() - 1);
            write.deleteCharAt(write.length() - 1);

            write.append(") values(");

            write.append("?,".repeat(Math.max(0, questionMarksCounter)));
            write.deleteCharAt(write.length() - 1);
            write.append(")");

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(write.toString());

            int id = 1;
            for (String value : values) {
                preparedStatement.setString(id, value);
                id++;
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Record was added");
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public void deleteRecord(String addColumnNameForIdentification, String valueInSecondColumn){
        try {
            String delete = "DELETE FROM " + tableName + " WHERE " + addColumnNameForIdentification + "=" + "'" + valueInSecondColumn + "'";

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(delete);

            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Record was deleted");
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public void setNull(String columnName, String addColumnNameForIdentification, String valueInSecondColumn){
        try {
            String changeCommand = "UPDATE " + tableName + " SET " + columnName + " = null WHERE " + addColumnNameForIdentification + "= '" + valueInSecondColumn + "'";

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(changeCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Value was set to null");
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public void createTable(String title, Column[] columns){
        try {
            StringBuilder cmd = new StringBuilder("CREATE TABLE " + title + " (");

            for (int i = 0; i != columns.length; i++) {
                cmd.append(columns[i].createColumn());
                cmd.append(", ");
            }

            cmd.deleteCharAt(cmd.length() - 1);
            cmd.deleteCharAt(cmd.length() - 1);
            cmd.append(")");

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(cmd.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Table " + title + " was created");
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public void changeTable(String tableName){
        this.tableName = tableName;
    }

    public void deleteTable(String tableName){
        try {
            String cmd = "DROP TABLE " + tableName;

            PreparedStatement preparedStatement = Objects.requireNonNull(getDBConnection()).prepareStatement(cmd);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            if(doLogs) logger.info("Table " + tableName + " was deleted at forever");
        }catch (Exception e){
            showExs.show(e);
        }
    }

    public boolean databaseIsExists(){
        return new File(databasePath).exists();
    }

    public void createDataBase(String path){
        if(doLogs) logger.info("Creating database " + path);
        new Thread(() -> {
            try {
                new File(path).createNewFile();

                if(doLogs) logger.info("Database " + path + " was created!");
            } catch (Exception e) {
                showExs.show(e);
            }
        }).start();
    }
}
