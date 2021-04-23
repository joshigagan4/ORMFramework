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