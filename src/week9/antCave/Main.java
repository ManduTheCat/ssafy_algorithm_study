package week9.antCave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		String word;
		PriorityQueue<Node> child = new PriorityQueue<>();
		boolean isLeaf;
		
		public Node() {	}
		
		public Node(String word) {
			this.word = word;
			this.isLeaf = true;
		}

		public PriorityQueue<Node> getChild() {
			return child;
		}
		
		public Node findChild(String str) {
			for(Node n : child) {
				if(n.word.compareTo(str) == 0) {
					return n;
				}
			}
			return null;
		}

		public void addChild(Node c) {
			this.child.add(c);
			this.isLeaf = false;
		}

		@Override
		public int compareTo(Node o) {
			return (this.word).compareTo(o.word);
		}
	}

	static class Trie {
		Node root;

		Trie() {
			root = new Node();
		}
		
		void insert(String words[]) {
			Node node = this.root;
			for(int i = 0; i < words.length; i++) {
				if(node.isLeaf) {
					Node newNode = new Node(words[i]);
					node.addChild(newNode);
					node = newNode;
				} else {
					Node newNode = node.findChild(words[i]);
					if(newNode != null) {
						node = newNode;
					} else {
						newNode = new Node(words[i]);
						node.addChild(newNode);
						node = newNode;
					}
				}
			}
		}
		
		void print(int cnt, PriorityQueue<Node> child) {	// preorder traversal (cnt = depth)
			while(!child.isEmpty()) {
				Node n = child.poll();
				for(int i = 0; i < cnt * 2; i++) {
					System.out.print('-');
				}
				System.out.println(n.word);
				if(!n.isLeaf) {
					this.print(cnt + 1, n.getChild());
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 정보의 개수

		Trie trie = new Trie();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String words[] = new String[K];
			for(int j = 0; j < K; j++) {
				words[j] = st.nextToken();
			}
			trie.insert(words);
		}

		trie.print(0, trie.root.getChild());
	}
}