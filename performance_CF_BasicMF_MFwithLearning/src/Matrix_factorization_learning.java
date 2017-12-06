import java.util.Random;

/**
 * Created by zouchanghong on 2017/12/7.
 */
public class Matrix_factorization_learning {
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


    public static double error(double real[],double[]predict){
        double sum=0;
        for (int i=0;i<real.length;i++){
            //      System.out.println(real[i]+" "+predict[i]);
            sum+= Math.abs(real[i]-predict[i]);
        }
        //  System.out.println(sum);
        return sum/(real.length);
    }
}
