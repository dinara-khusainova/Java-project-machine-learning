package AI;
import java.util.Random;
public class AI {
    //-0.24630666  -0.15008605  1.4959582  -0.0701679  -1.3195424  1.3173544  0.9111799  76
    //-0.15461874  0.3612156  3.8194861  -0.67561996  -2.461247  -2.2251077  -3.3329325  79
    //-0.2836951f,  0.3401295f,  3.013947f,  -0.111403465f,  -2.1147878f,  -2.0710797f,  -3.5993f, 74
    float[]k=new float[7];// Массив коэффициентов и 7-констант
    public boolean shouldMove(float x_light, float y_light, float x_spider, float y_spider, float x_bomb, float y_bomb){
        // AI должен сделать движение, если значение вычисления меньше 0
        return k[0]*x_light+k[1]*y_light+k[2]*x_spider+k[3]*y_spider+k[4]*x_bomb+k[5]*y_bomb+k[6]<0;
    }
    public AI(float[]k){
        // Конструктор класса AI, принимает массив коэффициентов и констант
        for(int i =0;i<7;i++){
            this.k[i]=k[i];
        }
    }
    AI(float delta, AI parent){
        Random rng=new Random();
        for(int i =0;i<7;i++){
            // Генерируем новые коэффициенты, основываясь на родительском объекте AI и дельте
            k[i]=parent.k[i]-delta+ rng.nextFloat(delta)*2;
        }
    }
    void output(){
        for(int i =0;i<7;i++){
            System.out.print(k[i]+"f,  ");}
        System.out.println("  ");
    }
}
