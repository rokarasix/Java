import java.util.*;

class March
{
    static int minimum= Integer.MIN_VALUE;
    static int max=0;
    static int ways=0;
    static TreeMap<Integer,Integer> map=new TreeMap<Integer, Integer>();
    public static void main(String args[]){

        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();
        while(t!=0){

            int n=sc.nextInt();
            int a[][]=new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    String s=sc.next();
                    if(s.equals("s")){
                        a[i][j]=-2;
                    }
                    else if(s.equals("e") ){
                        a[i][j]=minimum;
                    }
                    else if(s.equals("x")){
                        a[i][j]=-1;
                    }
                    else{
                        a[i][j]=Integer.valueOf(String.valueOf(s));
                    }

                    System.out.print(" ");
                }
                System.out.println();
            }

            March m=new March();
            m.travel(a,n-1,n-1,0,n);
            t--;
        }
    }

    private void travel(int a[][],int i,int j,int sum,int n){

        TreeMap<Integer,Integer> mapRes=travelUtil(a,i,j,sum,n);

        System.out.println(mapRes.lastKey()+" "+ mapRes.get(mapRes.lastKey()));

    }

    private TreeMap<Integer,Integer> travelUtil(int a[][],int i,int j,int sum,int n){

        if(i>=0 && i<n && j>=0 && j<n){

            if(a[i][j]== minimum){
                max=sum;
                if(!map.containsKey(max)){
                    map.put(max,ways);
                }else{
                    int v=map.get(max);
                    map.put(max,++v);
                }


                return map;
            }

            else if(a[i][j]!=-1 && (i!=n-1 || j!=n-1)){

                sum=sum+a[i][j];
                if(max<sum){
                    max=sum;
                    ways=1;
                }
                travelUtil(a,i-1,j,sum,n);
                travelUtil(a,i-1,j-1,sum,n);
                travelUtil(a,i,j-1,sum,n);
            }
            else if(i==n-1 && j==n-1){
                travelUtil(a,i-1,j,sum,n);
                travelUtil(a,i-1,j-1,sum,n);
                travelUtil(a,i,j-1,sum,n);
            }

        }

        return map;
    }

}

