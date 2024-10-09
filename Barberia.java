import java.util.concurrent.Semaphore;


public class Barberia {
    //Atributos
    private Semaphore semBarbero;
    private Semaphore semSillon;
    private Semaphore semSalida;
    private Semaphore semSillas; //Esta es la posibilidad, para reemplazar lo de las sillas
    //private Cola sillasDeEspera;
    private Cola sillasDisponibles;

    //Constructor
    public Barberia(){
        this.semBarbero=new Semaphore(0);
        this.semSillon=new Semaphore(1);
        this.semSalida=new Semaphore(0);
        this.semSillas= new Semaphore(5, true); //Aca las sillas darian permisos para sentarse, reparte hasta
        //5 permisos, con equidad.
        this.sillasDisponibles= new Cola();
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

    public Semaphore getSemSillas(){
        return this.semSillas;
    }

    public Cola getSillasDisponibles(){
        return this.sillasDisponibles;
    }

    //Propios

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
        this.semSillas.release();
    }


    public void solicitarCorte(){
        this.getSemBarbero().release();
    }


    public void esperarCorte(){
        try {
            this.getSemSalida().acquire();
        } catch (InterruptedException e) {


        }
    }


    public void liberarSillon(Cliente c){
        this.getSemSillon().release();
        if(c.equals(this.sillasDisponibles.obtenerFrente())){
            this.sillasDisponibles.sacar();
        }
    }

    //Aca verificaria el sillon, si puede se corta
    //sino solicita un permiso a la silla y se sienta
    //sino se va
    public void  verifSillon(){
        boolean sillon, silla=false;
   
            sillon= this.getSemSillon().tryAcquire(); //Aca no deberiamos hacer el cartel que diga que comenzo a cortarse?
            if(!sillon){
                silla= this.getSemSillas().tryAcquire();
                if(silla){
                    System.out.println("El cliente se sento en la silla");//igual no se si queda bien
                                                                            //
                }
            }else if(!silla){
                    System.out.println("Se jue"); //Aca por tiempos del cpu me parece que no le da para sillon setearse
                                                    //true, porque al principio siempre entra aca y no deberia.
                }
            
    }

    public boolean pedirSillon(){
        boolean sillon;
        sillon= this.getSemSillon().tryAcquire();
        return sillon;
    }

    public boolean sentarse(Cliente c){
        boolean silla;
        silla = this.sillasDisponibles.poner(c);
        return silla;
    }
   
}

