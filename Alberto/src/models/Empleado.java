package models;

public class Empleado extends Persona{
    private String usuario;
    private String contrasenia;

    public Empleado(String dni, String nombre, String usuario) {
        super(dni, nombre);
        this.usuario = usuario;
        this.contrasenia ="1234";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                "} " + super.toString();
    }
}
