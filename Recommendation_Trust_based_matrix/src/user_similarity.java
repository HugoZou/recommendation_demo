/**
 * Created by zouchanghong on 2017/11/22.
 */

import java.util.Map;
public class user_similarity {
    static int user=0;// user who are more simlarity to predict user


    // get and return the simlarity between predict_user and exist user
    public static double more_simlarity(double[][] test,int uid,int index){
        double[] vector1=test[uid];
        double[] vector2;
        double tem_simlarity=0.0;
        double simlarity=-1.1;
        for (int row =0;row<test.length;row++){
            if(uid!=row){
                //inital
                double dotProduct =0.0;
                double normA = 0.0;
                double normB = 0.0;
                vector2=test[row];
                for (int i = 0; i<vector1.length; i++){
                    if(index != i+1){
                        dotProduct += vector1[i] * vector2[i];
                        normA += Math.pow(vector1[i],2);
                        normB += Math.pow(vector2[i],2);
                    }
                }
                tem_simlarity  = dotProduct /( (Math.sqrt(normA)) * (Math.sqrt(normB)));
                if(tem_simlarity>simlarity) {
                    simlarity = tem_simlarity;
                    user=row;
                }
            }
        }
        return simlarity;
    }

    //average of predict_user
    public static double Average(double matrix[][],int ItemId,int User){

        double average=0;
        int cols = matrix[0].length;
        for(int index=0;index< cols;index++){
            if(index!=ItemId) {
                average += matrix[User][index];
            }
        }
        average = average / (cols-1);
        return average;
    }
    //average of exist user
    public static double Average(double matrix[][],int User){
        double average=0;
        int cols = matrix[0].length;
        for(int index=0;index< cols;index++){

            average+=matrix[User][index];

        }
        average = average / cols;
        return average;
    }

    //return the value of predicting which was based on the more simlarity user data
    public static double predict(double matrix[][], int UserID, int ItemID){
        double predict_value=0.0;
        double simlarity=more_simlarity(matrix,UserID,ItemID);
        int more_simlarity_user = user;
        double average_user=0.0;
        double average_predict_user=0.0;
        average_user = Average(matrix,more_simlarity_user);
        average_predict_user=Average(matrix,ItemID,UserID);
        predict_value = average_predict_user +
                simlarity*(matrix[more_simlarity_user][ItemID]-average_user)/Math.abs(simlarity);
        return predict_value;
    }

    //Caculate mae error
    public static double error(double matrix[][]){
        double sum=0;
        int column=matrix[0].length;
        int total_user=matrix.length;
        for(int j=0;j<total_user;j++){
            for(int i=0;i<column;i++){
                sum += Math.abs(predict(matrix,j,i)-matrix[j][i]);
            }
        }
        return sum/(column*total_user);
    }



    public static void main(String[] args) {
        // 12 items
        //       act   romantic documentray comedy
        double[][] test= {
                {1,2,3,   4,5,4,  2,1,2,  2,3,2,  1,2,2},//romantic
                {1,2,3,  2,1,2,  2,1,2,  4,5,4,  2,1,1,}, //documentar
                {4,5,5,  3,2,4,  1,3,1,  2,1,1,  1,1,2}, //act
                {1,2,3,  1,2,3,  3,2,4,  4,4,4,  2,1,2,}, //documentary
                {5,4,5,  1,2,3,  1,3,1,  1,1,3,  2,1,1}, //act
                {1,2,3,   5,5,4,  2,3,2,  2,1,2,  1,1,1},//romantic
                {1,2,1,  1,1,1,  2,1,2,  2,1,1,  4,5,5,}, //comedy
                {2,2,1,  1,1,1,  2,2,3,  2,2,1,  5,5,4,}, //comedy

        };
        System.out.println("MAE is " +error(test));



    }
}
