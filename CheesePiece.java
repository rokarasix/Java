package programs;

public class CheesePiece {

    public static void main(String[] args) {

        int arr[][] = new int[][] { { 1, 1, 1 }, { 0, 1, 0 }, { 0, 0, 9 } };
        CheesePiece c = new CheesePiece();

        int n = c.isPath(arr, 3, 3);
        System.out.println(n);
    }


    int isPath(int[][] arr, int m, int n) {

        return solveMazeUtil(arr, 0, 0, m, n);
    }


    Integer solveMazeUtil(int[][] arr, int x, int y, int m, int n) {

        if ((x + 1 == m - 1) && (y + 1 == n - 1) && (arr[x + 1][y + 1] == 9)) {
            return 1;
        }

        if (x >= 0 && x < m && y >= 0 && y < n) {

            if (arr[x][y] == 9) {
                return 1;
            }

            else if (arr[x][y] == 1){

                if (solveMazeUtil(arr, x + 1, y, m, n) == 1) {
                    return 1;
                }

                if (solveMazeUtil(arr, x, y + 1, m, n) == 1) {
                    return 1;
                }

            }

    }

            return 0;

}
}

