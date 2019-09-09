package qliphoth.sortPractice;

/**
 * 归并排序：分治法，先分再排，自下而上
 */
public class margeSort {

    public void sort(int[] array, int[] tmp, int begin, int end) {
        if(begin<end) {
            int mid = (begin + end)>>1;
            sort(array, tmp, begin, mid);
            sort(array, tmp, mid+1, end);
            merge(array, tmp, begin, mid, end);
        }
    }

    private void merge(int[] array, int[] tmp, int begin, int mid, int end) {
        int i = begin;
        int j = mid + 1;
        int k = begin;
        while(i<=mid&&j<=end) {
            tmp[k++] = array[i]<array[j]?array[i++]:array[j++];
        }
        while(i<=mid) {
            tmp[k++] = array[i++];
        }
        while(j<=mid) {
            tmp[k++] = array[j++];
        }
        while(k>=begin) {  // 复制回原数组
            array[k--] = tmp[k--];
        }
    }

}
