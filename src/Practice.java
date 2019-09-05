public class Practice {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        int maxNum = 0;
        for(int i:weights) {
            sum += i;
            if(i>maxNum) {
                maxNum = i;
            }
        }
        int mid = (maxNum + sum)>>1;
        while(sum>maxNum) {
            if(isAvailable(weights,mid,D)) {

            }
        }
        return mid;
    }

    private boolean isAvailable(int[] weights, int ship, int D) {
        return true;
    }

}
