package pa2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import java.util.ArrayList;

public class DegreesOfSeparationBFS {

    // Instance Variables.
    private SymbolGraph sg; 
    private BreadthFirstPaths bfs;
    private ArrayList<String> movies_list;

    // this class cannot be instantiated
    public DegreesOfSeparationBFS(String fname, String delimiter, String source) {
        movies_list = new ArrayList<String>();
        In in = new In(fname);
        while(!in.isEmpty()){
            String[] list = in.readLine().split(delimiter);
            movies_list.add(list[0]);
        }
        this.sg = new SymbolGraph(fname, delimiter);
        Graph G = this.sg.graph();
            int s = sg.indexOf(source);
            bfs = new BreadthFirstPaths(G, s);
        }
    }

    // Getters
    public SymbolGraph getSymbolGraph() { return sg; }
    public BreadthFirstPaths getBreadthFirstPaths() { return bfs; }

    // Read actor's name from standard input, print bacon relationship
    public int baconNumber(String sink)
    {
        if(!this.sg.contains(sink) || !this.bfs.hasPathTo(this.sg.indexOf(sink))){
            return -1;
        }
        int bacon = 0;
        for(int i : bfs.pathTo(this.sg.indexOf(sink))){
            if(this.sg.nameOf(i) != null && movies_list.contains(this.sg.nameOf(i))){
                bacon++;
            }
        }
        System.out.printf("%s has a Bacon number of %d\n", sink, bacon);
        // return bacon number of the sink.
        return bacon;

    }

    // Calculate the path itself.
    public Stack<Integer> graphPath(String sink){
        Stack<Integer> path = new Stack<Integer>();
        for(int i : bfs.pathTo(this.sg.indexOf(sink))){
            path.push(i);
        }
        return path;

    }

    public void printPath(Stack<Integer> path){
        int size = 0;
       for(int i : path){
           if(movies_list.contains(sg.nameOf(i))){
               size++;
           }
       }
       // Until the value of the size computed above...
        for(int i = 0; i < size; i++){
            String actor = sg.nameOf(path.pop());
            String movie = sg.nameOf(path.pop());
            System.out.printf("%s was in \"%s\" with %s\n",actor, movie, sg.nameOf(path.peek()));
        }
        System.out.println();


    }
    /*
     * @param args the command-line arguments
     */
    // Main function.
    public static void main(String[] args) {
        // Taking the three command line arguments.
        String filename  = args[0]; // filename
        String delimiter = args[1]; // delimiter
        String source    = args[2]; // center

        // Creating the object baconGraph of DegreesOfSeparationBFS type. 
        DegreesOfSeparationBFS baconGraph = new DegreesOfSeparationBFS(filename, delimiter, source);

        // Print the Bacon diagram
        //baconGraph.printBaconDiagram();
        int i, no_args = args.length;
        // Get degrees of separation
        for(i=3;i<no_args;i++) {
            baconGraph.baconNumber(args[i]);
            Stack<Integer> path = baconGraph.graphPath(args[i]);
            baconGraph.printPath(path);
        }
    }
}



