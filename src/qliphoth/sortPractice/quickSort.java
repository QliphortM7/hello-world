package qliphoth.sortPractice;

public class quickSort {
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
