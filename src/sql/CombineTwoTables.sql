/**
 * LeetCode 175. Combine Two Tables
 * https://leetcode.com/problems/combine-two-tables/
 */

SELECT t1.FirstName, t1.LastName, t2.City, t2.State
FROM Person AS t1
LEFT OUTER JOIN Address AS t2
ON t1.PersonId = t2.PersonId

/* EOF */