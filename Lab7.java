package Lab7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lab7 {

    public static class Worker {
        private String firstName;
        private String lastName;
        private double salary;
        private String position;

        public Worker(String firstName, String lastName, double salary, String position) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
            this.position = position;
        }

        public double getSalary() {
            return salary;
        }

        public String getPosition() {
            return position;
        }

        @Override
        public String toString() {
            return "Worker{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", salary=" + salary +
                    ", position='" + position + '\'' +
                    '}';
        }
    }

    public static class Department {
        private String name;
        private Worker headOfDepartment;
        private List<Worker> workers;

        public Department(String name, Worker headOfDepartment) {
            this.name = name;
            this.headOfDepartment = headOfDepartment;
            this.workers = new ArrayList<>();
        }

        public void addWorker(Worker worker) {
            workers.add(worker);
        }

        public List<Worker> getWorkers() {
            return workers;
        }

        public Worker getHeadOfDepartment() {
            return headOfDepartment;
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", headOfDepartment=" + headOfDepartment +
                    ", workers=" + workers +
                    '}';
        }
    }

    public static class Company {
        private String name;
        private Worker director;
        private List<Department> departments;

        public Company(String name, Worker director) {
            this.name = name;
            this.director = director;
            this.departments = new ArrayList<>();
        }

        public void addDepartment(Department department) {
            departments.add(department);
        }

        public List<Department> getDepartments() {
            return departments;
        }

        // Завдання 1: Знайти максимальну зарплату
        public double findMaxSalary() {
            double maxSalary = Double.MIN_VALUE;

            for (Department department : departments) {
                Iterator<Worker> iterator = department.getWorkers().iterator();
                while (iterator.hasNext()) {
                    Worker worker = iterator.next();
                    if (worker.getSalary() > maxSalary) {
                        maxSalary = worker.getSalary();
                    }
                }

                if (department.getHeadOfDepartment().getSalary() > maxSalary) {
                    maxSalary = department.getHeadOfDepartment().getSalary();
                }
            }

            if (director.getSalary() > maxSalary) {
                maxSalary = director.getSalary();
            }

            return maxSalary;
        }

        // Завдання 2: Визначити відділ, в якому хоча б один працівник отримує більшу зарплату за свого начальника
        public List<Department> findDepartmentsWithHigherSalary() {
            List<Department> result = new ArrayList<>();

            for (Department department : departments) {
                boolean hasEmployeesWithHigherSalary = false;

                for (Worker worker : department.getWorkers()) {
                    if (worker.getSalary() > department.getHeadOfDepartment().getSalary()) {
                        hasEmployeesWithHigherSalary = true;
                        break;
                    }
                }

                if (hasEmployeesWithHigherSalary) {
                    result.add(department);
                }
            }

            return result;
        }

        // Завдання 3: Скласти список усіх працівників компанії
        public List<Worker> getAllWorkers() {
            List<Worker> allWorkers = new ArrayList<>();

            for (Department department : departments) {
                allWorkers.add(department.getHeadOfDepartment());
                Iterator<Worker> iterator = department.getWorkers().iterator();
                while (iterator.hasNext()) {
                    allWorkers.add(iterator.next());
                }
            }

            allWorkers.add(director);

            return allWorkers;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    ", director=" + director +
                    ", departments=" + departments +
                    '}';
        }

        public static void main(String[] args) {
            // Створення компанії та додавання департаментів, працівників і зарплат
            Worker director = new Worker("Jack", "Sparrow", 100000, "Director");
            Company company = new Company("Example Company", director);

            Department department1 = new Department("IT", new Worker("Dominic", "Toretto", 80000, "Head"));
            department1.addWorker(new Worker("John", "Doe", 50000, "Employee"));
            department1.addWorker(new Worker("Alice", "Smith", 60000, "Employee"));

            Department department2 = new Department("Finance", new Worker("Obi-wan", "Kenobi", 90000, "Head"));
            department2.addWorker(new Worker("Bob", "Johnson", 55000, "Employee"));
            department2.addWorker(new Worker("Eva", "Williams", 70000, "Employee"));

            company.addDepartment(department1);
            company.addDepartment(department2);

            // Завдання 1: Знайти максимальну зарплату
            double maxSalary = company.findMaxSalary();
            System.out.println("Максимальна зарплата: " + maxSalary);

            // Завдання 2: Визначити відділ, в якому хоча б один працівник отримує більшу заробітну платню
            List<Department> departmentsWithHigherSalary = company.findDepartmentsWithHigherSalary();
            System.out.println("Відділи з працівниками, які отримують більшу зарплату за свого начальника: " + departmentsWithHigherSalary);

            // Завдання 3: Скласти список усіх працівників компанії
            List<Worker> allWorkers = company.getAllWorkers();
            System.out.println("Список усіх працівників компанії: " + allWorkers);
        }
    }

    public static void main(String[] args) {
        Company.main(args);
    }
}













