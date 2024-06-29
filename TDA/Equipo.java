package TDA;

public class Equipo {

  private String nombre;
  private char categoria;
  private int puntos;
  private int partidosJugados;
  private int partidosGanados;
  private int partidosEmpatados;
  private int partidosPerdidos;
  private int golesAFavor;
  private int golesEnContra;
  private int diferenciaGoles;
  private Jugador[] jugadores;

  public Equipo(String nombre, char categoria) {
    this.nombre = nombre;
    this.categoria = categoria;
    this.puntos = 0;
    this.partidosJugados = 0;
    this.partidosGanados = 0;
    this.partidosEmpatados = 0;
    this.partidosPerdidos = 0;
    this.golesAFavor = 0;
    this.golesEnContra = 0;
    this.diferenciaGoles = 0;
    this.jugadores = new Jugador[15];
  }

  // Getters
  public String getNombre() {
    return this.nombre;
  }

  public char getCategoria() {
    return this.categoria;
  }

  public int getPuntos() {
    return this.puntos;
  }

  public int getPartidosJugados() {
    return this.partidosJugados;
  }

  public int getPartidosGanados() {
    return this.partidosGanados;
  }

  public int getPartidosEmpatados() {
    return this.partidosEmpatados;
  }

  public int getPartidosPerdidos() {
    return this.partidosPerdidos;
  }

  public int getGolesAFavor() {
    return this.golesAFavor;
  }

  public int getGolesEnContra() {
    return this.golesEnContra;
  }

  public Jugador[] getJugadores() {
    return this.jugadores;
  }

  public int getDiferenciaGoles() {
    return this.diferenciaGoles;
  }

  // Setters

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setCategoria(char categoria) {
    this.categoria = categoria;
  }

  public void setJugador(Jugador jugador) {
    try {
      int i = 0;
      boolean load = false;
      while (!load && i < jugadores.length) {
        if (jugadores[i] != null && Jugador.equals(jugadores[i], jugador)) {
          System.out.println("El jugador ya se encuentra en el equipo");
          load = true;
        } else {
          if (this.jugadores[i] == null) {
            this.jugadores[i] = jugador;
            load = true;
          }
        }
        i++;
      }
      if (!load) {
        System.out.println("No se pudo cargar el jugador");
      }
    } catch (Exception e) {
      System.out.println("Error al cargar jugador");
    }
  }

  // ! Revisar esto
  public void setPuntos(int puntos) {
    this.puntos += puntos;
  }

  public void setDiferenciaGoles() {
    this.diferenciaGoles = this.golesAFavor - this.golesEnContra;
  }

  public void setPartidosJugados(int partidosJugados) {
    this.partidosJugados += partidosJugados;
  }

  public void setPartidosGanados(int partidosGanados) {
    this.partidosGanados += partidosGanados;
  }

  public void setPartidosEmpatados(int partidosEmpatados) {
    this.partidosEmpatados += partidosEmpatados;
  }

  public void setPartidosPerdidos(int partidosPerdidos) {
    this.partidosPerdidos += partidosPerdidos;
  }

  public void setGolesAFavor(int golesAFavor) {
    this.golesAFavor += golesAFavor;
  }

  public void setGolesEnContra(int golesEnContra) {
    this.golesEnContra += golesEnContra;
  }
  // !

  // FARLOPA DE NUEVO
  public void sumarPuntos(int puntos) {
    this.puntos += puntos;
  }

  // FARLOPA DE CLASE MARADONA
  public void sumarPartidosJugados() {
    this.partidosJugados++;
  }

  public void sumarPartidosGanados() {
    this.partidosGanados++;
    this.puntos += 3;
  }

  public void sumarPartidosEmpatados() {
    this.partidosEmpatados++;
    this.puntos++;
  }

  public void sumarPartidosPerdidos() {
    this.partidosPerdidos++;
  }
}
