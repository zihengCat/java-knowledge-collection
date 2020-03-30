/**
 * LeetCode 620. Not Boring Movies
 * https://leetcode.com/problems/not-boring-movies/
 */
SELECT id, movie, description, rating
FROM cinema
WHERE description <> 'boring'
AND id % 2 <> 0
ORDER BY rating DESC
/* EOF */