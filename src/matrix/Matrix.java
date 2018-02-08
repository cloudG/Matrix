package matrix;

import java.math.BigDecimal;

/**
 * Created by Guo on 2018/2/8.
 */
public class Matrix {
    /**
     * Version
     */
    private static final long serialVersionUID = 1L;

    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double get(int m, int n) {
        return matrix[m][n];
    }

    public void set(int m, int n, double value) {
        matrix[m][n] = value;
    }

    public int getRowNumber() {
        return matrix.length;
    }

    public int getColNumber() {
        return matrix[0].length;
    }

    public Matrix copy(){
        Matrix A = new Matrix(this.matrix);
        return A;
    }

    public Matrix T(){
        Matrix A = new Matrix(new double[this.matrix[0].length][this.matrix.length]);
        for(int i = 0;i<A.getRowNumber();i++){
            for(int j=0;j<A.getColNumber();j++){
                A.set(i,j,this.get(j,i));
            }
        }
        return A;
    }

    /**
     * Only 2X2 Matrix
     * @return Matrix
     */
    public Matrix inverse(){
        Matrix A = new Matrix(new double[this.matrix.length][this.matrix[0].length]);
        double num = 1 / ((this.matrix[0][0] * this.matrix[1][1]) - (this.matrix[0][1] * this.matrix[1][0]));
        A.set(0,0,this.matrix[1][1] * num);
        A.set(0,1,this.matrix[0][1] * (-num));
        A.set(1,0,this.matrix[1][0] * (-num));
        A.set(1,1,this.matrix[0][0] * num);
        return A;
    }

    public Matrix zero(int m, int n){
        double[][] d = new double[m][n];
        for (int i =0;i<m;i++){
            for (int j =0;j<n;j++){
                d[i][j]=0;
            }
        }
        Matrix A = new Matrix(d);
        return A;
    }

    public Matrix identity(int size){
        double[][] d = new double[size][size];
        for (int i =0;i<size;i++){
            for (int j =0;j<size;j++){
                d[i][j]=0;
                if(i==j){
                    d[i][j]=1;
                }
            }
        }
        Matrix A = new Matrix(d);
        return A;
    }

    /**
     *
     * @param num
     */
    public void print(int num) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(" [ ");
            for (int j = 0; j < matrix[0].length; j++) {
                BigDecimal b = new BigDecimal(matrix[i][j]);
                double number = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.print(number + " ");
            }
            System.out.println("] ");
        }
        System.out.print("]");
    }

    public Matrix plus(Matrix A) {
        Matrix C = new Matrix(new double[A.getRowNumber()][A.getColNumber()]);
        if (this.getRowNumber() == A.getRowNumber() && this.getColNumber() == A.getColNumber()) {
            for (int i = 0; i < C.getRowNumber(); i++) {
                for (int j = 0; j < C.getColNumber(); j++) {
                    double result = this.get(i, j) + A.get(i, j);
                    C.set(i, j, result);
                }
            }
        }
        return C;
    }

    public Matrix minus(Matrix A) {
        Matrix C = new Matrix(new double[A.getRowNumber()][A.getColNumber()]);
        if (this.getRowNumber() == A.getRowNumber() && this.getColNumber() == A.getColNumber()) {
            for (int i = 0; i < C.getRowNumber(); i++) {
                for (int j = 0; j < C.getColNumber(); j++) {
                    double result = this.get(i, j) - A.get(i, j);
                    C.set(i, j, result);
                }
            }
        }
        return C;
    }

    public Matrix times(double num) {
        Matrix C = new Matrix(new double[this.getRowNumber()][this.getColNumber()]);
        for (int i = 0; i < C.getRowNumber(); i++) {
            for (int j = 0; j < C.getColNumber(); j++) {
                double result = this.get(i, j) * num;
                C.set(i, j, result);
            }
        }
        return C;
    }

    public Matrix times(Matrix A) {
        Matrix C = new Matrix(new double[this.getRowNumber()][A.getColNumber()]);
        if (this.getColNumber() == A.getRowNumber()) {
            double result = 0;
            for (int i = 0; i < this.getRowNumber(); i++) {
                for (int j = 0; j < A.getColNumber(); j++) {
                    for (int k = 0; k < this.getColNumber(); k++) {
                        result += this.get(i, k) * A.get(k, j);
                    }
                    C.set(i, j, result);
                    result = 0;
                }
            }
        }
        return C;
    }

    public Matrix divide(double num) {
        Matrix C = new Matrix(new double[this.getRowNumber()][this.getColNumber()]);
        for (int i = 0; i < C.getRowNumber(); i++) {
            for (int j = 0; j < C.getColNumber(); j++) {
                double result = this.get(i, j) / num;
                C.set(i, j, result);
            }
        }
        return C;
    }
}
