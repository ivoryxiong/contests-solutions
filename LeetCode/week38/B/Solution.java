//
// Solution
// Created by ivoryxiong on 2017/6/25.

import java.util.*;

public class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
                    @Override
                    public int compare(final int[] course1, final int[] course2) {
                        return course1[1] - course2[1];
                    }
                });
                            
        PriorityQueue<Integer> selectedCourses = new PriorityQueue<Integer>();
        int time = courses[0][0];
        selectedCourses.offer(-courses[0][0]);
        for (int i = 1; i < courses.length; i ++) {
            int[] course = courses[i];
            System.out.println("process : " + course[0]  + '\t' + course[1] + "\t time:" + time);
            int big = -selectedCourses.poll();
            if (time + course[0] <= course[1]) {
                System.out.println("add " + course[0]);
                selectedCourses.offer(-course[0]);
                time += course[0];
            } else if (big > course[0] && (time - big + course[0] <= course[1])){
            System.out.println("remove " + big + "\tadd " + course[0]);
                time = time - big + course[0];
                big = course[0];
            }
            selectedCourses.offer(-big);
        }
        System.out.println(selectedCourses);
        return selectedCourses.size();
    }
    
    public static void main(String [] args) {
        Solution sol = new Solution();
//        int [][] nums = new int[][] {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
//        int [][] nums = new int[][] {{7,16},{2,3},{3,12},{3,14},{10,19},{10,16},{6,8},{6,11},{3,13},{6,16}};
//        int [][] nums = new int[][] {{7,17},{3,12},{10,20},{9,10},{5,20},{10,19},{4,18}};
        int [][] nums = new int[][] {{5,5},{4,6},{2,6}};
        int ans = sol.scheduleCourse(nums);
        System.out.println(ans);
    }
}