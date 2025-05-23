package servicios;

import models.Cliente;
import models.Persona;
import models.Vehiculo;
import utils.Constantes;
import utils.Marca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Servicios implements Iservicios {
    static ArrayList<Persona> personas = new ArrayList<>();

    static ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    public void aniadirPersona(Persona persona) {
        if (!personas.contains(persona)) {
            personas.add(persona);
        }
    }

    @Override
    public boolean aniadirVehiculo(Vehiculo vehiculo) {
        if (!vehiculos.contains(vehiculo)) {
            vehiculos.add(vehiculo);
            return true;
        }
        return false;
    }

    public boolean borrarVeh (String matricula){
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getMatricula().equals(matricula)){
                vehiculos.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Persona> devolverPersona() {
        return personas;
    }

    @Override
    public ArrayList<Vehiculo> devolverVehiculo() {
        return vehiculos;
    }

    @Override
    public ArrayList<Vehiculo> ordenarMarca(){
        Comparator<Vehiculo> veh = new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo o1, Vehiculo o2) {

                Marca marca1 = o1.getMarca();
                Marca marca2 = o2.getMarca();

                return marca1.compareTo(marca2);
            }
        };
        Collections.sort(vehiculos, veh);
        return vehiculos;
    }

    @Override
    public ArrayList<Vehiculo> ordenarAlquiMatricula(){

        ArrayList<Vehiculo> alqui = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getCliente() != null){
                alqui.add(vehiculos.get(i));
            }
        }

        Comparator<Vehiculo> veh = new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo o1, Vehiculo o2) {

                String matri1 = o1.getMatricula();
                String matri2 = o2.getMatricula();

                return matri1.compareTo(matri2);
            }
        };

        Collections.sort(alqui, veh);
        return alqui;
    }

    @Override
    public ArrayList<Vehiculo> ordenarSinAlquiMatricula(){

        ArrayList<Vehiculo> sinAlqui = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getCliente() == null){
                sinAlqui.add(vehiculos.get(i));
            }
        }

        Comparator<Vehiculo> veh = new Comparator<Vehiculo>() {
            @Override
            public int compare(Vehiculo o1, Vehiculo o2) {

                String matri1 = o1.getMatricula();
                String matri2 = o2.getMatricula();

                return matri1.compareTo(matri2);
            }
        };

        Collections.sort(sinAlqui, veh);
        return sinAlqui;
    }

    @Override
    public String alquilar(String dni, String matricula, int dias) {
        for (int i = 0; i < personas.size() ; i++) {

            if (personas.get(i).getDni().equals(dni)){
                if(personas.get(i) instanceof Cliente){
                        for (int j = 0; j < vehiculos.size(); j++) {

                        if (vehiculos.get(j).getMatricula().equals(matricula)){
                            String valor = String.valueOf(vehiculos.get(j).alquilar( (Cliente) personas.get(i), dias));
                            ( (Cliente) personas.get(i) ).setVehiculosAlquilados(vehiculos.get(j));
                            return "Se pudo alquilar " + valor;
                        }
                    }
                }
            }
        }
        return Constantes.OPCION_EQUIVOCADA;
    }

    @Override
    public String  desalquilar(String matricula) {
        for (int i = 0; i < vehiculos.size() ; i++) {
            if (vehiculos.get(i).getMatricula().equals(matricula)){
                
                vehiculos.get(i).desalquilar();
                for (int j = 0; j < personas.size(); j++) {
                    if (personas.get(j) instanceof Cliente) {
                        for (int k = 0; k < (((Cliente) personas.get(j)).getVehiculosAlquilados().size()); k++) {
                            if (((Cliente) personas.get(j)).getVehiculosAlquilados().get(k).getMatricula().equals(matricula)) {
                                ((Cliente) personas.get(j)).delVehiculosAlquilados(k);
                                return "Se desalquilo correctamente";
                            }
                        }
                    }
                }
            }

        }return Constantes.ERROR;
    }
    
}




