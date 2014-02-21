/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filósofoschinos;

/**
 *
 * @author Jose
 */
public class FilósofosChinos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // leer parametros, crear vectores y buffer intermedio
        Mesa mesa = new Mesa();
        Filosofo[] filosofo = new Filosofo[5];
           
    // crear hebras
        for(int i = 0; i < filosofo.length; i++) 
            filosofo[i] = new Filosofo(mesa,i) ;
    // poner en marcha las hebras
        for(int i = 0; i < filosofo.length; i++) 
            filosofo[i].thr.start();
 
    }
    
}
