# pgjdbc-ng-test1

This test project tests an issue with multi-layered UDTs being written using SQLData mappers.  

How to setup the DB to run the tests.   
1. Create role named `test1` with password of `test1`, and enable allow login.  
2. Create Database named `test1` with the role `test1` as the owner.   
3. Run the `main.sql` script on the database, The script can be found in the `src\main\java\io\tlf\sqltest1` folder.  
The project can be opened and run in netbeans. 
