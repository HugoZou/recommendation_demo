import java.util.Random;
import java.io.*;
/**
 * Created by zouchanghong on 2017/11/16.
 */

// SVD method
public class Main {

    public static void matrix_factorization(
            double[] R,double[]  P,double[] Q, int N,
            int M,int K) {
       int steps=5000;
       double  alpha=  0.0004;
        double beta=  0.01;
        for (int step=0;step<steps;++step){
            for (int i=0;i<N;++i){
                for (int j=0;j<M;++j){
                    if(R[i*M+j]>0){
                        double error= R[i*M+j];

                        for (int k=0;k<K;++k){
                            error -= P[i*K+k]*Q[k*M+j];
                        }
                        for(int k=0;k<K;k++){

                            P[i*K+k] += alpha * (2 * error * Q[k*M+j] - beta * P[i*K+k]);
                            Q[k*M+j] += alpha * (2 * error * P[i*K+k] - beta * Q[k*M+j]);

                        }
                    }
                }

            }
            double loss = 0;
            for (int i=0;i<N;i++){
                for (int j=0;j<M;++j){
                    if(R[i*M+j]>0){
                        double error = 0;
                        for (int k=0;k<K;k++){
                            error += P[i*K+k]*Q[k*M+j];
                        }
                        loss += Math.pow(R[i*M+j]-error,2);
                        for (int k=0;k<K;k++){
                           loss += (beta/2) * (Math.pow(P[i*K+k],2) + Math.pow(Q[k*M+j],2));
                           // loss += (Math.pow(P[i*K+k],2) + Math.pow(Q[k*M+j],2));
                        }
                    }
                }
            }

            if (loss<0.001){
                break;
            }
            if (step%500 == 0){
                System.out.println("loss:"+loss);
            }

        }
    }

    public static double []generator(int user,int item){
        double matrix[]= new double[user*item];
        int max=5;
        int min=1;
        Random random = new Random();
        for(int index__user=0;index__user<user;index__user++){
            for(int index_item=0;index_item<item;index_item++){
                matrix[index__user*item+index_item]=random.nextInt(max)%(max-min+1)+min;
            }
        }
        return matrix;
    }

    public static double[] simulator(double[] matrix){
        int max=8;
        int min=3;
        Random random = new Random();
        for (int i=0;i<matrix.length;){
//            matrix[i]=0;
            matrix[i]=0;
            i+=random.nextInt(max)%(max-min+1)+min;
        }
        return matrix;
    }


    public static double error(double real[],double[]predict){
        double sum=0;
        for (int i=0;i<real.length;i++){
      //      System.out.println(real[i]+" "+predict[i]);
            sum+= Math.abs(real[i]-predict[i]);
        }
      //  System.out.println(sum);
        return sum/(real.length);
    }

    public static void main(String[] args) {
         int N=50; //用户数
         int M=10; //物品数

         int K=10; //主题个数

         double []R=new double[N*M];

         double []P=new double[N*K];

         double []Q=new double[M*K];

         // initialize
        double[] data_real=generator(N,M);
        double[] data_temple=new double[N*M];
        // temple matrix of realdata
        for (int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                data_temple[i*M+j]=data_real[i*M+j];
            }
        }




        System.out.println("R real matrix");
        for (int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                System.out.print(data_real[i*M+j]+",　");
            }
            System.out.println("");
        }

        double[] data_blank= new double[N*M];
        data_blank=simulator(data_temple);
        double[] data_predict=new double[N*M];
        R=data_blank;
        //R=data_real;


        System.out.println("R blank matrix");
        for (int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                System.out.print(R[i*M+j]+",　");
            }
            System.out.println("");
        }



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
        //
        System.out.println("Factorize Start");
        matrix_factorization(R,P,Q,N,M,K);
        System.out.println("Factorize End");

        //R matrix
        for(int i=0;i<N;++i){
             for(int j=0;j<M;++j) {
                  double temp=0;
                  for (int k=0;k<K;++k){
                   temp+=P[i*K+k]*Q[k*M+j];
                  }
                  data_predict[i*M+j]=temp;
                 System.out.print(String.format("%.2f", temp)+" ");
                 //System.out.print(temp+" ,");
             }
            System.out.println("");
        }
        //System.out.println(data_predict.length);




        System.out.println("MAE is "+ error(data_real,data_predict));
    }

}
