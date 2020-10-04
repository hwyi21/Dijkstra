package algorithm;

import java.util.PriorityQueue;

public class Dijkstra {
	private int n; // ������ ��
	private int maps[][]; // ���鰣�� ����ġ ������ ����

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
		int distance[] = new int[n + 1]; // �ִ� �Ÿ��� ������ ����

		// distance�� ���Ѵ�� �ʱ�ȭ.
		for (int i = 1; i < n + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// ���۳�尪 �ʱ�ȭ.
		distance[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s, distance[s]));
		
		Node current;
		while(!pq.isEmpty()) {
			current = pq.poll();
			int here = current.idx; 
			int cost = current.weight;
			
			//���� ���(A)���� Ư�� ��� (C)���� �� ��
			//�̹� A���� C�� ���� �� ª�� ��ΰ� �ִٸ� �����Ѵ�.
			if(cost > distance[here]) {
				continue;
			}
			
			//����� ������ŭ �ݺ�
			for(int adj=0; adj<=n; adj++) {
				//����ġ ��� : 
				//A-> B->C�� ��θ� ���ļ� ���� ���
				//A~B ���� ���� �Ÿ� + B~C���� ���� �Ÿ�
				int w = distance[here]+maps[here][adj];
				
				//A���� C�� �ٷ� ���� ��찡 B�� ���ļ� ���� �ͺ��� �ִٸ� 
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

