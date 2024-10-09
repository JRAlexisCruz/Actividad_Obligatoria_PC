public class Cliente implements Runnable{
    //Atributos
    private Barberia barberia;
    private int id;


    //Constructor
    public Cliente(Barberia bar, int id){
         this.barberia=bar;
         this.id=id;
    }//pruebita lucas


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
    public boolean equals(Cliente c){
        boolean equals=false;
        if(c!=null){
            equals=this.id==c.getId();
        }
        return equals;
    }

    public void run(){
        boolean esperando = true;
        boolean yaEntro = false;
        while(esperando){
            boolean sillonDisponible=false;
            if(this.barberia.getSillasDisponibles().esVacia()){
                sillonDisponible=this.barberia.pedirSillon();
            }else if(yaEntro){
                if(this.equals(this.barberia.getSillasDisponibles().obtenerFrente())){
                    sillonDisponible=this.barberia.pedirSillon();
                }
            }
            if(sillonDisponible){
                System.out.println("Cliente "+this.id+" cortandose el pelo");
                this.barberia.solicitarCorte();
                this.barberia.esperarCorte();
                this.barberia.liberarSillon(this);
                System.out.println("Cliente "+this.id+" termino");
                esperando=false;
            }else if(!yaEntro){
                esperando=this.barberia.sentarse(this);
                if(esperando){
                    System.out.println("Cliente "+this.id+" esperando en una silla");
                }else{
                    System.out.println("Cliente "+this.id+" se fue");
                }
                yaEntro=true;
            }
        }
    }
}

