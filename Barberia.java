import java.util.concurrent.Semaphore;


public class Barberia {
    //Atributos
    private Semaphore semBarbero;
    private Semaphore semSillon;
    private Semaphore semSalida;
    //private Cola sillasDeEspera;
    private int cantSillas;
    //Constructor
    public Barberia(){
        this.semBarbero=new Semaphore(0, true);
        this.semSillon=new Semaphore(1,true);
        this.semSalida=new Semaphore(0,true);
        //this.sillasDeEspera=new Cola();
        this.cantSillas=5;
    }


    //Observadores
    public Semaphore getSemBarbero(){
        return this.semBarbero;
    }


    public Semaphore getSemSillon(){
        return this.semSillon;
    }


    public Semaphore getSemSalida(){
        return this.semSalida;
    }


    public int getCantSillas(){
        return this.cantSillas;
    }


    // public Cola getCola(){
    //     return this.sillasDeEspera;
    // }


    //Propios
    // public Cliente proximoCliente(){
    //     Cliente proximo;
    //     proximo=this.getCola().obtenerFrente();
    //     return proximo;
    // }


    public boolean verifSillon(){
        boolean verificado;
        verificado=this.getSemSillon().tryAcquire();
        return verificado;
    }


    public void descansar(){
        try{
            System.out.println("Barbero esperando");
            this.getSemBarbero().acquire();
        }catch(InterruptedException e){
           
        }
    }


    public void atender(){
        try{
            System.out.println("Barbero atendiendo");
            Thread.sleep(2000);
        }catch(InterruptedException e){
           
        }
    }


    public void liberar(){
        System.out.println("Barbero termino de atender");
        this.getSemSalida().release();
    }


    public void solicitarCorte(){
        this.getSemBarbero().release();
        this.cantSillas--;
    }


    public void esperarCorte(){
        try {
            this.getSemSalida().acquire();
        } catch (InterruptedException e) {


        }
    }


    public void liberarSillon(){
        this.getSemSillon().release();
    }
   
}

