/*
In this data structure, each index contains the root it is connected to. The data structure
essentially represents a tree. If an index's id is the same as itself, it is either
a standalone component or it is a root that has multiple children.

This makes the UNION operation quicker, hence Quick UNION.

So to do a union operation e.g qu.union(7,3):
Find the topmost root of 7, then the topmost root of 3, then
Change the index containing topmost root of 7 to topmost root of 3.
I.e you are moving the whole branch under another root.
 */

public class QuickUnionUF{
    private int[] id;
    //Populate array - N array access
    public QuickUnionUF(int N){
        id = new int[N];
        for(int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    //Runtime - depth of index array accesses
    public int findTopRoot(int index){
        //While loop to find root index - recursion doesn't really work
        while(id[index] != index){
            index = id[index];
        }

        return index;


    }

    //Changes top-most root of first to the topmost root of the second - shift the tree
    //Runtime - depth of first + depth of second array accesses
    public void union(int first, int second){
        int rootToUpdate = findTopRoot(first);
        int updateTo = findTopRoot(second);



        id[rootToUpdate] = updateTo;
    }

    //Connected if topmost roots are connected.
    //Runtime: depth of first + depth of second array accesses
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

    }

    public static void main(String[]args){
        QuickUnionUF qf = new QuickUnionUF(10);
        qf.union(4,3);

        qf.union(3,8);

        qf.union(6,5);

        qf.printId();

        qf.union(9,4);
        System.out.println(qf.connected(9,1));
        qf.printId();
    }

    /*

    Union: Runtime < O(N) in average case because depth of roots might be less. But worst case - O(N)
    since must find top root for both.

    Find(isConnected) => O(N) only in worst case.

    Trees can get too tall so find becomes too expensive.
     */

}