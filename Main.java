public class Main {
    public static void main(String[] args) {


        Barberia barberia = new Barberia();
        Barbero b;
        Cliente c[];
        Thread[] tc;
        Thread tb;
        int i;
        int t;


        b= new Barbero(barberia);
        c= new Cliente[10];
        tc= new Thread[10];
        tb= new Thread(b);


        for(i=0; i<10; i++){
            c[i]= new Cliente(barberia, i);
            tc[i]= new Thread(c[i], "Cliente: "+ i);
        }
       
        tb.start();
        for(t=0; t< c.length; t++){
            tc[t].start();
        }

    }

}
