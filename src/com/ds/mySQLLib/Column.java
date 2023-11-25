package com.ds.mySQLLib;

public class Column {
    private final String name;
    private final String type;
    private double size;
    private final boolean isNotNull;
    private final boolean isUnique;
    private final String defaultValue;

    public Column(String name, String type, int size, boolean isNotNull, boolean isUnique, String defaultValue) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.isNotNull = isNotNull;
        this.isUnique = isUnique;
        this.defaultValue = defaultValue;
    }

    public String createColumn(){
        StringBuilder cmd = new StringBuilder(name + " " + type + " (" + size + ") DEFAULT ("+"'"+defaultValue+"'"+")");

        if(isNotNull) cmd.append(" NOT NULL ");
        if(isUnique) cmd.append(" UNIQUE ");

        return cmd.toString();
    }

    public class Types{
        public static final String TEXT = "TEXT";
        public static final String REAL = "REAL";
        public static final String BLOB = "BLOB";
        public static final String NUMERIC = "NUMERIC";
        public static final String INTEGER = "INTEGER";
    }
}
