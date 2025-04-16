package com.pqwz.pqwzep.service;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeAuthService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // BCrypt initialized

    public boolean loginById(Long id, String password) {
        System.out.println("/login endpoint hit!");

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isEmpty()) {
            System.out.println("Employee ID not found: " + id);
            return false;
        }

        Employee employee = optionalEmployee.get();

        System.out.println("DB password (hashed): " + employee.getPassword());
        System.out.println("Entered password: " + password);

        boolean match = passwordEncoder.matches(password, employee.getPassword());

        if (match) {
            System.out.println("Password matched.");
        } else {
            System.out.println("Password mismatch.");
        }

        return match;

    }
}



/*

package com.pqwz.pqwzep.service;
Places this class inside your service package (which holds business logic).

import com.pqwz.pqwzep.model.Employee;
Brings in your Employee model so we can work with employee records from the DB.

import com.pqwz.pqwzep.repository.EmployeeRepository;
Imports the repository you just created — which gives access to DB operations like findByEmployeeId().

import lombok.RequiredArgsConstructor;
This is a Lombok feature that auto-generates a constructor for any final fields.

import org.springframework.stereotype.Service;
Tells Spring that this class is a service bean.

It will be auto-managed and injectable into other classes (like your controller).

@Service
Marks the class as a service component.

Spring will automatically detect it and allow other classes to @Autowired it.

@RequiredArgsConstructor
Lombok annotation that creates a constructor for private final EmployeeRepository employeeRepository.

You don’t have to write the constructor yourself.

public class EmployeeAuthService
Declares your service class, where we define methods related to authentication logic.

private final EmployeeRepository employeeRepository;
This is your link to the database.

Marked final to ensure it's set once and never changed.

Automatically initialized via constructor injection (because of @RequiredArgsConstructor).

public boolean login(String employeeId, String password)
This is the main method that handles login logic.

It takes the employee ID and password sent from the frontend.

Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(employeeId);
Queries the database to check if an employee with the given ID exists.

Optional safely wraps the result to prevent null pointer exceptions.

if (optionalEmployee.isEmpty()) { return false; }
If no employee is found → login fails.

Employee employee = optionalEmployee.get();
Gets the actual Employee object if present.

return employee.getPassword().equals(password);
Compares the password sent from the frontend with the one stored in DB.

 */

