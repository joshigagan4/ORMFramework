package com.thinking.machines.ormframework.pojo;
import com.thinking.machines.ormframework.annotations.*;
import java.util.*;
import java.math.*;
@Table(name="student")
public class Student{
@Column(name="aadhar_card_number")
public String aadharCardNumber;

@ForeignKey(parent="course",column="code")
@Column(name="course_code")
public int courseCode;

@Column(name="gender")
public String gender;

@Column(name="date_of_birth")
public Date dateOfBirth;

@Column(name="last_name")
public String lastName;

@PrimaryKey
@Column(name="roll_number")
public int rollNumber;

@Column(name="first_name")
public String firstName;

}