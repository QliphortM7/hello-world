package qliphoth.sortPractice;

/**
 * 快速排序：先分再排，自上而下
 */
public class quickSort {

    public void quickSort(int[] data) {

    }
    /**
     *  每次调用partition，都会有一个数被放到正确排序的位置，并且右侧数字大于左侧数字
     * @param data
     * @param len
     * @param begin
     * @param end
     * @return
     */
    public int partition(int[] data, int len, int begin, int end) {
        swap(data, begin, end);
        int small = begin - 1;
        for(int i=begin;i<end;i++) {
            if(data[i]<data[end]) {
                small++;
                if(small!=i) {
                    swap(data, small, i);
                }
            }
        }
        small++;
        swap(data, small, end);
        return small;
    }

    private void swap(int[] data, int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
}
