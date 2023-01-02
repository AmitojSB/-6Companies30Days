class Solution {
    public int numberOfSubstrings(String s) {
        CharCounter counter = new CharCounter();
        // 2 pointers for sliding window
        int start = 0;
        int end = 0;
        int res = 0;
        int n = s.length();
        while (start < n) {
            if (counter.hasAll()) {
                // all characters are there, update res and slide
                // start to the right.
                res += n - end + 1;
                counter.dec(s.charAt(start));
                start++;
            } else if (end == n) {
                // arrived at the end and not all characters are 
                // present, we are done
                return res;
            } else {
                // all characters are not present, slide end to the
                // right and add a new character
                counter.inc(s.charAt(end));
                end++;
            }
        }
        return res;
    }
    
    public final class CharCounter {
        private final int[] counts;
        
        public CharCounter() {
            counts = new int[3];
        }
        
        public boolean hasAll() {
            return hasA() && hasB() && hasC();
        }
        
        public void inc(char x) {
            switch(x) {
                case 'a':
                    incA();
                    return;
                case 'b':
                    incB();
                    return;
                case 'c':
                    incC();
                    return;
                default:
                    return;
            }
        }
        public void dec(char x) {
            switch(x) {
                case 'a':
                    decA();
                    return;
                case 'b':
                    decB();
                    return;
                case 'c':
                    decC();
                    return;
                default:
                    return;
            }
        }
        
        private boolean hasA() {
            return has(0);
        }
        private boolean hasB() {
            return has(1);
        }
        
        private boolean hasC() {
            return has(2);
        }
        private void incA() {
            inc(0);
        }
        private void incB() {
            inc(1);
        }
        
        private void incC() {
            inc(2);
        }
        private void decA() {
            dec(0);
        }
        private void decB() {
            dec(1);
        }
        
        private void decC() {
            dec(2);
        }
        
        private boolean has(int i) {
            return counts[i] > 0;
        }
        
        private void inc(int i) {
            counts[i]++;
        }
        
        private void dec(int i) {
            counts[i]--;
        }
    }
}
      