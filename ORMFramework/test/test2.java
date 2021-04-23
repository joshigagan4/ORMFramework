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