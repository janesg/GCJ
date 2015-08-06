CREATE OR REPLACE
PACKAGE BODY pkg_employee AS
 
  FUNCTION get_name (emp_id IN employee.id%TYPE) 
                     RETURN employee.name%TYPE 
  IS
    emp_name employee.name%TYPE;
  BEGIN
    SELECT name
    INTO emp_name
    FROM employee
    WHERE id = emp_id;
    
    RETURN emp_name;
  END get_name;

  FUNCTION get_name2 (emp_id IN employee.id%TYPE, 
                      emp_name OUT employee.name%TYPE)
                      RETURN VARCHAR2
  IS
  BEGIN
    SELECT name
    INTO emp_name
    FROM employee
    WHERE id = emp_id;
    
    RETURN 'SUCCESS';
  END get_name2;

END pkg_employee;
/

show errors