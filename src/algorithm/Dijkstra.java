package algorithm;

import java.util.PriorityQueue;

public class Dijkstra {
	private int n; // 노드들의 수
	private int maps[][]; // 노드들간의 가중치 저장할 변수

	public Dijkstra(int n) {
		this.n = n;
		maps = new int[n + 1][n + 1];

	}
	
	public void input(int i, int j, int w) {
		maps[i][j] = w;
	}
	
	class Node implements Comparable<Node>{
		private int idx;
		private int weight;
		
		Node(int idx, int weight){
			this.idx = idx;
			this.weight = weight;
		}
		
		public int compareTo(Node n){
	        return weight <= n.weight ? -1 : 1;
	    }

	}
	
	public void dijkstra(int s) {
		int distance[] = new int[n + 1]; // 최단 거리를 저장할 변수

		// distance값 무한대로 초기화.
		for (int i = 1; i < n + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// 시작노드값 초기화.
		distance[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s, distance[s]));
		
		Node current;
		while(!pq.isEmpty()) {
			current = pq.poll();
			int here = current.idx; 
			int cost = current.weight;
			
			//시작 노드(A)에서 특정 노드 (C)까지 갈 때
			//이미 A에서 C를 가는 더 짧은 경로가 있다면 무시한다.
			if(cost > distance[here]) {
				continue;
			}
			
			//노드의 갯수만큼 반복
			for(int adj=0; adj<=n; adj++) {
				//가중치 계산 : 
				//A-> B->C의 경로를 거쳐서 가는 경우
				//A~B 까지 가는 거리 + B~C까지 가는 거리
				int w = distance[here]+maps[here][adj];
				
				//A에서 C로 바로 가는 경우가 B를 거쳐서 가는 것보다 멀다면 
				if(maps[here][adj]!=0&&distance[adj]>w) {
					distance[adj] = w;
					pq.add(new Node(adj, distance[adj]));
				}
			}
		}
		
		for(int i=1;i<n+1;i++){
            System.out.print(distance[i]+" ");
        }
        System.out.println("");


	}

	public static void main(String[] args) {
		Dijkstra g = new Dijkstra(6);
		g.input(1, 2, 8);
		g.input(1, 3, 1);
		g.input(1, 4, 2);
		g.input(3, 2, 5);
		g.input(3, 4, 2);
		g.input(4, 5, 3);
		g.input(4, 6, 5);
		g.input(5, 6, 1);
		g.input(6, 1, 5);
		g.dijkstra(1);
	}

}

