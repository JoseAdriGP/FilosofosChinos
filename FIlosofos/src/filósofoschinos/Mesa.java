/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fil�sofoschinos;
/**
 *
 * @author Jose
 */
import java.util.ArrayList;
import monitor.AbstractMonitor;
import monitor.Condition;

public class Mesa extends AbstractMonitor{
    private ArrayList<String> filosofos = new ArrayList<String>();
    private ArrayList<Boolean> tenedores = new ArrayList<Boolean>();
    private ArrayList<Boolean> haberComido = new ArrayList<Boolean>();
    private int numFilosofos = 0;
    private Condition sentarse;
    private Condition cogerDerecha;
    private Condition cogerIzquierda;
    
    Mesa(){
        sentarse = makeCondition();
        cogerDerecha = makeCondition();
        cogerIzquierda = makeCondition();
        filosofos.add("Confucio");
        filosofos.add("Zhuangzi");
        filosofos.add("Lao-Tsé");
        filosofos.add("Han Fei");
        filosofos.add("Mozi");
        for(int i=0; i<5; i++)
            tenedores.add(true);
        for(int i=0; i<5; i++)
            haberComido.add(false);
    }
    
    public void pensar(int filosofo){
        enter();
        System.out.println("El filosofo " + filosofos.get(filosofo) + " está filosofando sobre la futilidad de la existencia.");
        leave();
    }
    
    public void sentarseMesa(int filosofo){
        enter();
        if(numFilosofos == 4)
            sentarse.await();
        System.out.println("El filosofo " + filosofos.get(filosofo) + " se ha sentado a comer.");
        numFilosofos++;
        leave();
    }
    
    public void tomarTenedorDerecha(int filosofo) {
        enter();
        int palillo = filosofo;
        while(!tenedores.get(palillo))
            cogerDerecha.await();
        tenedores.set(palillo, Boolean.FALSE);
        System.out.println("El filosofo " + filosofos.get(filosofo) + " ha cogido el palillo de su derecha.");
        leave();
    }
    
    public void tomarTenedorIzquierda(int filosofo) {
        enter();
        int palillo = (filosofo+1)%5;
        while(!tenedores.get(palillo))
            cogerIzquierda.await();
        tenedores.set(palillo, Boolean.FALSE);
        System.out.println("El filosofo " + filosofos.get(filosofo) + " ha cogido el palillo de su izquierda.");
        leave();
    }
    
    public void comer(int filosofo) {
        enter();
        int palillo1 = filosofo;
        int palillo2 = (filosofo+1)%5;
        if(!tenedores.get(palillo1) && !tenedores.get(palillo2)){
            System.out.println("El filosofo " + filosofos.get(filosofo) + " está comiendo.");
            haberComido.set(filosofo, Boolean.TRUE);
        }
        leave();
    }
    
    public void soltarTenedorDerecha(int filosofo){
        enter();
        int palillo = filosofo;
        if(haberComido.get(filosofo))
            tenedores.set(palillo, Boolean.TRUE);
        System.out.println("El filosofo " + filosofos.get(filosofo) + " ha dejado el palillo de su derecha.");
        cogerDerecha.signal();
        leave();
    }
    
    public void soltarTenedorIzquierda(int filosofo){
        enter();
        int palillo = (filosofo+1)%5;
        if(haberComido.get(filosofo))
            tenedores.set(palillo, Boolean.TRUE);
        System.out.println("El filosofo " + filosofos.get(filosofo) + " ha dejado el palillo de su izquierda.");
        cogerIzquierda.signal();
        leave();
    }
    
    public void levantarseMesa(int filosofo){
        enter();
        System.out.println("El filosofo " + filosofos.get(filosofo) + " se levanta de la mesa.");
        haberComido.set(filosofo, Boolean.FALSE);
        numFilosofos--;
        sentarse.signal();
        leave();
    }

}
