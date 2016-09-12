/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosaleatorios.BankSimulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Nano <nanopilo@hotmail.com>
 */
public class BankSimulation {

    public static boolean servidor1 = false; //true if is busy
    public static boolean servidor2 = false;
    public static double [] evento = new double[3];
    static double tiempo = 0;
    public static int queue = 0;

    public static int Poisson(double lambda) {
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

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        double arriveRate = 6.0;
        double departureA = 3.0;
        double departureB = 6.0;

        evento[0] = Poisson(arriveRate);
        try {
            while (tiempo < 380) {

                if (tiempo == evento[0]) {
                    bw.write("Tiempo: " + tiempo + "\tTiempo llegada de cliente\n");
                    evento[0] = Poisson(arriveRate) + tiempo; // calculamos el tiempo de llegada
                    if (servidor1 == false) {
                        servidor1 = true; //se ocupa el servidor 1
                        evento[1] = Poisson(departureA) + tiempo;
                        bw.write("Tiempo: " + tiempo + "\tAsignacion cajero A\n");

                    } else if (!servidor2) {
                        servidor2 = true;
                        evento[2] = Poisson(departureB) + tiempo;
                        bw.write("Tiempo: " + tiempo + "\tAsignacion cajero B\n");
                    }
                }

                //llegó cliente
                else if (tiempo == evento[1]) {
                    
                   // bw.write("Tiempo: " + tiempo + "Tiempo asignación cajero A");

                    servidor1 = false; //availabñe
                    bw.write("Tiempo: " + tiempo + "\tDespacho cajero A\n");
                    if (queue > 0) {
                        servidor1 = true;
                        queue--;

                        //Schedule: Departure A
                        evento[1] = Poisson(departureA) + tiempo;
                           bw.write("Re-asignacion cajero A \t"+ tiempo+"\n");
                    }
                    //despacho a
                }
                else  if (tiempo == evento[2]) {
                    bw.write("Tiempo: " + tiempo + "\tTiempo asignación cajero B\n");

                    servidor2 = false; //availabñe
                    if (queue > 0) {
                        servidor2 = true;
                        queue--;

                        //Schedule: Departure A
                        evento[2] = Poisson(departureB) + tiempo;
                         bw.write("Re-asignacion cajero B \t"+ tiempo+"\n");
                    }
                    //despacho a
                }
                bw.flush();
                tiempo += 1;
                bw.write("Tiempo\t" + tiempo + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
//
//try {
//            while (time < 480) {
//                if (time == events[0]) {
//                    bw.write("Tiempo: " + time + "\tLlego cliente\n");
//                    events[0] = Al.poisson(arriveRate) + time;
//                    if (!busyA) {
//                        busyA = true;
//                        // Schedule: Departure A
//                        events[1] = Al.poisson(departureA) + time;
//                        bw.write("Tiempo: " + time + "\tAsignacion cajero A\n");
//                        bw.write("Tardara: \t" + (events[1] - time) + "\n");
//                    } else if (!busyB) {
//                        busyB = true;
//                        // Schedule: Departure B
//                        events[2] = Al.poisson(departureB) + time;
//                        bw.write("Tiempo: " + time + "\tAsignacion cajero B\n");
//                        bw.write("Tardara: \t" + (events[1] - time) + "\n");
//                    } else {
//                        queue = +1;
//                        bw.write("Cola: \t" + queue + "\n");
//                    }
//                }
//                if (time == events[1]) {
//                    busyA = false;
//                    bw.write("Tiempo: " + time + "\tDespacho cajero A\n");
//                    if (queue > 0) {
//                        busyA = true;
//                        queue = -1;
//
//                        // Schedule: Departure A
//                        events[1] = Al.poisson(departureA) + time;
//                        bw.write("Re-asignacion cajero A\n");
//                    }
//                }
//                if (time == events[2]) {
//                    busyB = false;
//                    bw.write("Tiempo: " + time + "\tDespacho cajero B\n");
//                    if (queue > 0) {
//                        busyB = true;
//                        queue = -1;
//
//                        // Schedule: Departure A
//                        events[2] = Al.poisson(departureB) + time;
//                        bw.write("Re-asignacion B\n");
//                    }
//                }
//                bw.flush();
//                time += 1;
//                bw.write("Tiempo\t"+ time + "\n");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
