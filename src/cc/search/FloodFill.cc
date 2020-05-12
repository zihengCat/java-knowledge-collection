#include <iostream>
#include <vector>
#include <array>

using namespace std;

class Solution {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int newColor) {
        array<array<int, 2>, 4> directions = {
            array<int, 2>{1, 0, },
            array<int, 2>{-1, 0, },
            array<int, 2>{0, 1, },
            array<int, 2>{0, -1, },
        };
        vector<vector<bool>> visited = vector<vector<bool>>(image.size());
        for (int i = 0; i < visited.size(); i++) {
            visited[i] = vector<bool>(image[i].size(), false);
        }
        floodFillDFS(image, sr, sc, image[sr][sc], newColor, visited, directions);
        return image;
    }
private:
    void floodFillDFS(
        vector<vector<int>>& image,
        int currentRow,
        int currentCol,
        int& originalColor,
        int& newColor,
        vector<vector<bool>>& visited,
        array<array<int, 2>, 4>& directions) {
        if (!isValid(image, currentRow, currentCol)
            || visited[currentRow][currentCol]
            || image[currentRow][currentCol] != originalColor) {
            return;
        }
        visited[currentRow][currentCol] = true;
        image[currentRow][currentCol] = newColor;
        for (int i = 0; i < directions.size(); i++) {
            floodFillDFS(
                image,
                currentRow + directions[i][0],
                currentCol + directions[i][1],
                originalColor,
                newColor,
                visited,
                directions
            );
        }
    }
    bool isValid(vector<vector<int>>& vec, int rowNum, int colNum) {
        return rowNum >= 0 && rowNum < vec.size()
            && colNum >= 0 && colNum < vec[rowNum].size();
    }
};

int main(int argc, char const *argv[]) {
    Solution obj = Solution();
    vector<vector<int>> image = {
        {1, 1, 1, },
        {1, 1, 0, },
        {1, 0, 1, },
    };
    obj.floodFill(image, 1, 1, 2);
    cout << "OK" << endl;
    return 0;
}
/* EOF */