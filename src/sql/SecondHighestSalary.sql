/**
 * LeetCode 176. Second Highest Salary
 * https://leetcode.com/problems/second-highest-salary/
 */

SELECT t.Salary AS SecondHighestSalary
FROM (
    SELECT Salary, COUNT(Salary) AS cnt
    FROM Employee
    GROUP BY Salary
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1
) AS t

SELECT IFNULL(
    (SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT 1 OFFSET 1),
    NULL
) AS SecondHighestSalary

/* EOF */