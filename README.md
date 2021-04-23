<h1>ORMFramework</h1>

It is java based framework for Object Relational Mapping.
This framework is designed to free the user from writing SQL statements/Queries to manage the database.
User can execute most of the SQL queries/operations such as insertion,updation,deletion and retrieval without even knowing any SQL.
It is specifically designed considering MySQL RDBMS syntaxes.

<h1>Prerequisites</h1>

The user needs to download the jar file and put it into the working directory or the lib path. That's it! The framework will take care of the rest.

<h1>How to use the framework</h1>

We have created a database named as 'tmschool' and created two tables 'course' and 'student' to demonstrate the working of the framework :-

![show_tables](https://user-images.githubusercontent.com/59817693/115868926-cbe92c00-a45a-11eb-923f-fc8d78c44338.PNG)

![course](https://user-images.githubusercontent.com/59817693/115868977-ddcacf00-a45a-11eb-96a5-e2451bd3bf58.PNG)

![student](https://user-images.githubusercontent.com/59817693/115868999-e1f6ec80-a45a-11eb-8715-bc248c771293.PNG)



This framework comes with a tool (POJOGenerator) that eases all your efforts.

Just create a conf.json file like we show you in below example :-

```
{
"jdbc-driver":"com.mysql.cj.jdbc.Driver",
"connection-url":"jdbc:mysql://localhost:3306/",
"database":"tmschool",
"username":"admin",
"password":"admin",
"package-name":"com.thinking.machines.ormframework.pojo",
"jar-file-name":"ormframework.jar"
}
```

and then create a class and in that class write following code :-

```
// example to generate POJO's
import com.thinking.machines.ormframework.utils.*;
class psp
{
public static void main(String gg[])
{
try
{
POJOGenerator.generatePOJOEquivalentToTable();
}catch(Exception e)
{
System.out.println(e);
}
}
}

```

when you run that code our tool POJOGenerator generates POJO's by creating folder structure that you specified in conf.json against package-name and also create its equivalent jar file with name that you specified in conf.json against jar-file-name in dist folder.


Our tool will create file like this:-


for Course

```
package com.thinking.machines.ormframework.pojo;
import com.thinking.machines.ormframework.annotations.*;
import java.util.*;
import java.math.*;
@Table(name="course")
public class Course{
@PrimaryKey
@AutoIncrement
@Column(name="code")
public int code;

@Column(name="title")
public String title;

}
```

for Student

```
package com.thinking.machines.ormframework.pojo;
import com.thinking.machines.ormframework.annotations.*;
import java.util.*;
import java.math.*;
@Table(name="student")
public class Student{
@ForeignKey(parent="course",column="code")
@Column(name="course_code")
public int courseCode;

@Column(name="gender")
public String gender;

@Column(name="date_of_birth")
public Date dateOfBirth;

@Column(name="last_name")
public String lastName;

@Column(name="aadhar_card_number")
public String aadharCardNumber;

@PrimaryKey
@Column(name="roll_number")
public int rollNumber;

@Column(name="first_name")
public String firstName;

}
```

<h3>Annotations</h3>

If the user solely depends upon the tools he/she may never need to use or worry about the annotations. But if he/she manually creates the class as per framework standard following annotations will be useful:

Annotation | Description
------------ | -------------
Table | It is a class level annotation and must be used with the class. The value of this annotation is the table_name that the user wants to map to the object.
Column | This annotation can only be used with the fields of the class. The value of this annotation is the column_name of the table that the user wants to map with the field.
PrimaryKey | This annotation can only be used with the field that is mapped to the primary key of the table.
AutoIncrement | This annotation can only be used with the field mapped to a column which is autoincremented.
ForeignKey | This annotation can only be used with the field that is mapped to the foreign key of the table.

<h3>DataManager</h3>

Following DataManager methods of our framework will be useful for insertion, updation, retrieval and deletion of the data:

Return Type | Method | Description
------------ | ------------- | --------------
DataManager | getDataManager() | A static method that gives you the instance of the framework. The Framework uses Singleton design technique.
void | begin() | Each and every transaction user want to do must start from begin().
void | save(Object object) | Does relational mapping of the received object and saves the information extracted from the object into the database.
void | delete(Object object) | delete the object from the database by looking for the field mapped to the primary key in the object.
void | update(Object object) | Updates all the details of the given object into the database.
Query | query(Class<?> tableClass) | It retrives data from the database.
void | end() | Commits the transaction.

<h3>Query</h3>

Following are the methods of Query class:-

Return Type | Method | Description
------------ | ------------- | --------------
Query | where(String value) | Takes the value and adds 'WHERE' keyword followed by the value to the so far generated query string.
Query | eq(Object value) | Takes the value and adds '=' followed by the value to the so far generated query string.
Query | le(Object value) | Takes the value and adds '<=' followed by the value to the so far generated query string.
Query | lt(Object value) | Takes the value and adds '<' followed by the value to the so far generated query string. 
Query | gt(Object value) | Takes the value and adds '>' followed by the value to the so far generated query string.
Query | ge(Object value) | Takes the value and adds '>=' followed by the value to the so far generated query string.
Query | ne(Object value) | Takes the value and adds '!=' followed by the value to the so far generated query string.
Query | orderBy(String value) | Takes the value and adds 'ORDER BY' followed by the value to the so far generated query string.
Query | ascending() | Takes the value and adds 'ASC' to the so far generated query string.
Query | descending() | Takes the value and adds 'DESC' to the so far generated query string.
Query | and(String value) | Takes the value and adds 'AND' to the so far generated query string.
Query | or(String value) | Takes the value and adds 'OR' to the so far generated query string.
List | fire() | Return the java.util.List type of object.

<h3>Examples</h3>

1)First Example will show you how to do save operation.

```
// example to show the save operation
import java.text.*;
import java.util.*;
import com.thinking.machines.ormframework.exceptions.*;
import com.thinking.machines.ormframework.annotations.*;
import com.thinking.machines.ormframework.framework.*;
import com.thinking.machines.ormframework.pojo.*;
class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();

Course c=new Course();
c.title="JAVA";
dm.begin();
dm.save(c);
dm.end();

SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
Student s=new Student();
s.firstName="Gagan";
s.lastName="Joshi";
s.rollNumber=101;
s.dateOfBirth=format.parse("2000-07-03");
s.aadharCardNumber="ABCD1234";
s.gender="M";
s.courseCode=4;
dm.begin();
dm.save(s);
dm.end();
}catch(Exception e)
{
System.out.println(e);
}
}
}
```

2)Second Example will show you how to do update operation.

```
// example to show the update operation
import java.text.*;
import java.util.*;
import com.thinking.machines.ormframework.exceptions.*;
import com.thinking.machines.ormframework.annotations.*;
import com.thinking.machines.ormframework.framework.*;
import com.thinking.machines.ormframework.pojo.*;
class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
Student s=new Student();
s.firstName="Sachin";
s.lastName="Sharma";
s.rollNumber=101;
s.dateOfBirth=format.parse("2000-12-15");
s.aadharCardNumber="PQRST1234";
s.gender="M";
s.courseCode=4;
dm.begin();
dm.update(s);
dm.end();
}catch(Exception e)
{
System.out.println(e);
}
}
}
```

3)Third Example will show you how to do delete operation.

```
// example to show the delete operation
import java.text.*;
import java.util.*;
import com.thinking.machines.ormframework.exceptions.*;
import com.thinking.machines.ormframework.annotations.*;
import com.thinking.machines.ormframework.framework.*;
import com.thinking.machines.ormframework.pojo.*;
class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();
Student s=new Student();
s.rollNumber=101;
dm.begin();
dm.delete(s);
dm.end();
}catch(Exception e)
{
System.out.println(e);
}
}
}
```

4)Fourth Example will show you how to do retrieval operation.

```
// example to show the retrieval operation
import java.text.*;
import java.util.*;
import com.thinking.machines.ormframework.exceptions.*;
import com.thinking.machines.ormframework.annotations.*;
import com.thinking.machines.ormframework.framework.*;
import com.thinking.machines.ormframework.pojo.*;
class psp
{
public static void main(String gg[])
{
try
{
DataManager dm=DataManager.getDataManager();
dm.begin();
List<Student> list=dm.query(Student.class).where("rollNumber").eq(1).and().where("name").eq("Gagan Joshi").fire();
dm.end();
dm.begin();
List<Student> list2=dm.query(Student.class).orderBy("firstName").fire();
dm.end();
dm.begin();
List<Student> list5=dm.query(Student.class).where("rollNumber").gt(1).orderBy("lastName").fire();
dm.end();
dm.begin();
List<Student> list3=dm.query(Student.class).where("rollNumber").gt(1).orderBy("firstName").descending().orderBy("rollNumber").fire();
dm.end();
for(Student ss:list3)
{
System.out.println("roll_no::"+ss.rollNumber+"   ,Name::"+ss.firstName+"   ,gender::"+ss.gender);
}
}catch(Exception e)
{
System.out.println(e);
}
}
}
```

