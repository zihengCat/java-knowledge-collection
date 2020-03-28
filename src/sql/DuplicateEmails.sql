/**
 * LeetCode 182. Duplicate Emails
 * https://leetcode.com/problems/duplicate-emails/
 */
SELECT t.Email
FROM (
    SELECT Email, COUNT(Email) AS cnt
    FROM Person
    GROUP BY Email
) AS t
WHERE t.cnt > 1
/* EOF */