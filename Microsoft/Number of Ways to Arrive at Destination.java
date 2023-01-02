class Solution {
    public int countPaths(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] road:roads){
            int u = road[0], v = road[1], t = road[2];
            graph.get(u).add(new int[]{v,t});
            graph.get(v).add(new int[]{u,t});
        }
        return dijkstra(graph, n);
    }
    private int dijkstra(List<List<int[]>> graph, int n){
        Queue<int[]> pq = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        int[] times = new int[n];
        long[] ways = new long[n];
        int mod = 1000000007;
        Arrays.fill(times, Integer.MAX_VALUE);
        times[0] = 0;
        ways[0] = 1;
        pq.add(new int[]{0,0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            if(curr[0]==n-1) return (int)ways[curr[0]];
            for(int[] next:graph.get(curr[0])){
                int newTime = curr[1]+next[1];
                if(newTime < times[next[0]]){
                    times[next[0]] = newTime;
                    ways[next[0]] = ways[curr[0]];
                    pq.add(new int[]{next[0], newTime});
                }
                else if(newTime == times[next[0]]){
                    ways[next[0]] = (ways[next[0]]+ways[curr[0]])%mod;
                }
            }
        }
        return 0;
    }
}