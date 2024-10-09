public class Barbero implements Runnable  {
    //Atributos
    private Barberia barberia;


    //Constructor
    public Barbero(Barberia bar){
        this.barberia=bar;
   }


   //Observadores
   public  Barberia getBarberia(){
    return this.barberia;
   }


   //Modificadores
   public void setBarberia(Barberia bar){
    this.barberia=bar;
   }


   //Propios
   public void run(){
        while(true){
            System.out.println("Clientes en cola:");
            System.out.println(this.barberia.getSillasDisponibles().toString());
            this.barberia.descansar();
            this.barberia.atender();
            this.barberia.liberar();  
        }
    }


   
}

