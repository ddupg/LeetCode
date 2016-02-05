class Solution {

    List<List<String>> ans;
    List<List<Integer>> g;
    int n, begin, end, max;
    String beginWord, endWord;
    List<String> words;
    boolean vis[];
    List<Integer> path;
    int[] dis, dis1, dis2;

    public Solution() {
        ans = new LinkedList<>();
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(beginWord);
        wordList.add(endWord);
        n = wordList.size();
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        this.beginWord = beginWord;
        this.endWord = endWord;

        build(wordList);

        dis = new int[n];
        dis1 = new int[n];
        dis2 = new int[n];
        bfs(begin, dis1);
        bfs(end, dis2);
        max = dis1[end];
        
        dis = dis1;
        path = new LinkedList<>();
        path.add(begin);
        vis = new boolean[n];
        vis[begin] = true;
        dfs(begin, 1);
        return ans;
    }

    private void bfs(int r, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(distance, n + 1);
        distance[r] = 0;
        queue.add(r);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < g.get(u).size(); i++) {
                int v = g.get(u).get(i);
                if (distance[v] != n + 1) continue;
                distance[v] = distance[u] + 1;
                queue.add(v);
            }
        }
    }

    private void dfs(int u, int x) {

        if (vis[end]) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < path.size(); i++) {
                list.add(words.get(path.get(i)));
            }
            ans.add(list);
            return;
        }
        if (x > max) return;

        for (int i = 0; i < g.get(u).size(); i++) {
            int v = g.get(u).get(i);

            if (vis[v] || dis[v] != x) continue;
            vis[v] = true;
            path.add(v);
            dfs(v, x + 1);
            path.remove(new Integer(v));
            vis[v] = false;
        }
    }

    private void build(Set<String> wordList) {
        words = new ArrayList<>();
        wordList.parallelStream().forEach(
                x -> words.add(x)
        );
        for (int i = 0; i < n; i++) {
            String a = words.get(i);
            if (beginWord.equals(a)) {
                begin = i;
            }
            if (endWord.equals(a)) {
                end = i;
            }
            for (int j = i + 1; j < n; j++) {
                String b = words.get(j);
                int cnt = 0;
                for (int k = 0; k < a.length(); k++) {
                    if (a.charAt(k) != b.charAt(k))
                        ++cnt;
                }
                if (cnt == 1) {
                    g.get(i).add(j);
                    g.get(j).add(i);
                }
            }
        }
    }
}
