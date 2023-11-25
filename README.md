# SimpleMySQLLib
This library can make it easier to work with .db files.

To get started, create an object of the Database class:

Database databse = new Database();

Next, call init() method where you need to pass arguments as .db-file path, table name in this database and also boolean value which will determine whether to use the built-in logging system in this library 

Example: dataBase.init("path/to/databse.db", "TableName", true);

And next you can use all methods in this library

1. Change value in any column
   
  To do this, use the changeValue() method. It takes 4 string parameters. The first is the column in which you want to change the value. The second is the new meaning. The third is an additional column to identify the column where you want to change the value. The fourth   is the value in the additional column

![example](https://github.com/tonikirillov07/MySQLLib/assets/111236830/74796bfc-bbd6-4b03-8db2-64f816cb3d45)

![code_example](https://github.com/tonikirillov07/MySQLLib/assets/111236830/b631b16a-4cb2-4c48-8b5b-c8f701e46c7c)

2. Adding record

  To add a new record to the table, use the addRecord() method. It takes two string arrays. The first is the names of the columns. The second is the values in these columns. Note that the values for the columns in the second array must be specified in the order in which those columns appear in the first array.

  ![code_example_2](https://github.com/tonikirillov07/MySQLLib/assets/111236830/28a6fc7d-6899-4761-84ea-0f9401f48c15)

  ![result_2](https://github.com/tonikirillov07/MySQLLib/assets/111236830/01088017-930e-4fe2-a73f-2957694ad59b)

3. Setting null

   To set a column to null, use the setNull() method. It takes three parameters. The first one is the column where you need to make the null value. The second is an additional column to identify the first one, which was mentioned above. The third one is the value in the second column. The last two parameters will be used frequently in this library

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/32ac77fa-1dbf-4dfa-94ca-7b3170f8b296)

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/9511ddf1-44f2-4020-9d71-50ba8d36f8a0)

4. Value is exists

  To check if a value exists in a table, use the valueIsExists() method. It takes two parameters. The first is the name of the column where you want to check the value. The second is the value in this column. This method returns true or false value.

  ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/d541d78e-b758-41e6-bf4d-e42ca0b16b8b)

  ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/af4c2380-3121-4a84-be5b-0864e736e0b3)

5. Get value from column
   
  To get a value from a column, use the getElementFromColumn() method. It takes three parameters. The first is the name of the column where you want to get the value from. The second is a column for identification. The third is the value in the second column.

  ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/0172d023-9cf9-4b40-b942-b1d0c959e40e)

  ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/e09fb5cf-7144-4189-912e-0bf39349795a)

6. Delete table

   To delete the table from database use method deleteTable(). It takes one parameters and it's table name.

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/8e71d79b-db69-4014-9376-40b10cb58453)

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/287c0d1d-9a77-4895-ad9c-f7c8ee7fefba)

7. Create table

    To create a table, use the createTable() method. It takes two parameters. The first is the name of the table. The second is Column arrays. Column is a class of this library that contains some description of the column. When you create an instance of the Column class, it will ask for 6 standard parameters. The first is the name of the column. The second is the column type (set as a string parameter). In the Column class, there is a class called Types, which contains some types of columns, five types to be exact. The third is the size of the column (set as an integer parameter). The fourth is the Boolean parameter. Thanks to it, you can specify whether the value of the column can be null. The fifth is also a Boolean parameter. You can use it to specify whether the value in the column is unique. The sixth is the default value in the column (set as a string parameter).

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/2e3e3432-7b0e-41b4-896c-9e4e3d9449dc)

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/8491169a-0f6c-4387-89db-7e1273ccb5ef)


8. Delete record.

   To delete the record in databse, use the deleteRecord() method. It takes two parameters. The first is the column for identification the record. The second is the value in the identification column.

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/64f58952-0a26-4881-87d5-46d6634e68f1)

   ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/dfbe118e-e898-4a88-b124-1d768ba4442e)

9. Change connection

   If you want to work with another database, use the useAnotherConnection() method. It takes one parameter and it's the java.sql.Connection class. After using this method, you can use all methods thats has been described above, only all actions will be applied to another database.

10. Create connection.

    This can be helpful if you use the method described in the ninth point. This method simply creates an object of the java.sql.Connection class based on the path to the .db file that you pass as the only argument in the createConnection() method.  

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/ce03a9e1-df66-43df-9cdc-ba35d8fd40a9)

11. Get connection

    If the functionality described above is not enough and you want to write all the sql queries yourself, you need an object of the java.sql.Connection class, which can be obtained using the getConnection() method, which does not accept any arguments.

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/0a793f94-2235-4cbc-8499-b9f2e2182745)

12. Change table

    If you want to work with another table in the same database, use the changeTable() method, where the only argument is passed - the name of the table. After that, you can use all the methods described above, but all the actions will be applied to a different table

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/4e675952-203a-4551-9684-d28a1f5d0262)

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/a0f3f309-0fe3-4f30-9cd1-17db91190604)

13. Create databse

    Use the createDataBase() method to create another .db file. Method takes one parameter - path to new .db file.
    
    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/376c98ce-92b2-474b-a5a6-e1e160d52607)

    ![image](https://github.com/tonikirillov07/MySQLLib/assets/111236830/77a68b7e-899d-40f1-bf79-66d483726b27)

14. Database is exists

    The method databaseIsExists() just checking does a .db file exist. Nothing interesting.







  









