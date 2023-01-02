public class Solution {
   public String getHint(String secret, String guess) {
        if (secret == null || guess == null) return "";
        if (secret.length() != guess.length()) return "";

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        int bull = 0;
        int cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bull++;
            } else {
                map1.merge(secret.charAt(i), 1, (a, b) -> a + b);
                map2.merge(guess.charAt(i), 1, (a, b) -> a + b);
            }
        }

        for (Character key : map1.keySet()) {
            if (map2.containsKey(key)) {
                cow += Math.min(map1.get(key), map2.get(key));
            }
        }

        return bull + "A" + cow + "B";
    }
}