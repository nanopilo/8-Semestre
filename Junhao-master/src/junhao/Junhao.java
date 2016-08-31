package junhao;

import java.util.HashMap;
import java.util.Map;

public class Junhao {

    private static Map buffer;
    
    public static void main (String ARGS[]){
        Junhao junhao = new Junhao();
        buffer = junhao.createMap(20);
        buffer = junhao.generateRandomNumber(buffer, 500, 4);
        junhao.printMap(buffer);
        }

    public Map createMap(double lambda) {
        buffer = new HashMap();
        double size = lambda;
        for (double i = 0; i < size; i++) {
            buffer.put(i, 0);
                                              
        }
        return buffer;
    }

    public Map generateRandomNumber(Map buffer, double runs, double lambda) {
        for (double i = 0; i < runs; i++) {
            Integer resultado = getPoisson(lambda);
            int actualValue = Integer.parseInt(buffer.get(resultado.doubleValue()).toString());
            buffer.put(resultado.doubleValue(), actualValue+1);
        }
        return buffer;
    }

    public int getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;
        do {
            k++;
            p *= Math.random();
        } while (p > L);
        return k - 1;
    }
    
    public void printMap(Map buffer) {
        for (double i = 0; i < buffer.size(); i++) {
            System.out.println("Position " + i + " is " + buffer.get(i));
        }
    }
}
