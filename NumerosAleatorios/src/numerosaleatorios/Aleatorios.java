/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosaleatorios;

/**
 *
 * @author Nano <nanopilo@hotmail.com>
 */
public class Aleatorios {

    public int Poisson(double lambda) {
        int k = 0;
        double p = 1.0;
        if (lambda < 0.0) {
            return -1;
        } else {
            double e_lambda = Math.exp(-lambda);

            do {
                k += 1;
                p *= Math.random();
            } while (p >= e_lambda);
        }

        return k - 1;
    }
    
    public int Bernoulli(double theta) {

        if (theta >= 0.0 && theta <= 1) {
            return Math.random() < 1 - theta ? 0 : 1;
        } else {
            return -1;
        }

    }

    public Aleatorios() {
    }

}
