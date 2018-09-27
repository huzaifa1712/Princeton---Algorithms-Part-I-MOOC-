/*
Problem with QuickUnion: Larger trees can get put under smaller trees, so distance to root is higher,
making root(p) operation much more expensive

Improvement: Only put smaller trees under bigger trees

Implementation: Maintain size array. Contains number of objects rooted at node i.
When doing union, use size to check for the smaller tree and put it under the larger tree
(change topmost root of the node with the smaller tree). Then increment the size of the new tree.
 */

public class WeightedQuickUnionUF{
    private int id[]; //contain index of immediate root
    private int sz[]; //number of objects rooted at index i(at first 1:itself)

    //Initialise id to indexes, sz array to 1 for each.
    public WeightedQuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];

        for(int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void printId(){
        for(int i = 0; i < id.length; i++){
            System.out.print(i);
            System.out.print(" ");
        }

        System.out.println("");

        for(int j = 0; j < id.length; j++){
            System.out.print(id[j]);
            System.out.print(" ");
        }

        System.out.println("");
        System.out.println("");

    }

    public void printSizes(){
        for(int i = 0; i < id.length; i++){
            System.out.print(i);
            System.out.print(" ");
        }

        System.out.println("");

        for(int j = 0; j < id.length; j++){
            System.out.print(sz[j]);
            System.out.print(" ");
        }

        System.out.println("");
        System.out.println("");


    }

    public static void main(String[]args){
        WeightedQuickUnionUF qfw = new WeightedQuickUnionUF(10);
        qfw.printId();
        System.out.println("");
        qfw.printSizes();
    }

}