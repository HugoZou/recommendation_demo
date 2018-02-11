/**
 * Created by zouchanghong on 2017/12/7.
 */
public class Main {
    public static void main(String[] args) {
//        double [] matrx={
//                1,2,0,5,4,4,1,2,0,5,4,4,
//                0,1,1,4,5,5,0,1,1,4,5,5,
//                1,0,2,5,4,5,1,0,2,5,4,5,
//                0,2,2,5,4,4,0,2,2,5,4,4,
//                4,5,5,0,1,1,4,5,5,0,1,1,
//                5,4,5,1,0,2,
//                5,4,4,1,2,0,
//                4,5,5,0,1,1,
//                5,4,5,1,0,2,
//
//        };
        double[][] test1_guess= {
                {1,2,3,   4,0,4,  2,1,2,  2,0,2,  1,2,2},//romantic
                {1,2,3,  2,1,2,  2,1,2,  4,0,4,  2,1,1,}, //documentar
                {4,5,5,  3,2,4,  1,3,1,  2,1,1,  1,0,2}, //act
                {0,2,3,  1,2,3,  3,2,4,  4,0,4,  2,1,2,}, //documentary
                {5,4,5,  1,2,3,  1,3,1,  1,0,3,  2,1,1}, //act
                {1,2,3,   5,5,0,  2,3,2,  2,1,2,  1,1,1},//romantic
                {1,2,1,  1,1,1,  2,1,2,  2,1,1,  0,5,5,}, //comedy
                {2,2,1,  1,0,1,  2,2,3,  2,2,1,  5,5,0,}, //comedy

        };

        double[][] test1_real= {
                {1,2,3,   4,5,4,  2,1,2,  2,3,2,  1,2,2},//romantic
                {1,2,3,  2,1,2,  2,1,2,  4,5,4,  2,1,1,}, //documentar
                {4,5,5,  3,2,4,  1,3,1,  2,1,1,  1,1,2}, //act
                {1,2,3,  1,2,3,  3,2,4,  4,4,4,  2,1,2,}, //documentary
                {5,4,5,  1,2,3,  1,3,1,  1,1,3,  2,1,1}, //act
                {1,2,3,   5,5,4,  2,3,2,  2,1,2,  1,1,1},//romantic
                {1,2,1,  1,1,1,  2,1,2,  2,1,1,  4,5,5,}, //comedy
                {2,2,1,  1,1,1,  2,2,3,  2,2,1,  5,5,4,}, //comedy

        };


        //CF
        System.out.println("Collaborative Filtering MAE is " +collaborative_filtering.error(test1_guess,test1_real));



        //MF method

        //2-dimension to 1-dimension
        double[] test2_guess;
        double[] test2_real;
        int len = 0;

        for (double[] elements : test1_guess){
            len+= elements.length;
        }
        test2_guess= new double[len];
        test2_real = new double[len];
        int index =0;
        for (double[] array : test1_guess){
            for (double element : array){
                test2_guess[index++] = element;
            }
        }
         index =0;
        for (double[] array : test1_real){
            for (double element : array){
                test2_real[index++] = element;
            }
        }

        int N=8; //用户数
        int M=15; //物品数
        int K=5; //主题个数
        double []R=new double[N*M];
        R=test2_guess;
        double []P=new double[N*K];
        double []Q=new double[M*K];

        //initialize
        for(int i=0;i<N;++i){
            for(int j=0;j<K;++j){
                P[i*K+j]= Math.random()%9;
            }
        }

        for(int i=0;i<K;++i) {
            for (int j = 0; j < M; ++j) {
                Q[i * M + j] = Math.random() % 9;


            }
        }
        Matrix_factorization_learning.matrix_factorization(R,P,Q,N,M,K);
}
