package models;
import java.util.ArrayList;
public class Cliente extends Persona{
        private String ciudad;
        private ArrayList<Vehiculo> vehiculosAlquilados;

        // Constructor
        public Cliente(String dni, String nombre, String ciudad) {
            super(dni, nombre);
            this.ciudad = ciudad;
            this.vehiculosAlquilados = new ArrayList<>();
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public ArrayList<Vehiculo> getVehiculosAlquilados() {
            return vehiculosAlquilados;
        }

        public void setVehiculosAlquilados(Vehiculo vehiculosAlquilados) {
            this.vehiculosAlquilados.add(vehiculosAlquilados);
        }

        @Override
        public String toString() {
            String info = super.toString() + " vive en " + ciudad;
            if (vehiculosAlquilados.isEmpty()) {
                info += " y no tiene vehículos alquilados.";
            } else {
                info += " y ha alquilado " + vehiculosAlquilados.size() + " coches que son: ";
                for (Vehiculo vehiculo : vehiculosAlquilados) {
                    info += "- " + vehiculo.getMatricula() + " ";
                }
            }
            return info;
        }
    public void delVehiculosAlquilados(int pos) {
        vehiculosAlquilados.remove(pos);
    }
}


