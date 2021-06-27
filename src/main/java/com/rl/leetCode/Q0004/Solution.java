package com.rl.leetCode.Q0004;

import org.junit.jupiter.api.Test;

/*

给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2

示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5

示例 3：
输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000

示例 4：
输入：nums1 = [], nums2 = [1]
输出：1.00000

示例 5：
输入：nums1 = [2], nums2 = []
输出：2.00000

提示：
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int index1 = 0,
            index2 = 0,
            len1 = nums1.length,
            len2 = nums2.length,
            curNum = 0;
        int flag = (len1 + len2)%2;
        double d = 0;

        while(index1 < len1 || index2 < len2){
            int curValue = 0;

            //第一个数组已经走完
            if(index1 == len1){
                curValue = nums2[index2];
                index2++;
            }
            else if(index2 == len2){
                curValue = nums1[index1];
                index1++;
            }
            else if(nums1[index1] < nums2[index2]){
                curValue = nums1[index1];
                index1++;
            }
            else if(nums1[index1] >= nums2[index2]){
                curValue = nums2[index2];
                index2++;
            }

            //偶数
            if(flag == 0){
                if(curNum == (len1 + len2)/2 - 1){
                    d += curValue;
                }
                else if(curNum == (len1 + len2)/2){
                    d += curValue;
                    d =  d/2;
                    break;
                }
            }
            else{
                if(curNum == (len1 + len2 -1)/2){
                    d = curValue;
                    break;
                }
            }

            curNum++;
        }

        return d;
    }

   @Test
    public void TestSort(){
        int[] nums1 = {1,3};

        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
}
