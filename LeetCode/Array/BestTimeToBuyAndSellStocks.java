class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        boolean raising = false;
        int buyAt = -1;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] >= prices[i-1]) {
                if(!raising) {
                    buyAt = i-1;
                    raising = true;
                }
            } else {
                if(raising) {
                    profit += prices[i-1] - prices[buyAt];
                    raising = false;
                }
            }
        }
        if(raising) {
            profit += prices[prices.length - 1] - prices[buyAt];
        }
        return profit;
    }
}