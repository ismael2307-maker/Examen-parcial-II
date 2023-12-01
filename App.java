import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class App {
    static Calendar fecha = new GregorianCalendar();
    static int dia = fecha.get(Calendar.DAY_OF_MONTH);
    static int mes = fecha.get(Calendar.MONTH);
    static int año = fecha.get(Calendar.YEAR);
    static Scanner x = new Scanner(System.in);

    static Producto[] productos = { new Producto("Coca Cola 2 lts", 40),
            new Producto("Pepsi 2 lts", 38),
            new Producto("KolaShaler 2 lts ", 44),
            new Producto("Arroz / Libra ", 21),
            new Producto("Frijoles / Libra ", 32),
            new Producto("Azucar / Libra ", 18),
            new Producto("Carne de cerdo / Libra ", 90),
            new Producto("Carne posta de Res / Libra ", 120),
            new Producto("Pechuga / Libra ", 40),
            new Producto("Atun enlatado / unidad ", 30),
            new Producto("Azucar morena / libra ", 35)
    };

    static int[] cantidades = new int[productos.length];
    static int tf = 0;
    static int td = 0;
    static int cl = 2307;
    static String busqueda;
    static boolean comparacion;

    public static void realizarVenta() {
        System.out.println("1.-Bebidas Gaseosas 2.-Granos Basicos 3.-Carnes " /*4. Otros*/);
        int categorias = digitarOpcion(1, 4);
        /* 
        if(categorias == 4){
            System.out.println("Ingrese el producto que desea buscar: ");
            busqueda = x.nextLine();
            int i = 0;
            while (i < productos.length && productos.length !=(busqueda)) i++;
                if ( i!=productos.length) System.out.println("Se ha encontrado");
            else System.out.println("No se ha encontrado");
        }
        */

        System.out.println("Digite el producto ");
        int pro = mostrarProductos(categorias);
        System.out.println("Digite la cantidad a llevar: ");
        int cantidad = leerValor();
        cantidades[pro] += cantidad;
        tf += productos[pro].precio * cantidad;
        System.out.println("Digite la clave de la caja Registradora ");
        int clave = leerValor();

        if (clave == cl) {
            System.out.println("Ingrese la cantidad de dinero entregada por el cliente: ");
            int cd = leerValor();
            if (cd < tf) {
                System.out.println("No se pudo realizar la compra ");
            } else {
                int vu = cd - tf;
                System.out.println("Resumen de la compra ");
                System.out.println("Fecha Actual: " + dia + "/" + (mes + 1) + "/" + año);
                System.out.println("Año actual: " + año);

                System.out.println("El total de la compra fue " + tf + " Cordobas");
                System.out.println("El dinero entregado por el cliente fue " + cd + " Cordobas");
                System.out.println("El vuelto del cliente es: " + vu + " Cordobas");

                td += tf;
                tf = 0;
            }

        } else {
            System.out.println("La clave ingresadar es incorrecta ");
        }
    }

    public static int digitarOpcion(int minimo, int maximo) {
        int opcion;
        do {
            System.out.println("Digite su opcion ");
            while (!x.hasNextInt()) {
                System.out.println("Seleccione su opción:");
                x.next();
            }
            opcion = x.nextInt();
        } while (opcion < minimo || opcion > maximo);
        return opcion;

    }

    public static int leerValor() {
        int num;
        do {
            while (!x.hasNextInt()) {
                System.out.println("Ingrese un numero valido:");
                x.next();
            }
            num = x.nextInt();

        } while (num <= 0);
        return num;
    }

    public static int mostrarProductos(int categoria) {
        int inicio = 0, fin = 0;

        switch (categoria) {
            case 1: {
                inicio = 0;
                fin = 2;
                break;
            }
            case 2: {
                inicio = 3;
                fin = 5;
                break;
            }
            case 3: {
                inicio = 6;
                fin = 8;
                break;
            }
            default:
                System.out.println("No ha digitidado ninguna opcion valida ");
        }

        for (int i = inicio; i <= fin; i++) {
            System.out.println(
                    i - inicio + 1 + "." + productos[i].nombre + " - Precio: " + productos[i].precio + " Cordobas");
        }
        return digitarOpcion(1, fin - inicio + 1) + inicio - 1;

    }

    public static void resumenDia() {
        System.out.println("Fecha actual: " + dia + "/" + (mes + 1) + "/" + año);
        System.out.println("año actual: " + año);
        System.out.println("El total en ventas del dia fue de: " + td + " Cordobas");
    }

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("1.-Vender 2.-Ver total del dia y finalizar ");
            int op = digitarOpcion(1, 2);

            switch (op) {
                case 1: {
                    realizarVenta();
                    break;
                }
                case 2: {
                    resumenDia();
                }
            }
        }

    }
}