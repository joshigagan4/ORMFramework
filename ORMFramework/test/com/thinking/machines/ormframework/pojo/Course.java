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