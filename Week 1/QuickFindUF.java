public class QuickFindUF{
  private int[] id;

  public QuickFindUF(int N){
    id = new int[N];
    for(int i = 0; i < N; i++){
      id[i] = i;
    }
  }

  public boolean connected(int first, int second){
      return id[first] == id[second];
  }

  public void union(int first, int second){
    int numToFind = id[first];
    int updateTo = id[second];

    for(int i = 0; i < id.length; i++){
        if(id[i] == numToFind){
            id[i] = updateTo;
        }
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

  }

  public static void main(String[]args){
      QuickFindUF qf = new QuickFindUF(10);
      qf.union(1,3);
      qf.printId();


  }
}
