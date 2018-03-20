/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs328lab;
import java.util.*;
/**
 *
 * @author marktan
 */
class Node implements Comparable {
    private int key;
    private ArrayList<Node> adj;
    private Node parentBFS,parentDFS;
    private int start,end,length;
    
    public Node(int k) {
        this.key = k;           this.adj = new ArrayList<Node>();
        parentBFS = null;       parentDFS = null;
        start = 0;  end = 0;    length = 0;
    }
        
    public void setKey(int k) {key = k;}
    
    public int getKey() {return key;}
    
    public void setAdj(ArrayList<Node> n) {adj = n;}
    
    public ArrayList<Node> getAdj() {return adj;}
    
    public void printAdj() {
        for(int i = 0; i < adj.size(); i++)
             System.out.print(adj.get(i) + " ");
    }
    
    public void setParentBFS(Node p) {parentBFS = p;}
    
    public Node getParentBFS() {return parentBFS;}
    
    public void setParentDFS(Node p) {parentDFS = p;}
    
    public Node getParentDFS() {return parentDFS;}
    
    public void setStart(int s) {start = s;}
    
    public int getStart() {return start;}
    
    public void setEnd(int e) {end = e;}
    
    public int getEnd() {return end;}
    
    public void setLength(int l) {length = l;}
    
    public int getLength() {return length;}
    
    public void addAdj(Node n) {this.adj.add(n);}
            
    public String toString() {return getKey() + " ";}
    
    public int compareTo(Object node) {
        int comp = ((Node)node).getKey();
        return this.key - comp;
    }
}

public class CECS328Lab5 {
    
    public static void BFS(Node u, Queue<Node> q) {
        q.add(u);
        Node ref = new Node(-1);
        u.setParentBFS(ref);
        while(!q.isEmpty()) {
            u = q.remove();
            System.out.println(u + " Length: " + u.getLength());
            for(Node v: u.getAdj()) {
                if(v.getParentBFS() == null) {
                    q.add(v);
                    v.setParentBFS(u);
                    v.setLength(u.getLength() + 1);
                }
            }
        }
    }
    
    public static void DFS(ArrayList<Node> n,LinkedList<Node> order, int timer, boolean hasC) {
        for(Node no: n) {
            if(no.getParentDFS() == null) {
                Node ref = new Node(-1);
                no.setParentDFS(ref);
                DFS_visit(no,no.getAdj(),order,timer,hasC);
            }
        }
    }
    
    public static void DFS_visit(Node u, ArrayList<Node> adj, LinkedList<Node> order, int timer, boolean hasC) {
        timer++;
        u.setStart(timer);
        for(Node v: adj) {
            if (v.getStart() != 0 && v.getEnd() == 0) {
                hasC = true;
            }
            else if(v.getStart() == 0) {
                if(hasC == false)
                    order.add(v);
                v.setParentDFS(u);
                DFS_visit(v,v.getAdj(),order,timer,hasC);
            }
        }
        timer++;
        u.setEnd(timer);
    }
    
    public static void DFS_visit(Node u, ArrayList<Node> adj, int timer) {
        timer++;
        u.setStart(timer);
        for(Node v: adj) {
            if (v.getStart() != 0 && v.getEnd() == 0) {
                //return;
            }
            else if(v.getStart() == 0) {
                v.setParentDFS(u);
                DFS_visit(v,v.getAdj(),timer);
            }
        }
        timer++;
        u.setEnd(timer);
        System.out.println("Node: " + u + " Start: " + u.getStart() + " End: " + u.getEnd());
    }
    
    public static void main(String[] args)
    {
        int order = 0, size = 0;
        Scanner in = new Scanner(System.in);
        
        System.out.print("What is the order of the graph? ");
        if(in.hasNextInt())
            order = in.nextInt();
        System.out.print("What is the size of the graph? ");
        if(in.hasNextInt())
            size = in.nextInt();
                
        int nodeCount = 0;
        ArrayList<Node> graph = new ArrayList<Node>();
        Node no;
        while(nodeCount < order) {
            no = new Node(nodeCount);
            graph.add(no);
            nodeCount++;    
        }
        
        int sizeCount = 0;
        int y,j;
        while(sizeCount < size) {
            y = new Random().nextInt(graph.size());
            j = new Random().nextInt(graph.size());
            if(!(graph.get(y).getAdj().contains(graph.get(j)))
                    && graph.get(y).getAdj().size() <= order)
            {
                graph.get(y).addAdj(graph.get(j));
                Collections.sort(graph.get(y).getAdj());
                sizeCount++;
            }
        }
        
        for(Node n: graph) {
            System.out.print("Adjacency List for Node " + n + ": ");
            n.printAdj();
            System.out.println("");
        }
        
        System.out.print("Select the starting vertex for BFS and DFS_Visit algorithms (0-" + (graph.size() - 1) + "): ");
        int select = 0;
        if(in.hasNextInt())
            select = in.nextInt();
        Node sVert = graph.get(select);
                
        int timer = 0;
        int timer1 = 0;
        boolean hasCycle = false;
        LinkedList<Node> o = new LinkedList<Node>();
        Queue<Node> q = new LinkedList<Node>();
        System.out.println("Vertices Reachable from node " + sVert + "using BFS");
        BFS(sVert,q);
        System.out.println();
        
        System.out.println("Vertices Reachable from node " + sVert + "using DFS_visit");
        DFS_visit(sVert,sVert.getAdj(),timer);
        System.out.println();
        
        System.out.println("Topological Order of Vertices");
        DFS(graph,o,timer1,hasCycle);
        if(hasCycle == false && !o.isEmpty()) {
            for(Node node: o)
                System.out.println("Node: " + node + " Start: " + node.getStart() + " End: " + node.getEnd());
        }
        else if(hasCycle == true && o.isEmpty()) {
            System.out.println("Cycle detected, topological sort is impossible.");
        }
        in.close();
    }
}
