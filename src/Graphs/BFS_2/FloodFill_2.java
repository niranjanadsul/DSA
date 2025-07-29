package Graphs.BFS_2;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill_2 {
    //https://leetcode.com/problems/flood-fill/description/
    /*You are given an image represented by an m x n grid of integers image,
     image[i][j] represents the pixel value of the image.
     You are also given three integers sr, sc, and color.
     Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
    To perform a flood fill:
    Begin with the starting pixel and change its color to color.
    Perform the same process for each pixel that is directly adjacent
    (pixels that share a side with the original pixel, either horizontally or vertically) and
    shares the same color as the starting pixel.
    Keep repeating this process by checking neighboring pixels of the updated pixels and
    modifying their color if it matches the original color of the starting pixel.
    The process stops when there are no more adjacent pixels of the original color to update.
    Return the modified image after performing the flood fill.
    Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
    Output: [[2,2,2],[2,2,0],[2,0,1]]
    */

    //Same as BFS and rotten oranges problem
    //TC = O(n*m)
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startPixelColor = image[sr][sc];
        if(startPixelColor==color)
            return image;
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{sr,sc});
        int[][] directions=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        while (!queue.isEmpty()){
            int[] pixel=queue.remove();
            image[pixel[0]][pixel[1]]=color;
            for(int[] arr:directions){
                int row=pixel[0]+arr[0];
                int col=pixel[1]+arr[1];
                if(row>=0 && row<image.length && col>=0 && col<image[0].length &&
                        image[row][col]==startPixelColor){
                    queue.add(new int[]{row,col});
                }
            }
        }
        return image;
    }
}
