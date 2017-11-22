import java.util.Random;

/**
 * Created by zouchanghong on 2017/11/16.
 */
public class Main {

    private static void matrix_factorization(
            double[] R,double[] P,double[] Q, int N,
            int M,int K) {
       int steps=5000;
       double  alpha=  0.0002;
        double beta=  0.02;
        for (int step=0;step<steps;++step){
            for (int i=0;i<N;++i){
                for (int j=0;j<M;++j){
                    if(R[i*M+j]>0){
                        double error= R[i*M+j];

                        for (int k=0;k<K;++k){
                            error -= P[i*K+k]*Q[k*M+j];
                        }
                        for(int k=0;k<K;k++){
                            //beta
//                            P[i*K+k] += alpha * (2 * error * Q[k*M+j] - beta * P[i*K+k]);
//                            Q[k*M+j] += alpha * (2 * error * P[i*K+k] - beta * Q[k*M+j]);
                            P[i*K+k] += alpha * (2 * error * Q[k*M+j]);
                            Q[k*M+j] += alpha * (2 * error * P[i*K+k]);
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
                            //loss += (beta/2) * (Math.pow(P[i*K+k],2) + Math.pow(Q[k*M+j],2));
                            loss += (Math.pow(P[i*K+k],2) + Math.pow(Q[k*M+j],2));
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
//    public double[] init_matrix(int N,int K,String type){
//        if(type.equals("P"))
//        for(int i=0;i<N;++i){
//            for (int j=0;j<K;j++){
//                P[i*K+j]=rand()%9;
//            }
//        }
//    }


    public static void main(String[] args) {
        int N=5; //用户数

         int M=4; //物品数

         int K=2; //主题个数

         double []R=new double[N*M];

         double []P=new double[N*K];

         double []Q=new double[M*K];

         // initialize
        double[] data1={5,3,0,1,4,0,0,1,1,1,0,5,1,0,0,4,0,1,5,4};
        double[] data2={};
        R=data1;
        System.out.println("R matrix");
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
                 System.out.print(String.format("%.2f", temp)+" ");
                 //System.out.print(temp+" ,");
             }
            System.out.println("");
        }
    }

}
