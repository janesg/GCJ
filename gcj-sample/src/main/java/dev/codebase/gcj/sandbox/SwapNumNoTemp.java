package dev.codebase.gcj.sandbox;

public class SwapNumNoTemp {

    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            
            IntSwapper iSwap = new IntSwapper(getRandomInt(), getRandomInt());
        
            System.out.println("Before Swap[" + i + "] - a: " + iSwap.getA() + " b: " + iSwap.getB());

            iSwap.swapNums();
        
            System.out.println("After Swap[" + i + "]  - a: " + iSwap.getA() + " b: " + iSwap.getB());
        }
        
    }
    
    private static int getRandomInt() {
        
        // We want the whole range of integers from -2^31 to 2^31 - 1
        double maxValue = (Integer.MAX_VALUE * 1.0) - (Integer.MIN_VALUE * 1.0);

        // Generate our random against the whole range shifted up to start at zero
        double randomValue = Math.random() * maxValue;
        
        // Now take off half the original range to land up back within Integer territory
        randomValue = randomValue - (Integer.MAX_VALUE * 1.0);
        
        return (int) Math.ceil(randomValue);
    }
    
    static class IntSwapper {
        private int a;
        private int b;
        
        public IntSwapper(int a, int b) {
            super();
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }
        
        public int getB() {
            return b;
        }
        
        public void swapNums() {
            a = a^b;
            b = a^b;
            a = a^b;            
        }
    }
}
