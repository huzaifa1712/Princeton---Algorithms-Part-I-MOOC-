/*
Problem with QuickUnion: Larger trees can get put under smaller trees, so distance to root is higher,
making root(p) operation much more expensive

Improvement: Only put smaller trees under bigger trees

Implementation: Maintain size array. Contains number of objects rooted at node i.
When doing union, use size to check for the smaller tree and put it under the larger tree
(change topmost root of the node with the smaller tree to that of the larger).
Then increment the size of the new tree.
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
    //Same as normal QuickUnion
    public int findTopRoot(int index){
        //While loop to find root index - recursion doesn't really work
        while(id[index] != index){
            index = id[index];
        }

        return index;


    }

    //Heuristic - check size array, if one tree(number of objects rooted at topmost root)
    //is smaller put it under the larger tree.
    public void union(int first, int second){
        int rootToUpdate = findTopRoot(first);
        int updateTo = findTopRoot(second);

        if (!(rootToUpdate == updateTo)) {
            //If topmost root of first has less objects connected to it, put first under second(default behaviour)
            if (sz[first] < sz[second]) {
                id[rootToUpdate] = updateTo;
                sz[second] += sz[first];

            }

            else {
                //But if topmost root(first) has more objects connected to it than that of the 2nd,
                //Put the topmost root of second under first
                //if both are equal size it comes to this case as well
                id[updateTo] = id[rootToUpdate];
                sz[first] += sz[second];
            }
        }
    }

    //Same as normal quick union - check if topmost roots equal
    public boolean connected(int first, int second){
        return findTopRoot(first) == findTopRoot(second);
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
        qfw.union(4,3);
        qfw.union(3,8);
        qfw.union(6,5);
        qfw.union(9,4);
        qfw.printId();
        System.out.println("");
        qfw.printSizes();
    }

    /*
    Running time analysis:
    Union: Constant time given roots, without roots its proportional to depth of first and second
    Find(connected): Takes time proportional to depth of first and second
    - But for find, with weighted quick union, depth of any node x is at most log2 (N) N - total nodes in tree
    Why?:
    - Imagine a node x in Tree 1(T1), put under T2
    - In what situation will depth increase?: When size of T2 >= T1
    - This means the size of T1 ATLEAST DOUBLES - because T2 must be bigger or equal to T1
    - If you double 1 log2 N times you get N => Max depth can only be log 2(N)
    - i.e Size of tree containing x can only double at most log2(N) times since no.of nodes in tree = N

    Runtime comparison
                            Initialise          Union           Connected
    quick-find                   N                N                 N
    quick-union                  N             usually < N          N
    weighted quick-union         N              log2(N)           log2(N)


     */
}