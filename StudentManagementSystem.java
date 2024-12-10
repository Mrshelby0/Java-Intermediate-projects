import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {

    // Student Class
    static class Student {
        private int id;
        private String name;
        private int age;
        private String grade;
        private String address;

        public Student(int id, String name, int age, String grade, String address) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + 
                   ", Grade: " + grade + ", Address: " + address;
        }
    }

    // StudentManager Class
    static class StudentManager {
        private ArrayList<Student> students;

        public StudentManager() {
            students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
            System.out.println("Student added: " + student.getName());
        }

        public void removeStudent(int id) {
            students.removeIf(student -> student.getId() == id);
            System.out.println("Student removed.");
        }

        public void updateStudent(int id, Scanner scanner) {
            for (Student student : students) {
                if (student.getId() == id) {
                    System.out.println("Updating details for: " + student.getName());
                    System.out.print("Enter new name (or press Enter to skip): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) student.setName(newName);

                    System.out.print("Enter new age (or press Enter to skip): ");
                    String newAge = scanner.nextLine();
                    if (!newAge.isEmpty()) student.setAge(Integer.parseInt(newAge));

                    System.out.print("Enter new grade (or press Enter to skip): ");
                    String newGrade = scanner.nextLine();
                    if (!newGrade.isEmpty()) student.setGrade(newGrade);

                    System.out.print("Enter new address (or press Enter to skip): ");
                    String newAddress = scanner.nextLine();
                    if (!newAddress.isEmpty()) student.setAddress(newAddress);

                    System.out.println("Student details updated.");
                    return;
                }
            }
            System.out.println("Student not found.");
        }

        public void viewStudents() {
            System.out.println("Students in the system:");
            for (Student student : students) {
                System.out.println(student);
            }
        }

        public void searchStudent(String query) {
            System.out.println("Search results for: " + query);
            for (Student student : students) {
                if (student.getName().toLowerCase().contains(query.toLowerCase()) ||
                    Integer.toString(student.getId()).contains(query)) {
                    System.out.println(student);
                }
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. View Students");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Student Address: ");
                    String address = scanner.nextLine();
                    manager.addStudent(new Student(id, name, age, grade, address));
                }
                case 2 -> {
                    System.out.print("Enter Student ID to remove: ");
                    int id = scanner.nextInt();
                    manager.removeStudent(id);
                }
                case 3 -> {
                    System.out.print("Enter Student ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    manager.updateStudent(id, scanner);
                }
                case 4 -> manager.viewStudents();
                case 5 -> {
                    System.out.print("Enter name or ID to search: ");
                    String query = scanner.nextLine();
                    manager.searchStudent(query);
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
