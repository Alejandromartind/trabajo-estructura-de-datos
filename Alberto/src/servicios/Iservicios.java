package servicios;

import models.Persona;
import models.Vehiculo;

import java.util.ArrayList;

public interface Iservicios  {
    public void aniadirPersona(Persona persona);

    public boolean aniadirVehiculo(Vehiculo vehiculo);

    public ArrayList<Persona> devolverPersona();

    public ArrayList<Vehiculo> devolverVehiculo();

    public ArrayList<Vehiculo> ordenarMarca();

    public ArrayList<Vehiculo> ordenarAlquiMatricula();

    public boolean borrarVeh (String matricula);

    public String alquilar(String dni, String matricula, int dias);

    public ArrayList<Vehiculo> ordenarSinAlquiMatricula();

    String desalquilar(String matricula);
}
