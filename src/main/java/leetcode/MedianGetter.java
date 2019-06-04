package leetcode;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: liweizhi
 * @Date: 2018/12/24 20:32
 * @Description:
 */
public class MedianGetter {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] all = new int[(nums1 == null ? 0 : nums1.length) + (nums2 == null ? 0 : nums2.length)];
        int count = 0;
        if (nums1 != null) {
            for (int i : nums1
            ) {
                all[count++] = i;
            }
        }
        if (nums2 != null) {
            for (int i : nums2
            ) {
                all[count++] = i;
            }
        }
        for (int i = 0; i < all.length; i++) {
            for (int j = 0; j < all.length - 1 - i; j++) {
                if (all[j] > all[j + 1]) {
                    int temp = all[j];
                    all[j] = all[j + 1];
                    all[j + 1] = temp;
                }
            }
        }
        System.out.println(StringUtils.join(all, ','));
        if (all.length % 2 == 0) {
            return (all[all.length / 2 - 1] + all[all.length / 2] + 0D) / 2;
        } else {
            return all[all.length / 2];
        }
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {

        int[] all = new int[(nums1 == null ? 0 : nums1.length) + (nums2 == null ? 0 : nums2.length)];

        if (nums1 == null || nums1.length == 0) {
            return getMedianFromArray(nums2);
        } else if (nums2 == null || nums2.length == 0) {
            return getMedianFromArray(nums1);
        } else {

        }

        return 0D;
    }

    private static double getMedianFromArray(int[] nums) {
        if (nums.length % 2 == 0) {
            return (nums[nums.length - 1] + nums[nums.length] + 0D) / 2;
        } else {
            return nums[nums.length];
        }
    }

    public static double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {

        System.out.println(findMedianSortedArrays2(new int[]{1, 2, 2, 2, 2, 2, 2, 2}, new int[]{3, 4}));
    }

}
