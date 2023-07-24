package com.ss.web.app.service;

import com.ss.web.app.model.Student;
import com.ss.web.app.repository.StudentRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

  @Test
  public void addStudent() {
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);
    Student student = new Student(4L, "ana", "perez");

    when(repo.save(student)).thenReturn(student);
    Student studentExpected = studentServiceImpl.addStudent(student);

    assertNotNull(studentExpected);
    assertEquals(student, studentExpected);
  }

  @Test
  public void findAllTest(){
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);
    List<Student> students = new ArrayList<>();
    students.add(new Student(1L, "Sho", "Test"));
    students.add(new Student(2L, "Auto", "Test"));
    students.add(new Student(3L, "Curl", "Test"));
    when(repo.findAll()).thenReturn(students);

    List<Student> studentsExpected = studentServiceImpl.findAll();

    assertNotNull(studentsExpected);
    assertEquals(students, studentsExpected);
  }

  @Test
  public void delete_ExistentUser_True(){
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);
    Student student = new Student(3L, "Curl", "Test");
    when(repo.delete(student)).thenReturn(true);

    boolean actualResult = studentServiceImpl.delete(student);

    assertTrue(actualResult);
  }

  @Test
  public void delete_NonExistentUser_False(){
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);
    Student newStudent = new Student(1L, "Pepe", "Pe");

    boolean actualResult = studentServiceImpl.delete(newStudent);

    assertFalse(actualResult);
  }

  @Test
  public void edit_ExistentUser_Success() {
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);

    Student student = new Student(3L, "Curl", "Test");
    when(repo.edit(student)).thenReturn(student);

    Student updatedStudent = studentServiceImpl.edit(student);

    assertNotNull(updatedStudent);
    assertEquals(student, updatedStudent);
  }

  @Test
  public void edit_NonExistentUser_Null() {
    StudentRepo repo = mock(StudentRepo.class);
    StudentServiceImpl studentServiceImpl = new StudentServiceImpl(repo);

    Student student = new Student(100L, "John", "Doe");
    when(repo.edit(student)).thenReturn(null);

    Student updatedStudent = studentServiceImpl.edit(student);

    assertNull(updatedStudent);
  }
}