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

        double[][] test1= {
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
        System.out.println("MAE is " +collaborative_filtering.error(test1));

        System.out.println(test1[0].length);


        //MF method
        double[] test2= {
                1,2,3,   4,5,4,  2,1,2,  2,3,2,  1,2,2, //romantic
                1,2,3,  2,1,2,  2,1,2,  4,5,4,  2,1,1,  //documentar
                4,5,5,  3,2,4,  1,3,1,  2,1,1,  1,1,2,  //act
                1,2,3,  1,2,3,  3,2,4,  4,4,4,  2,1,2, //documentary
                5,4,5,  1,2,3,  1,3,1,  1,1,3,  2,1,1, //act
                1,2,3,   5,5,4,  2,3,2,  2,1,2,  1,1,1,//romantic
                1,2,1,  1,1,1,  2,1,2,  2,1,1,  4,5,5, //comedy
                2,2,1,  1,1,1,  2,2,3,  2,2,1,  5,5,4}; //comedy;
        int N=8; //用户数
        int M=15; //物品数
        int K=5; //主题个数
        double []R=new double[N*M];
        double []P=new double[N*K];
        double []Q=new double[M*K];

    }
}
