package com.pqwz.pqwzep.repository;

import com.pqwz.pqwzep.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
}

/*

package com.pqwz.pqwzep.repository;
This declares that this file belongs to your repository package. It helps organize your code logically.

✅ import com.pqwz.pqwzep.model.Employee;
You're importing the Employee entity (our model) so we can interact with it through the database.

✅ import org.springframework.data.jpa.repository.JpaRepository;
This is the Spring Data JPA interface that gives you powerful database access methods like:

findAll()

findById()

save()

delete()

Without writing any SQL manually.

✅ import java.util.Optional;
This allows us to return a value that may or may not be present (i.e., safe from null pointer errors).

✅ public interface EmployeeRepository extends JpaRepository<Employee, Long>
This defines a new interface.

It tells Spring: “Create a full DB repository for the Employee entity.”

Employee: the model class to be mapped to the database.

Long: the type of the primary key (our id field in Employee.java).


 */