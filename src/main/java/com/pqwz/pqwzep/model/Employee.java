package com.pqwz.pqwzep.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "manager_name", nullable = false)
    private String managerName;
}

/*

package com.pqwz.pqwzep.model;
This declares the package name.

1-In Java, every class belongs to a "folder"/package. This one lives in the model package (where all your data models/entities will go).

2-import jakarta.persistence.*;
This imports JPA (Jakarta Persistence API) annotations.

JPA is used to map this Java class to a SQL database table.

✅ import lombok.*;
This imports Lombok, a library that reduces boilerplate code (like writing getters/setters manually).

✅ @Entity
This tells Spring Boot and JPA: "This class represents a table in the database."

So here, Employee will be mapped to a table (like employees) in MySQL.

✅ @Table(name = "employees")
Specifies the actual name of the table in the database.

So even though the class is called Employee, the corresponding SQL table will be called employees.

✅ @Data
A Lombok annotation that generates:

Getters

Setters

toString()

equals() and hashCode() automatically

Saves you from writing that boilerplate manually.

✅ @NoArgsConstructor
Generates a default constructor with no arguments.

Needed by JPA for creating objects when fetching from the DB.

✅ @AllArgsConstructor
Generates a constructor that takes all fields as parameters.

Handy for quickly creating a full Employee object.

✅ public class Employee {
Starts the definition of your model class.

✅ @Id
Marks the field below it (id) as the primary key for the table.

✅ @GeneratedValue(strategy = GenerationType.IDENTITY)
Tells MySQL to auto-increment the ID.

Every new employee gets a new unique ID automatically.

✅ private Long id;
This is the primary key field (auto-generated by the DB).

Type Long because ID is a large number.

✅ @Column(name = "employee_id", nullable = false, unique = true)
Maps this field to a column in the DB called employee_id.

nullable = false → Cannot be null (required)

unique = true → No two employees can have the same employee ID.

✅ private String employeeId;
The unique ID used for login.

✅ @Column(name = "name")
Maps the name field to a column named name in the DB.

✅ private String name;
Stores the employee's full name.

✅ @Column(name = "password", nullable = false)
Maps to the password column in the DB.

nullable = false → Required, cannot be empty.

✅ private String password;
This stores the employee's password (ideally hashed, we’ll handle that soon)
 */
