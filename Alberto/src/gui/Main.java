package gui;

import models.Cliente;
import models.Empleado;
import models.Persona;
import models.Vehiculo;
import servicios.Iservicios;
import servicios.Servicios;
import utils.Constantes;
import utils.Marca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Iservicios servicio=new Servicios();
        cargaInicialDatos(servicio);

        Scanner inicio = new Scanner(System.in);
        System.out.println("Elige una opción \n" +
                "1. Acceder a mi cuenta \n" +
                "2. Salir"
        );
        int opcion = inicio.nextInt();

        switch(opcion){

            case 1:
                System.out.println("Dime tu nombre de usuario");
                String nomUsuario = inicio.next();
                System.out.println("Dime tu contraseña");
                String contra = inicio.next();
                if(inicioSesion(nomUsuario, contra, servicio)){
                    menu(servicio);
                }
                break;
            case 2:
                System.out.println(Constantes.SALIR_MENU);
                break;
        }

    }

    private static boolean inicioSesion(String nomUsuario, String contra, Iservicios servicio) {
        for (int i = 0; i < servicio.devolverPersona().size(); i++) {
        if(servicio.devolverPersona().get(i) instanceof Empleado){
             if(((Empleado) servicio.devolverPersona().get(i)).getUsuario().equals(nomUsuario)){
                if(((Empleado) servicio.devolverPersona().get(i)).getContrasenia().equals(contra)){
                   return true;
               }
            }
        }
        }
        return false;
    }

    private static void mostrarMenu(){
        System.out.println(
                "1. Añadir Vehículo.\n" +
                "2. Eliminar Vehículo.\n" +
                "3. Mostrar Todos\n" +
                "4. Mostrar Alquilados\n" +
                "5. Mostrar No Alquilados\n" +
                "6. Alquilar\n" +
                "7. Desalquilar\n" +
                "8. Mostrar Clientes\n" +
                "9. Salir.");
    }
    private static void cargaInicialDatos(Iservicios servicio){

        Persona p1=new Cliente("111111111A","Juan Lopez","Villanueva de la Canñada");
        Persona p2=new Cliente( "22222222B"," Marina Pinteño","Cádiz");
        Persona p3=new Cliente("33333333C","Amanda Gárcia","Valladolid");
        Persona p4=new Cliente("44444444D","Rafael Caro","Devon");
        Persona p5=new Cliente("55555555E","Manuel Ceballos","San Lúcar");
        Persona p6=new Empleado("12345678A","Ana Iglesias","profesorAna");
        Persona p7=new Empleado("01941075J","Alberto Gonzalez","");
        servicio.aniadirPersona(p1);
        servicio.aniadirPersona(p2);
        servicio.aniadirPersona(p3);
        servicio.aniadirPersona(p4);
        servicio.aniadirPersona(p5);
        servicio.aniadirPersona(p6);
        servicio.aniadirPersona(p7);


        Vehiculo v1 = new Vehiculo("1111AAA", Marca.Audi, "A5", "Negro", 200.0);
        Vehiculo v2 = new Vehiculo("2222BBB", Marca.Seat, "Ibiza", "Gris", 100.0);
        Vehiculo v3 = new Vehiculo("3333CCC", Marca.Ford, "Focus", "Azul", 75.5);
        Vehiculo v4 = new Vehiculo("4444DDD", Marca.Volkswagen, "Polo", "Blanco", 60.5);
        servicio.aniadirVehiculo(v1);
        servicio.aniadirVehiculo(v2);
        servicio.aniadirVehiculo(v3);
        servicio.aniadirVehiculo(v4);
    }

    public static void menu(Iservicios iservicios){
        final int SALIR = 9;
        Scanner sc=new Scanner(System.in);
        int opcionSeleccionada;

        do {
            mostrarMenu();
            opcionSeleccionada = sc.nextInt();

            switch (opcionSeleccionada) {
                case 1:
                    System.out.println(anadirVehiculoNuevo(iservicios, sc));
                    break;
                case 2:
                    eliminarVehiculo(iservicios, sc);
                    break;
                case 3:
                    ordenarVehMarca(iservicios);
                    break;
                case 4:
                    ordenarAlquiMatri(iservicios);
                    break;
                case 5:
                    ordenarSinAlquiMatri(iservicios);
                    break;
                case 6:
                    alquilarVh(iservicios, sc);
                    break;
                case 7:
                    desalquilarVeh(iservicios,sc);
                    break;
                case 8:
                    mostrarCLientesConVehiculosAlquilados(iservicios);
                    break;
                case 9:
                    System.out.println(Constantes.SALIR_MENU);
                    break;
                default:
                    System.out.println(Constantes.OPCION_EQUIVOCADA);
            }

        } while (opcionSeleccionada != SALIR);
    }

        private static String anadirVehiculoNuevo(Iservicios servicio, Scanner sc){

        System.out.println("Dime la matricula del coche");
        String matricula = sc.next();
        //Guardar coche con el constructor pequeño y aplicar el equals para comprobar las matriculas
        System.out.println("Elige que marca quieres  \n " +
                "1.- Audi \n" +
                "2.- Alfa Romeo \n" +
                "3.- BMW \n" +
                "4.- Citroen \n" +
                "5.- Ford \n" +
                "6.- Peugeot \n" +
                "7.- KIA \n" +
                "8.- Dacia \n" +
                "9.- VOLKSWAGEN \n" +
                "10.- Seat \n" +
                "11.- Mercedes");
        int marcas= sc.nextInt();
        Marca marca =  elegirMarca(marcas);
        System.out.println("Dime el modelo del vehiculo");
        String modelo = sc.next();
        System.out.println("Dime el color del vehiculo");
        String color = sc.next();
        System.out.println("Dime el precio sin iva");
        double precioSinIva = sc.nextDouble();

        if (servicio.aniadirVehiculo(new Vehiculo(matricula, marca, modelo, color, precioSinIva))){
            return Constantes.VALIDO;
        } else {
            return Constantes.ERROR_V;
        }

 }


    private static Marca elegirMarca(int marcas) {
        Marca marca = null;
        switch (marcas){
            case 1:
                marca = Marca.Audi;
                break;
            case 2:
                marca = Marca.AlfaRomeo;
                break;
            case 3:
                marca = Marca.BMW;
                break;
            case 4:
                marca = Marca.Citroen;
                break;
            case 5:
                marca =Marca.Ford;
                break;
            case 6:
                marca = Marca.Peugeot ;
                break;
            case 7:
                marca =Marca.KIA;
                break;
            case 8:
                marca =Marca.Dacia;
                break;
            case 9:
                marca =Marca.Volkswagen;
                break;
            case 10:
                marca =Marca.Seat;
                break;
            case 11:
                marca =Marca.Mercedes;
                break;
        }

        return marca;
    }

    private static String eliminarVehiculo(Iservicios servicio, Scanner sc) {
        System.out.println("Dime la matricula del coche");
        String matricula = sc.next();
        if (servicio.borrarVeh(matricula)){
            return "Se elimino correctamente";
        } else {
            return "No se encontro la matricula";
        }
    }
    private static void ordenarVehMarca(Iservicios servicio){
        for (int i = 0; i < servicio.ordenarMarca().size(); i++) {
            System.out.println(servicio.ordenarMarca().get(i).toString());
        }
    }

    private static void  ordenarAlquiMatri (Iservicios servicio){
        for (int i = 0; i < servicio.ordenarAlquiMatricula().size(); i++) {
            System.out.println(servicio.ordenarAlquiMatricula().get(i).toString());
        }
    }

    private static void ordenarSinAlquiMatri(Iservicios servicio){
        for (int i = 0; i < servicio.ordenarSinAlquiMatricula().size(); i++) {
            System.out.println(servicio.ordenarSinAlquiMatricula().get(i).toString());
        }
    }

    private static void alquilarVh (Iservicios servicio, Scanner sc){

        System.out.println("Dime el dni del la persona");
        String dni = sc.next();
        System.out.println("Dime el matricula del vehiculo");
        String matricula = sc.next();
        System.out.println("Dime el numero de dias a alquilar");
        int diasAlqui = sc.nextInt();

        System.out.println(servicio.alquilar(dni, matricula,diasAlqui));
    }
    private static void desalquilarVeh(Iservicios servicio,Scanner sc) {
        System.out.println("Dime el matricula del vehiculo");
        String matricula = sc.next();
        System.out.println(servicio.desalquilar(matricula));
    }
    private static void mostrarCLientesConVehiculosAlquilados(Iservicios servicio){
        ArrayList<Persona> persona = servicio.devolverPersona();
        for (int i = 0; i < persona.size(); i++) {
            if (persona.get(i) instanceof Cliente){
                System.out.println(persona.get(i));
                ArrayList <Vehiculo> cochesCliente = ( (Cliente) persona.get(i) ).getVehiculosAlquilados();
                for (int j = 0; j < cochesCliente.size(); j++) {
                    System.out.println(cochesCliente.get(j));
                }
            }


        }
    }

}