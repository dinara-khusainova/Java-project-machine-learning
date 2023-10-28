// Обучение AI путем создания и сравнения дочерних объектов AI с родительским объектом start

package AI;
public class Educator {
    public static void main(String[]args){
        AI start = new AI(new float[]{0,0,0,0,0,0,0}); // Массив нулевых коэффициентов и констант
        AI best = start;
        int res = result(best);
        // Создаем новый объект child и выбираем лучший
        while (res < 100){
            for(int i = 0; i < 100;i ++){
                AI child = new AI(1f,start);
                int child_res=result(child);
                if(child_res > res){
                    best=child;
                    res=child_res;
                }
            }
            start=best;
            start.output();
            System.out.println(res);
        }
    }
    static int result(AI ai){
        int res=0;
        for(int i =0; i<10;i++){
            res+=new Simulator(ai).run();
        }
        return res/10;
    }
}

