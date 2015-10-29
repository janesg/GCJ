-- CREATE TABLE FOR DEPARTMENT
CREATE TABLE DEPARTMENT 
(
"ID" NUMBER, 
"NAME" VARCHAR2(30), 
 PRIMARY KEY ("ID")  
); 

-- CREATE TABLE FOR EMP
CREATE TABLE EMP
(
"ID" NUMBER, 
"MGR_ID" NUMBER, 
"DEPT_ID" NUMBER, 
"NAME" VARCHAR2(30), 
"SAL" NUMBER, 
"DOJ" DATE, 
 PRIMARY KEY ("ID") ENABLE, 
 FOREIGN KEY ("MGR_ID") REFERENCES EMP ("ID") ENABLE, 
 FOREIGN KEY ("DEPT_ID") REFERENCES DEPARTMENT ("ID") ENABLE
);

-- INSERT STATEMENT FOR DEPARTMENT
INSERT INTO DEPARTMENT values (1,'HR');
INSERT INTO DEPARTMENT values (2,'Engineering');
INSERT INTO DEPARTMENT values (3,'Marketing');
INSERT INTO DEPARTMENT values (4,'Sales');
INSERT INTO DEPARTMENT values (5,'Logistics');

-- INSERT STATEMENT FOR EMP
INSERT INTO EMP values (1, NULL, 2,'Hash', 100, to_date('2012-01-01', 'YYYY-MM-DD'));
INSERT INTO EMP values (2, 1, 2, 'Robo', 100, to_date('2012-01-01', 'YYYY-MM-DD'));
INSERT INTO EMP values (3, 2, 1, 'Privy', 50, to_date('2012-05-01', 'YYYY-MM-DD'));
INSERT INTO EMP values (4, 1, 1, 'Inno', 50, to_date('2012-05-01', 'YYYY-MM-DD'));
INSERT INTO EMP values (5, 2, 2, 'Anno', 80, to_date('2012-02-01', 'YYYY-MM-DD'));
INSERT INTO EMP values (6, 1, 2, 'Darl', 80, to_date('2012-02-11', 'YYYY-MM-DD'));
INSERT INTO EMP values (7, 1, 3, 'Pete', 70, to_date('2012-04-16', 'YYYY-MM-DD'));
INSERT INTO EMP values (8, 7, 3, 'Meme', 60, to_date('2012-07-26', 'YYYY-MM-DD'));
INSERT INTO EMP values (9, 2, 4, 'Tomiti', 70, to_date('2012-07-07', 'YYYY-MM-DD'));
INSERT INTO EMP values (10, 9, 4, 'Bhuti', 60, to_date('2012-08-24', 'YYYY-MM-DD'));

-- INNER JOIN using where clause
-- without the quotes around each alias:
--	full stop in Dept. would cause error
--	Employee would be in UPPER CASE
SELECT d.name "Dept.", e.name "Employee" 
FROM DEPARTMENT d, EMP e
WHERE e.dept_id = d.id 

-- INNER JOIN using JOIN clause
-- can drop use of 'INNER' keyword since it is default
SELECT d.name "Dept.", e.name "Employee"
FROM DEPARTMENT d
INNER JOIN EMP e
	ON e.dept_id = d.id 

-- LEFT OUTER JOIN using Oracle specific syntax
SELECT d.name "Dept.", e.name "Employee" 
FROM DEPARTMENT d, EMP e
WHERE d.id = e.dept_id (+)

-- LEFT OUTER JOIN using ANSI SQL syntax
-- can drop use of 'OUTER' keyword since it is implied by LEFT
SELECT d.name "Dept.", e.name "Employee" 
FROM DEPARTMENT d
LEFT OUTER JOIN EMP e
	ON d.id = e.dept_id

-- UNION ALL returns all the results from both selects regardless if there are duplicates
-- returns 2 identical results for the employee with id = 5
SELECT * FROM EMP WHERE ID = 5
UNION ALL
SELECT * FROM EMP WHERE ID = 5

-- UNION returns results from both selects filtering out duplicates
-- returns a single row for the employee with id = 5
SELECT * FROM EMP WHERE ID = 5
UNION
SELECT * FROM EMP WHERE ID = 5

-- MINUS clause removes 2nd select results from 1st selects results
-- returns results for employees with ids = 1 and 2
SELECT * FROM EMP
MINUS
SELECT * FROM EMP WHERE ID > 2

-- INTERSECT clause returns only results that exist in both select result sets
-- returns results for employees with ids = 2 and 5
SELECT * FROM EMP WHERE ID IN (2, 3, 5)
INTERSECT
SELECT * FROM EMP WHERE ID IN (1, 2, 4, 5)

-- GROUP BY clause to use aggregate function
-- Logistics department doesn't appear (with NULL average salary) unless we use LEFT OUTER JOIN
SELECT d.name "Dept.", avg(e.sal) "Av. Salary" 
FROM DEPARTMENT d
LEFT OUTER JOIN EMP e
   ON d.id = e.dept_id
GROUP BY d.name

-- HAVING clause filters the GROUP BY results
-- NULL average salary for Logistics department gets filtered out
SELECT d.name "Dept.", avg(e.sal) "Av. Salary" 
FROM DEPARTMENT d
LEFT OUTER JOIN EMP e
   ON d.id = e.dept_id
GROUP BY d.name
HAVING avg(e.sal) > 80

-- SELF JOIN 
-- use LEFT OUTER JOIN to include row for the top-level manager that has no manager themselves (i.e. manager is NULL)
SELECT e1.name "Employee", e2.name "Manager"
FROM EMP e1
LEFT OUTER JOIN EMP e2
	ON e1.mgr_id = e2.id

-- Generating a row number without using rownum
-- column used in sub-select has to be unique
-- use of >= means thats that the column will be sorted in descending order
--        <= means thats that the column will be sorted in ascending order
SELECT name, sal, (SELECT COUNT(*) FROM EMP e2 WHERE e1.name >= e2.name) "Row Num"
FROM EMP e1
order by "Row Num"

SELECT name, sal, (SELECT COUNT(*) FROM EMP e2 WHERE e1.name <= e2.name) "Row Num"
FROM EMP e1
order by "Row Num" DESC

-- could also use id since it is unique (being primary key it would be)
SELECT name, sal, (SELECT COUNT(*) FROM EMP e2 WHERE e1.id >= e2.id) "Row Num"
FROM EMP e1
order by "Row Num"

-- Get the top 3 results using Oracle specific syntax
SELECT *
FROM EMP
WHERE ROWNUM <= 3;

-- Get the top 3 results using Oracle specific syntax
-- RowNum allocated to results before returned so any query can be used as subquery
SELECT *
FROM (SELECT * FROM EMP where sal < 80 order by sal desc)
WHERE ROWNUM <= 3;

-- Get the top 3 results using generic self join correlated subquery
SELECT *
FROM EMP e1
WHERE (SELECT count(*) FROM EMP e2 WHERE e1.id > e2.id) < 3

-- 
SELECT name, sal, ROW_NUMBER() OVER(order by sal desc) "rownum_by_sal"
FROM EMP

SELECT name, sal, RANK() OVER(order by sal desc) "rank_by_sal"
FROM EMP

SELECT name, sal, DENSE_RANK() OVER(order by sal desc) "dense_rank_by_sal"
FROM EMP
