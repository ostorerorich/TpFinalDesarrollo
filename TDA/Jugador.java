package TDA;

public class Jugador {

  private String apellido;
  private String nombre;
  private int edad;
  private int dni;
  private int nroCamiseta;
  private String equipo;
  private int goles;

  // Constructores

  public Jugador(String apellido, String nombre, int edad, int dni, int nroCamiseta, String equipo) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.edad = edad;
    this.dni = dni;
    this.nroCamiseta = nroCamiseta;
    this.equipo = equipo;
    this.goles = 0;
  }

  // Setter's
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public void setDni(int dni) {
    this.dni = dni;
  }

  public void setNroCamiseta(int nroCamiseta) {
    this.nroCamiseta = nroCamiseta;
  }

  public void setEquipo(String equipo) {
    this.equipo = equipo;
  }

  public void setGoles(int goles) {
    this.goles += goles;
  }

  // Getter's

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public int getEdad() {
    return edad;
  }

  public int getDni() {
    return dni;
  }

  public int getNroCamiseta() {
    return nroCamiseta;
  }

  public String getEquipo() {
    return equipo;
  }

  public int getGoles() {
    return goles;
  }

  // Metodos
  public static boolean equals(Jugador jugador, Jugador jugador2) {
    return jugador2.getDni() == jugador.getDni();
  }

  public String toString() {
    return "(" + this.apellido + ", " + this.nombre + ", " + this.edad + ", " + this.dni + ", " + this.nroCamiseta
        + ", " + this.equipo + ", " + this.goles + ")";
  }
}
