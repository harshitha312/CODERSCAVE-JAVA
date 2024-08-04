import java.util.*;
public class StudentInformationSystem {
    static class Student {
        private String studentId;
        private String name;
        private String dateOfBirth;
        private String address;
        private String contactInfo;
        public Student(String studentId, String name, String dateOfBirth, String address, String contactInfo) {
            this.studentId = studentId;
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.address = address;
            this.contactInfo = contactInfo;
        }
        @Override
        public String toString() {
            return "Student ID: " + studentId + "\nName: " + name + "\nDate of Birth: " + dateOfBirth +
                   "\nAddress: " + address + "\nContact Info: " + contactInfo;
        }
    }
    static class Course {
        private String courseId;
        private String courseName;
        private int credits;
        public Course(String courseId, String courseName, int credits) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.credits = credits;
        }
        @Override
        public String toString() {
            return "Course ID: " + courseId + "\nCourse Name: " + courseName + "\nCredits: " + credits;
        }
    }
    static class Enrollment {
        private String studentId;
        private String courseId;
        private String semester;
        private double grade;
        public Enrollment(String studentId, String courseId, String semester, double grade) {
            this.studentId = studentId;
            this.courseId = courseId;
            this.semester = semester;
            this.grade = grade;
        }
    }
    static class DatabaseManager {
        private Map<String, Student> students = new HashMap<>();
        private Map<String, Course> courses = new HashMap<>();
        private Map<String, Enrollment> enrollments = new HashMap<>();
        public void addStudent(Student student) {
            students.put(student.studentId, student);
        }
        public void addCourse(Course course) {
            courses.put(course.courseId, course);
        }
        public void enrollStudent(String studentId, String courseId, String semester, double grade) {
            Enrollment enrollment = new Enrollment(studentId, courseId, semester, grade);
            enrollments.put(studentId + "-" + courseId, enrollment);
        }
        public Student getStudent(String studentId) {
            return students.get(studentId);
        }
        public Course getCourse(String courseId) {
            return courses.get(courseId);
        }
    }
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        Student student1 = new Student("S001", "John Doe", "2005-01-01", "Hyderabad", "555-6969");
        Student student2 = new Student("S002", "Jane Smith", "2004-02-02", "Karnataka", "696-5678");
        dbManager.addStudent(student1);
        dbManager.addStudent(student2);
        Course course1 = new Course("C001", "Introduction to Programming", 3);
        Course course2 = new Course("C002", "Data Structures", 4);
        dbManager.addCourse(course1);
        dbManager.addCourse(course2);
        dbManager.enrollStudent("S001", "C001", "Fall 2024", 90.0);
        dbManager.enrollStudent("S002", "C002", "Spring 2024", 85.0);
        Student student = dbManager.getStudent("S001");
        Course course = dbManager.getCourse("C001");
        System.out.println("Student Information:\n" + student);
        System.out.println("\nCourse Information:\n" + course);
    }
}
