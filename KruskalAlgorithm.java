import java.util.Scanner;

class Edge implements Comparable<Edge> {
    private int v1;
    private int v2;
    private int w;

    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }

    public int getV1() {
        return this.v1;

    }

    public int getV2() {
        return this.v2;
    }

    public int getW() {
        return w;
    }
}

public class KruskalAlgorithm {

    public static void main(String[] args) {
        // Taking input for graph

        Scanner sc  =new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n  =sc.nextInt();

        System.out.println("Enter number of edges: ");
        int e = sc.nextInt();


        Edge[] edges = new Edge[e];
        System.out.println("Enter both vertex  and weight of the edges");
        for(int i = 0  ; i< e; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();
            Edge obj = new Edge(v1, v2, w);
            edges[i]  = obj;
        }

        Edge[] mst = kruskalAlgorithmMethod(edges, n);
        System.out.println("MST all edges with weight: ");
        System.out.println("v1\tv1\tw");
        for(int i =  0 ; i< mst.length; i++){
            if(mst[i].getV1() < mst[i].getV2())
            System.out.println(mst[i].getV1()+"\t"+ mst[i].getV2()+"\t"+mst[i].getW());
            else
                System.out.println(mst[i].getV2()+"\t"+ mst[i].getV1()+"\t"+mst[i].getW());

        }

    }

    private static Edge[] kruskalAlgorithmMethod(Edge[] edges, int n) {

        Edge[] mst = new Edge[n-1];
        int[] parent = new int[n];
        for(int j = 0 ; j< n; j++)
            parent[j] = j;

        
        int count = 0 , i =0 ;

        while(count != n-1){

            Edge curEdge = edges[i++];
            int parentV1 = findParent(curEdge.getV1(), parent);
            int parentV2  =findParent(curEdge.getV2(), parent);

            if(parentV1 != parentV2){
                // This edge will include in mst
                mst[count] = curEdge;
                count++;
                parent[parentV1]  =parentV2;
            }
        }



        return mst;
    }

    private static int findParent(int v, int[] parent) {
        if(v == parent[v])
            return v;
        return findParent(parent[v], parent);
    }
}