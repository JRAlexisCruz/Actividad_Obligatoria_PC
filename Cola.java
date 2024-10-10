public class Cola {

    private Cliente[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 6;

    // Crea y devuelve una cola vacía.
    public Cola() {
        this.arreglo = new Cliente[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    /*
     * Pone el elemento al final de la cola. Devuelve verdadero si el elemento
     * se pudo agregar en la estructura y falso en caso contrario. Siempre se
     * deja la última celda del arreglo vacía, por eso verifica que la siguiente
     * celda después del final sea distinto del frente para poner un nuevo elemento.
     */
    public boolean poner(Cliente nuevoElem) {
        boolean exito = false;
        boolean estaLlena = ((this.fin + 1) % TAMANIO) == this.frente; // Si está llena = true, si no, false.

        if (!estaLlena) {
            // Cuando la cola no está llena, agrego elemento en fin y actualizo posicion fin
            this.arreglo[this.fin] = nuevoElem;
            // arreglo circular, si llegué al final (TAMANIO-1) y el sig es TAMANIO, vuelvo
            // a posición 0
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;
        }

        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }

        return exito;
    }

    // Devuelve el elemento del frente de la cola. La cola no debe ser vacía.
    public Cliente obtenerFrente() {
        Cliente elem = null;

        if (!this.esVacia()) {
            elem = this.arreglo[this.frente];
        }

        return elem;
    }

    // Verifica si la cola está vacía
    public boolean esVacia() {
        return this.frente == this.fin;
    }

    // Vacía la cola desde el frente hasta el fin. Cola vacía: frente = fin
    public void vaciar() {
        while (this.frente != this.fin) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
    }

    @Override
    public Cola clone() {
        /*
         * Devuelve una copia exacta de los datos de la estructura original, y
         * respetando el orden de los mismos en otra estructura del mismo tipo.
         */

        Cola clon = new Cola();
        // Manipula la estructura de manera directa y eficiente.
        if (!this.esVacia()) {
            int aux = this.frente;
            while (aux != this.fin) {
                clon.arreglo[aux] = this.arreglo[aux];
                aux = (aux + 1) % TAMANIO;
            }
        }
        clon.frente = this.frente;
        clon.fin = this.fin;

        return clon;
    }

    @Override
    public String toString() {
        int i = this.frente;
        String cadena = "";

        if (this.esVacia()) {
            cadena = "[]";
        } else {
            cadena = "["; // Comienzo con un corchete
            while (((i + 1) % TAMANIO) != this.fin) { // Recorro hasta el anteúltimo elemento y coloco una coma al final
                cadena += this.arreglo[i].getId();
                i = (i + 1) % TAMANIO;
                cadena += ",";
            }
            cadena += this.arreglo[i].getId() + "]"; // Agrego el último elemento y el corchete de cierre
        }

        return cadena;
    }
}