CREATE OR REPLACE
PACKAGE pkg_employee AS

  FUNCTION get_name  (emp_id IN employee.id%TYPE) 
                      RETURN employee.name%TYPE;
                      
  FUNCTION get_name2 (emp_id IN employee.id%TYPE,
                      emp_name OUT employee.name%TYPE)
                      RETURN VARCHAR2;
                      
END pkg_employee;
/

show errors