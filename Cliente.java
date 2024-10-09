public class Cliente implements Runnable{
    //Atributos
    private Barberia barberia;
    private int id;


    //Constructor
    public Cliente(Barberia bar, int id){
         this.barberia=bar;
         this.id=id;
    }


    //Observadores
    public  Barberia getBarberia(){
        return this.barberia;
    }
   
    public int getId(){
        return this.id;
    }


    //Modificadores
    public void setBarberia(Barberia bar){
    this.barberia=bar;
    }
   
    public void setId(int id){
        this.id=id;
    }


    //Propios
    public void run(){
        if(this.barberia.verifSillon()){
            System.out.println("Cliente "+this.id+" cortandose el pelo");
            this.barberia.solicitarCorte();
            this.barberia.esperarCorte();
            this.barberia.liberarSillon();
            System.out.println("Cliente "+this.id+" termino");
        }
    }
}

