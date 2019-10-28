package com.hpblue.basesdedatos;

public class Contacto {
    int id;
    String usuario, email,telefono,fecha_nacimiento;

    public Contacto(int id, String usuario, String email, String telefono,String fecha_nacimiento){
        this.id = id;
        this.usuario = usuario;
        this.email =  email;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public Contacto(){

    }
    public int getId() {
        return id;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
