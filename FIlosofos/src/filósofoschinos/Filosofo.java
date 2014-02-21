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

public class Filosofo implements Runnable{
    public Thread thr;
    int filosofo;
    Mesa mesa;
    
    public Filosofo(Mesa p_mesa, int p_filosofo){
        mesa = p_mesa;
        filosofo = p_filosofo;
        thr   = new Thread((Runnable) this,"Filosofo" + filosofo );
    }

    public void run(){
        while (true) {
            mesa.pensar(filosofo);
            try { 
                Thread.sleep(2000); 
            }catch (InterruptedException e) { }
            mesa.sentarseMesa(filosofo);
            
            mesa.tomarTenedorDerecha(filosofo);
            mesa.tomarTenedorIzquierda(filosofo);

            mesa.comer(filosofo);
            try { 
                Thread.sleep(2000); 
            }catch (InterruptedException e) { }
            
            mesa.soltarTenedorDerecha(filosofo);
            mesa.soltarTenedorIzquierda(filosofo);
            
            mesa.levantarseMesa(filosofo);
            try { 
                Thread.sleep(10000); 
            }catch (InterruptedException e) { }
        }
        
    }
}
