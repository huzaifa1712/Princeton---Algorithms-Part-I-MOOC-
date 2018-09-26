//Dynamic connectivity problem - given set of N objects connect 2 objects or find if any two
//are connected

//One way is QuickFind - all objects represented with ids in array. If 2 objects connected,
//change to have the same ID(Make it the same as the second id passed in)
//e.g qf.union(1,3): Change the id of index 1 to match that of index 3. So all objects
//connected to index 1 will also automatically now be connected to index 3.

//QuickFIND - Find(isConnected?) is O(1), but Union is O(N)

public class QuickFindUF{
  private int[] id; //store all ids in array

  public QuickFindUF(int N){
    id = new int[N];
    for(int i = 0; i < N; i++){
      //Initialise to be same as index number(only connected to itself)
      id[i] = i;
    }
  }

  //Connected if their ids are the same
  public boolean connected(int first, int second){
      return id[first] == id[second];
  }

  //Update all elements with the same id as the first one to have the id of the second one.
  public void union(int first, int second){
    int numToFind = id[first];
    int updateTo = id[second];

    for(int i = 0; i < id.length; i++){
        if(id[i] == numToFind){
            id[i] = updateTo;
        }
    }
  }

  //Print indexes(nodes/objects) then ids below.
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
      QuickFindUF qf = new QuickFindUF(10);
      qf.union(1,3);
      qf.printId();


  }
}

/*
Problems with this model:
Union runtime: O(N), initialise: O(N).
Find/connected method: O(1)

Union is an expensive operation: takes N^2 array access to process N union commands on N
objects. Must iterate through the whole array to change ids.

Quadratic operations are too expensive: as computers get bigger and faster, the speed increases
but memory size also increases. So the time to access everything remains the same.
=> Doesn't scale with technology
 */