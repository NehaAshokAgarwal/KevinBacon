package pa2;
import java.util.*;

/**
 * Class to demonstrate greedy algorithms. Skeleton. 
 */
public class Grid {
    private boolean[][] grid = null;
    private ArrayList<Set<Spot> > allGroups; // All groups
    /**
     * Very simple constructor
     *
     * @param ingrid
     *            a two-dimensional array of boolean to be used as the grid to
     *            search
     */
    public Grid(boolean[][] ingrid) {
        grid = ingrid;
        allGroups = new ArrayList<Set<Spot> >();
    }

    /**
     * Main method, creates a Grid, then asks it for the size of the group
     * containing the given point.
     */
    public static void main(String[] args) {
        // Check arguments here.

        // Usage: java Grid 3 7 to search from (3, 7), top occupied square
        // of L-shaped group.
        // -all to print all sets.

        boolean[][] gridData = {
                { false, false, false, false, false, false, false, false,
                        false, true },
                { false, false, false, true, true, false, false, false, false,
                        true },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, true, false, false, true, false,
                        false },
                { false, false, false, true, false, false, false, true, false,
                        false },
                { false, false, false, false, false, false, false, true, true,
                        false },
                { false, false, false, false, true, true, false, false, false,
                        false },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false } };
        // Other stuff here.

    }

    public void printAllGroups()
    {
        for(Set<Spot> g:allGroups) {
            for(Spot s:g)
                System.out.println(s);
            System.out.println("");
        }
    }
    // The best way IMO to calculate the number of groups is to set up a matrix of integers and
    // for each non-0 entry calculate the group it's in.
    public ArrayList<Set<Spot>> calcAllGroups() {
        // walking through every square in the N by N grid.
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                // Creating set of spot  objects for every square we go through...
                Set<Spot> set = new HashSet<>();
                // Calling the private recursion method to find the group members...
                 recursion(set, i, j);
                 // if the set is not empty, we add it to the allgroups array.
                 if(!set.isEmpty()){
                    allGroups.add(set);
                }
            }
        }
        // return the array.
        return allGroups;

    }
    public void recursion(Set<Spot> set, int i, int j){
        // if i or j is not in the grid then return i.e. [0, N].
        if (j < 0 ||j > grid.length-1 || i < 0 || i > this.grid.length-1){
            return;
        }
        // if spot (square) is not true (i.e. 0) then return because it is not in the group.
        if(!this.grid[i][j]){
            return;
        }
        // Otherwise, set the square(i,j) in the grid to false.
        grid[i][j] = false;
        // Create a spot object by passing i or j.
        Spot spot = new Spot(i, j);
        // add the spot into the allgroups array
        set.add(spot);
        //recurse to the square's corresponding neighbour to search if it's in the group or not.
        recursion(set, i+1, j);
        recursion(set, i-1, j);
        recursion(set, i, j+1);
        recursion(set, i, j-1);
    }
    /**
     * Prints out a usage message
     */
    private static void printUsage() {
        System.out.println("Usage: java Grid <i> <j>");
        System.out.println("Find the size of the cluster of spots including position i,j");
        System.out.println("Usage: java Grid -all");
        System.out.println("Print all spots.");
    }

    /**
     * This calls the recursive method to find the group size
     *
     * @param i
     *            the first index into grid (i.e. the row number)
     * @param j
     *            the second index into grid (i.e. the col number)
     * @return the size of the group
     */
    public int groupSize(int i, int j) {
        // i and j are not in the grid itself i.e. they cross the boundaries [0, N].
        if (j < 0 ||j > grid.length-1 || i < 0 || i > this.grid.length-1){
            throw new IndexOutOfBoundsException("Illegal i or j");
        }
        // initialising the variables(keeps track of the number of members of the group) to 0
        int right = 0;
        int left = 0;
        int bottom = 0;
        int top = 0;

        // If the square(i,j) in thr group is set as false, then return because it is not in the group.
        if(!this.grid[i][j]){
            return 0;
        }
        // Set the square in the grid to false.
        grid[i][j] = false;
        // Recurse to the corresponding neighbours of the square to search if they are a member of the group.
        right = groupSize(i,j+1);
        bottom = groupSize(i+1,j);
        left = groupSize(i,j-1);
        top = groupSize(i-1,j);

        // return the size of the group.
        return 1 + right + bottom + left + top;
    }

    public static class Spot {
        int i;
        int j;
        int group;
        /**
         * Constructor for a Spot
         *
         * @param i
         *            the i-coordinate of this Spot
         * @param j
         *            the j-coordinate of this Spot
         */
        public Spot(int i, int j) {
            this.i = i;
            this.j = j;
            this.group = 0; // Default. Will be set later.
        }

        /**
         * Tests whether this Spot is equal (i.e. has the same coordinates) to
         * another
         *
         * @param o
         *            an Object
         * @return true if o is a Spot with the same coordinates
         */
        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (o.getClass() != getClass())
                return false;
            Spot other = (Spot) o;
            return (other.i == i) && (other.j == j);

        }

        /**
         * Returns an int based on Spot's contents
         * another way: (new Integer(i)).hashCode()^(new Integer(j)).hashCode();
         */
        public int hashCode() {
            return i << 16 + j; // combine i and j two halves of int
        }

        public void setGroup(int g) {group = g;}

        public int getI() {return i;}
        public int getJ() {return j;}
        public int getGroup() {return group;}
        /**
         * Returns a String representing this Spot, just the coordinates. You can add group if you want.
         */
        public String toString() {
            return "(" + i + " , " + j+")";
        }
    }
}