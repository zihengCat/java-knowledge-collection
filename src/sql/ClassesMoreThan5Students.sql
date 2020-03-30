/**
 * LeetCode 596. Classes More Than 5 Students
 * https://leetcode.com/problems/classes-more-than-5-students/
 */
SELECT class
FROM courses
GROUP BY class
HAVING COUNT(DISTINCT student) >= 5
/* EOF */