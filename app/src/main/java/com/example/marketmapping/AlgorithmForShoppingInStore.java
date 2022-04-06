package com.example.marketmapping;

import android.view.View;
import android.widget.Toast;

import java.util.*;

/*

class Graph
{
    int V;	// Number of nodes.
    Vector<Integer>[] adj;	// Number of vertices

    int level;

    @SuppressWarnings("unchecked")
    Graph(int V)	// Constructor
    {
        this.V = V;
        this.adj = new Vector[2 * V];

        for(int i = 0; i < 2 * V; i++)
            this.adj[i] = new Vector<>();
    } // End Graph Constructor

    public void addEdge(int v, int w, int weight)
    {
        if(weight == 2)
        {
            adj[v].add(v + this.V);
            adj[v + this.V].add(w);
        }else
            adj[v].add(w);
    } // End addEdge

    public int printShortestPath(int[] parent, int s, int d)
    {
        level = 0;

        if (parent[s] == -1)
        {
            System.out.printf("Shortest Path between " + "%d and %d is %s ",s,d,s);
            return level;
        }

        printShortestPath(parent, parent[s], d);

        level++;
        if(s < this.V)
            System.out.printf("%d ", s);

        return level;

    } // End printShortestPath

    public int findShortestPath(int src, int dest)
    {
        boolean[] visited = new boolean[2 * this.V];
        int[] parent = new int[2 * this.V];

        for (int i = 0; i < 2 * this.V; i++)
        {
            visited[i] = false;
            parent[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();

        visited[src] = true;
        queue.add(src);

        while(!queue.isEmpty())
        {
            int s = queue.peek();

            if(s == dest)
                return printShortestPath(parent, s, dest);
            queue.poll();

            for(int i : this.adj[s])
            {
                if(!visited[i])
                {
                    visited[i] = true;
                    queue.add(i);
                    parent[i] = s;
                }
            }
        }
        return 0;
    } // End findShortestPath
} // End Graph Class

public class AlgorithmForShoppingInStore {

        public void order(Graph g, int[] nodes)
        {
            Arrays.sort(nodes);

            //return nodes;
        }



        public void RunFuction()
        {
            int V = 24;

            Graph g = new Graph(V);

            g.addEdge(1, 2, 1);
            g.addEdge(1, 8, 0);
            g.addEdge(2, 1, 1);
            g.addEdge(2, 3, 1);
            g.addEdge(2, 7, 0);
            g.addEdge(3, 2, 1);
            g.addEdge(3, 4, 1);
            g.addEdge(3, 6, 0);
            g.addEdge(4, 3, 1);
            g.addEdge(4, 5, 0);
            g.addEdge(5, 4, 0);
            g.addEdge(5, 6, 1);
            g.addEdge(5, 12, 2);
            g.addEdge(6, 3, 0);
            g.addEdge(6, 5, 1);
            g.addEdge(6, 7, 1);
            g.addEdge(7, 2, 0);
            g.addEdge(7, 6, 1);
            g.addEdge(7, 8, 1);
            g.addEdge(8, 1, 0);
            g.addEdge(8, 7, 1);
            g.addEdge(8, 9, 2);
            g.addEdge(9, 8, 2);
            g.addEdge(9, 10, 1);
            g.addEdge(9, 16, 0);
            g.addEdge(10, 9, 1);
            g.addEdge(10, 11, 1);
            g.addEdge(10, 15, 0);
            g.addEdge(11, 10, 1);
            g.addEdge(11, 12, 1);
            g.addEdge(11, 14, 0);
            g.addEdge(12, 5, 0);
            g.addEdge(12, 11, 1);
            g.addEdge(12, 13, 2);
            g.addEdge(13, 12, 0);
            g.addEdge(13, 14, 1);
            g.addEdge(13, 20, 2);
            g.addEdge(14, 11, 0);
            g.addEdge(14, 13, 1);
            g.addEdge(14, 15, 1);
            g.addEdge(15, 10, 0);
            g.addEdge(15, 14, 1);
            g.addEdge(15, 16, 1);
            g.addEdge(16, 9, 0);
            g.addEdge(16, 15, 1);
            g.addEdge(16, 17, 2);
            g.addEdge(17, 16, 2);
            g.addEdge(17, 18, 1);
            g.addEdge(17, 24, 0);
            g.addEdge(18, 17, 1);
            g.addEdge(18, 19, 1);
            g.addEdge(18, 23, 0);
            g.addEdge(19, 18, 1);
            g.addEdge(19, 20, 1);
            g.addEdge(19, 22, 0);
            g.addEdge(20, 13, 2);
            g.addEdge(20, 19, 1);
            g.addEdge(20, 21, 0);
            g.addEdge(21, 20, 0);
            g.addEdge(21, 22, 1);
            g.addEdge(22, 19, 0);
            g.addEdge(22, 21, 1);
            g.addEdge(22, 23, 1);
            g.addEdge(23, 18, 0);
            g.addEdge(23, 22, 1);
            g.addEdge(23, 24, 1);
            g.addEdge(24, 23, 1);
            g.addEdge(24, 17, 0);


            int[] nodes = {1,4,7,15,24,20};

            order(g, nodes);

            for (int node: nodes)
            {
                System.out.println(node);


            }
        } // End Main
    // end GFG
}

*/


