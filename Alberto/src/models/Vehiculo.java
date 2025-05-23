package models;

import utils.Marca;

import java.util.Objects;

public class Vehiculo {
    private String matricula;
    private Marca marca;
    private String modelo;
    private String color;
    private double precioDia;
    private boolean alquiler;
    private Cliente cliente;
    private int numDias;

    public Vehiculo(String matricula, Marca marca, String modelo, String color, double precioDia) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioDia = precioDia;
        this.alquiler =false;
    }

    public String getMatricula() {
        return matricula;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public boolean isAlquiler() {
        return alquiler;
    }

    public void setAlquiler(boolean alquiler) {
        this.alquiler = alquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumDias() {
        return numDias;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    public double alquilar(Cliente cliente, int dias){
        setAlquiler(true);
        setNumDias(dias);
        setCliente(cliente);
        return this.precioDia * 1.21;
    }
    public void desalquilar(){
        setAlquiler(false);
        setNumDias(0);
        setCliente(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return matricula.equals(vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
      if (this.alquiler==false){

          return "vehiculo: matricula: " + this.matricula + ", marca: " + this.marca + ", modelo: " + this.modelo + ",color:" + this.color
                  + ", precio sin IVA: " + this.precioDia +", esta alquilado: " + this.alquiler + ", informacion cliente: ninguno " + ", numero de dias alquilado: 0 ";
      }
      else {
          return "vehiculo: matricula: " + this.matricula + ", marca: " + this.marca + ", modelo: " + this.modelo + ",color:" + this.color
                  + ", precio sin IVA: " + this.precioDia +", esta alquilado: " + this.alquiler + ", informacion cliente: " +
                  this.cliente.getNombre() + " " + this.cliente.getDni() + ", numero de dias alquilado: " + this.numDias;

      }
    }
}
