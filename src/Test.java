import matrix.Matrix;

/**
 * Created by Administrator on 2018/2/8.
 */
public class Test {
    public static void main(String[] args){
        double[][] ma1 = {{2,1,0},
                          {0,2,0},
                          {2,0,1}};
        double[][] ma2 = {{0.5,-0.25,0},
                          {0,0.5,0},
                          {-1,0.5,1}};
        Matrix A = new Matrix(ma1);
        Matrix B = new Matrix(ma2);
        Matrix C = A.times(B);
        C.print(2);
        System.out.println();
        System.out.println();
    }
}
