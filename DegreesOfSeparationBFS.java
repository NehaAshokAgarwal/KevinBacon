package pa2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import java.util.ArrayList;

public class DegreesOfSeparationBFS {

    // Instance Variables.
    private SymbolGraph sg; // Symbol graph representation of the file provided.
    private BreadthFirstPaths bfs; // Shortest path from the source to every other vertex.
    private ArrayList<String> movies_list; //  ArrayList of movies object (String)

    // this class cannot be instantiated
    public DegreesOfSeparationBFS(String fname, String delimiter, String source) {
        // Initialising the Arraylist movies
        movies_list = new ArrayList<String>();
        // Creating the In object in.
        In in = new In(fname);
        // Read the file fname until it is empty...
        while(!in.isEmpty()){
            // Split the line just read into the list of String object.
            String[] list = in.readLine().split(delimiter);
            //According to the file format, the first element in the list would be the movie name.
            movies_list.add(list[0]);
        }
        // Initialising the Symbol graph i.e. every integer is mapped to the strings in the fname
        // provided by splitting the line according the delimiter provided.
        this.sg = new SymbolGraph(fname, delimiter);
        // Creating graph by calling method graph of the SymbolGraph class.
        Graph G = this.sg.graph();
        // If the sb contains the source provided, then find the shortest path of all the vertex
        // in the graph from the source by creating BreathFirstPath object bfs and passing the Graph object and source to it.
        if(this.sg.contains(source)){
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
        // if the sink is not in the graph or the sink is not connected to the source, then return -1.
        if(!this.sg.contains(sink) || !this.bfs.hasPathTo(this.sg.indexOf(sink))){
            return -1;
        }
        //set the bacon initially to 0.
        int bacon = 0;
        // In the shortest path from the source to the sink...
        for(int i : bfs.pathTo(this.sg.indexOf(sink))){
            // If that particular vertex represent a movie name, then incrementing the bacon number by 1.
            if(this.sg.nameOf(i) != null && movies_list.contains(this.sg.nameOf(i))){
                bacon++;
            }
        }
        // Print out the bacon number according to the format given.
        System.out.printf("%s has a Bacon number of %d\n", sink, bacon);
        // return bacon number of the sink.
        return bacon;

    }

    // Calculate the path itself.
    public Stack<Integer> graphPath(String sink){
        // Create a stack object and call it path.
        Stack<Integer> path = new Stack<Integer>();
        // Walk through the shortest pah of the sink in the graph...
        for(int i : bfs.pathTo(this.sg.indexOf(sink))){
            // push the path into the stack.
            path.push(i);
        }
        // return the stack.
        return path;

    }

    public void printPath(Stack<Integer> path){
       // every other vertex is an actor

        // Computing how many movie names comes in the shortest path of the sink from the source.
        // By iterating through the shortest path and incrementing size variable on every encounter to the movie name.
        int size = 0;
       for(int i : path){
           if(movies_list.contains(sg.nameOf(i))){
               size++;
           }
       }
       // Until the value of the size computed above...
        for(int i = 0; i < size; i++){
            // pop the stack and store the value to  variable actor cause the
            // first value is the actor name and so on...
            String actor = sg.nameOf(path.pop());
            //pop the stack and store the value to the variable movie because
            // the second value is the movie name and so on...
            String movie = sg.nameOf(path.pop());
            //Print out the path according the format provided.
            System.out.printf("%s was in \"%s\" with %s\n",actor, movie, sg.nameOf(path.peek()));
        }
        // printing a next line character at the end.
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



