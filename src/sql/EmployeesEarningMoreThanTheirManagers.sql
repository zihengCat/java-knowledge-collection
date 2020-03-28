/**
 * LeetCode 181. Employees Earning More Than Their Managers
 * https://leetcode.com/problems/employees-earning-more-than-their-managers/
 */
SELECT t1.Name AS Employee
FROM Employee AS t1, Employee AS t2
WHERE t1.ManagerId = t2.Id
AND t1.Salary > t2.Salary
/* EOF */