import java.util.Scanner;

import TDA.Equipo;
import TDA.Jugador;

public class Metodos {
  private final static Scanner sc = new Scanner(System.in);

  public static void cargarMenu(Equipo[][] fixture, int[][] resultados, Jugador[] jugadores, Equipo[] equipos) {
    boolean exec = true;
    System.out.println(
        "--> Selecciona una opcion valida: \n1 - Mostrar fixture en pantalla\n2 - Cargar fecha\n3 - Mostrar fechas cargadas\n4 - Tabla de goleadores \n5 - Ver tabla de posiciones\n6 - Ingresar nuevo jugador\n7 - Mostrar jugador\n8 - Promedio de edad de jugadores\n9 - Lista de jugadores\n10 - Buscar Jugador por equipo y edad\n 0 - Salir.");
    try {
      int respuesta = Integer.parseInt(sc.nextLine());
      switch (respuesta) {
        case 0:
          exec = false;
          break;
        case 1:
          mostrarFixture(fixture);
          break;
        case 2:
          cargarResultados(fixture, resultados, jugadores);
          break;
        case 3:
          mostrarFechas(resultados, fixture);
          break;
        case 4:
          mostrarTabla(jugadores);
          break;
        case 5:
          verTablaDePosiciones(equipos);
          break;
        case 6:
          System.out.println("Ingresar nuevo jugador");
          String[] datos = new String[6];
          System.out.println("Ingresar apellido: ");
          datos[0] = sc.nextLine();
          System.out.println("Ingresar nombre: ");
          datos[1] = sc.nextLine();
          System.out.println("Ingresar edad: ");
          datos[2] = sc.nextLine();
          System.out.println("Ingresar dni: ");
          datos[3] = sc.nextLine();
          System.out.println("Ingresar nro camiseta: ");
          datos[4] = sc.nextLine();
          System.out.println("Ingresar nombre equipo: ");
          datos[5] = sc.nextLine();
          if (buscarJugador(jugadores, Integer.parseInt(datos[3]))) {
            System.out.println("El jugador ya se encuentra en el equipo");
          } else {
            int equipoPos = buscarPosicionEquipo(equipos, datos[5]);
            Jugador jugadorACargar = new Jugador(datos[0].toLowerCase(), datos[1].toLowerCase(),
                Integer.parseInt(datos[2]),
                Integer.parseInt(datos[3]),
                Integer.parseInt(datos[4]), datos[5].toLowerCase());
            int posNull = posicionNull(jugadores);
            if (equipoPos != -1 && posNull != -1) {
              jugadores[posNull] = jugadorACargar;
              cargarJugador(equipos, jugadorACargar);
            } else {
              System.out.println("No se encontro el equipo");
            }
          }
          break;
        case 7:
          mostrarJugador(equipos);
          break;
        case 8:
          int prom = edadPromedio(jugadores, 0, 0);
          System.out.println("Edad promedio de los jugadores: " + prom);
          break;
        case 9:
          try {
            System.out.println(
                "1 - Bubble sort por apellido y nombre - Metodo mas eficiente \n2 - Selection sort por apellido y nombre - Metodo menos eficiente");
            int opcion = Integer.parseInt(sc.nextLine());
            if (opcion != 1 && opcion != 2) {
              throw new NumberFormatException();
            }
            mostrarJugadores(jugadores, opcion);
          } catch (NumberFormatException e) {
            System.out.println("Opcion invalida");
          }
          break;
        case 10:
          mostrarJugadorMenor(equipos);
          break;
        default:
          System.out.println("Opcion invalida");
          break;
      }
    } catch (NumberFormatException e) {
      System.out.println("Opcion invalida");
    }

    if (exec)
      cargarMenu(fixture, resultados, jugadores, equipos);

  }

  public static void mostrarJugadorMenor(Equipo[] equipos) {
    System.out.println("Ingresar nombre del equipo: ");
    String nombreEquipo = sc.nextLine();
    System.out.println("Ingresar edad: ");
    int edad = Integer.parseInt(sc.nextLine());
    int pos = buscarPosicionEquipo(equipos, nombreEquipo);
    if (pos != -1) {
      Jugador encontrado = jugadorMenor(equipos[pos].getJugadores(), edad, 0);
      if (encontrado != null) {
        System.out.println(String.format("%-15s %-15s %10s %10s %10s %10s %10s", "Apellido", "Nombre", "Edad", "DNI",
            "Nro Camiseta", "Equipo", "Goles"));
        System.out.println(String.format("%-15s %-15s %10d %10d %10d %10s %10d", encontrado.getApellido(),
            encontrado.getNombre(), encontrado.getEdad(), encontrado.getDni(), encontrado.getNroCamiseta(),
            encontrado.getEquipo(), encontrado.getGoles()));
      } else {
        System.out.println("No se encontro el jugador");
      }
    } else {
      System.out.println("No se encontro el equipo");
    }
  }

  public static Jugador jugadorMenor(Jugador[] jugadores, int edad, int i) {
    Jugador encontrado = null;
    if (jugadores[i] == null || i == jugadores.length) {
      if (jugadores[i] != null) {
        if (jugadores[i].getEdad() < edad) {
          encontrado = jugadores[i];
        } else {
          encontrado = jugadorMenor(jugadores, edad, i + 1);
        }
      }
    } else {
      if (jugadores[i].getEdad() < edad) {
        encontrado = jugadores[i];
      } else {
        encontrado = jugadorMenor(jugadores, edad, i + 1);
      }
    }
    return encontrado;
  }

  public static void mostrarJugador(Equipo[] equipos) {
    System.out.println("Ingresar numero de camiseta: ");
    int camiseta = Integer.parseInt(sc.nextLine());
    System.out.println("Ingresar nombre del equipo: ");
    String nombreEquipo = sc.nextLine();
    int pos = buscarPosicionEquipo(equipos, nombreEquipo);
    if (pos != -1) {
      int posJugador = buscarJugadorCamiseta(equipos[pos].getJugadores(), camiseta);
      if (posJugador != -1) {
        Jugador encontrado = equipos[pos].getJugadores()[posJugador];
        System.out.println(encontrado.getApellido() + " "
            + encontrado.getNombre() + " " + encontrado.getEdad() + " " + encontrado.getDni() + " "
            + encontrado.getNroCamiseta() + " " + encontrado.getEquipo() + " " + encontrado.getGoles());
      } else {
        System.out.println("No se encontro el jugador");
      }
    } else {
      System.out.println("No se encontro el equipo");
    }
  }

  public static int buscarPosicionEquipo(Equipo[] equipos, String nombre) {
    int pos = -1;
    int i = 0;
    boolean encontrado = false;
    while (i < equipos.length && !encontrado) {
      if (equipos[i] != null && equipos[i].getNombre().equalsIgnoreCase(nombre)) {
        pos = i;
        encontrado = true;
      }
      i++;
    }
    return pos;
  }

  public static void cargarResultados(Equipo[][] fixture, int[][] resultados, Jugador[] jugadores) {
    try {
      System.out.println("Ingresar numero de fecha valido: ");
      int fecha = Integer.parseInt(sc.nextLine()) - 1;
      if (fecha < fixture.length && fecha >= 0) {
        // en el caso que la columna 4 sea 0, significa que no se ingresaron los
        // resultados, caso contrario para -1
        if (resultados[fecha][8] != -1) {
          cargarFecha(jugadores, fixture, resultados, fecha);
        } else {
          System.out.println("Ya se ingresaron los resultados de esta fecha");
        }
      }

    } catch (Exception e) {
      System.out.println("  === Error al cargar resultados ===");
    }
  }

  public static void cargarFecha(Jugador[] jugadores, Equipo[][] fixture, int[][] resultados, int fecha) {
    System.out.println("Ingresar resultados de la fecha " + (fecha + 1) + ": ");
    int i = 0;
    while (i < fixture[fecha].length - 1) {
      try {
        System.out.print("\nIngresar goles del equipo " + fixture[fecha][i].getNombre() + ": ");
        int golesLocal = Integer.parseInt(sc.nextLine());
        System.out.print("\nIngresar goles del equipo " + fixture[fecha][i + 1].getNombre() + ": ");
        int golesVisitante = Integer.parseInt(sc.nextLine());

        if (golesLocal < 0 || golesVisitante < 0) {
          throw new NumberFormatException();
        } else if (golesLocal == golesVisitante) {
          fixture[fecha][i].sumarPartidosEmpatados();
          fixture[fecha][i + 1].sumarPartidosEmpatados();

        } else if (golesLocal > golesVisitante) {
          fixture[fecha][i].sumarPartidosGanados();
        } else {
          fixture[fecha][i + 1].sumarPartidosGanados();
        }
        if (golesLocal >= 0 && golesVisitante >= 0) {

          resultados[fecha][i] = golesLocal;
          // ! Local a visitante
          fixture[fecha][i].setGolesAFavor(golesLocal);
          fixture[fecha][i + 1].setGolesEnContra(golesLocal);
          cargarGoles(fixture[fecha][i], golesLocal);
          // ! Visitante a local
          resultados[fecha][i + 1] = golesVisitante;
          fixture[fecha][i + 1].setGolesAFavor(golesVisitante);
          fixture[fecha][i].setGolesEnContra(golesVisitante);
          cargarGoles(fixture[fecha][i + 1], golesVisitante);

          fixture[fecha][i].setDiferenciaGoles();
          fixture[fecha][i + 1].setDiferenciaGoles();

          fixture[fecha][i].setPartidosJugados();
          fixture[fecha][i + 1].setPartidosJugados();
          i += 2;
        } else {
          throw new NumberFormatException();
        }

      } catch (NumberFormatException e) {
        System.out.println("Error al ingresar goles");
      }
      resultados[fecha][8] = -1;
    }
  }

  public static void cargarGoles(Equipo equipo, int goles) {
    Jugador[] jugadoresEquipo = equipo.getJugadores();

    int golesTotales = 0;
    while (golesTotales < goles) {
      try {
        System.out
            .print("\nIngresar el numero de camiseta del jugador de " + equipo.getNombre() + " que hizo el gol: ");
        int camiseta = Integer.parseInt(sc.nextLine());
        int pos = buscarJugadorCamiseta(jugadoresEquipo, camiseta);
        boolean existe = pos != -1;

        if (existe) {
          jugadoresEquipo[pos].setGoles(1);
          golesTotales++;
        } else {
          System.out.println("No se encontro el jugador con la camiseta " + camiseta);
        }

      } catch (NumberFormatException e) {
        System.out.println("No es un numero valido");
      }
    }

  }

  public static boolean buscarJugador(Jugador[] jugadores, int dni) {
    boolean existe = false;
    int i = 0;
    while (!existe && i < jugadores.length) {
      if (jugadores[i] != null) {
        if (jugadores[i].getDni() == dni) {
          existe = true;
        }
      }
      i++;
    }
    return existe;
  }

  public static int buscarJugadorCamiseta(Jugador[] jugadores, int camiseta) {
    boolean existe = false;
    int i = 0;
    int pos = -1;
    while (!existe && i < jugadores.length) {
      if (jugadores[i] != null && jugadores[i].getNroCamiseta() == camiseta) {
        existe = true;
        pos = i;
      }
      i++;
    }
    return pos;
  }

  public static int posicionNull(Jugador[] jugadores) {
    int i = 0;
    int pos = 0;
    boolean encontrado = false;
    while (i < jugadores.length && !encontrado) {
      if (jugadores[i] == null) {
        encontrado = true;
        pos = i;
      }
      i++;
    }
    if (!encontrado) {
      pos = -1;
    }

    return pos;
  }

  public static void cargarJugador(Equipo[] equipos, Jugador jugador) {
    boolean load = false;
    int i = 0;

    while (!load && i < equipos.length) {
      if (equipos[i] != null) {
        if (jugador.getEquipo().equalsIgnoreCase(equipos[i].getNombre())) {
          equipos[i].setJugador(jugador);
          load = true;
        }
        i++;
      }
    }
  }

  public static Equipo[][] generarFixture(Equipo[] equipos) {
    Equipo[][] fixture = new Equipo[7][8];
    int cantEquipos = equipos.length;
    int cantFechas = cantEquipos - 1;
    for (int fecha = 0; fecha < cantFechas; fecha++) {
      for (int j = 0; j < 8; j = j + 2) {
        int local = (fecha + j) % (cantEquipos - 1);
        int visitante = (cantEquipos - 1 - j + fecha) % (cantEquipos - 1);
        if (j == 0) {
          visitante = cantEquipos - 1;
        }
        fixture[fecha][j] = equipos[local];
        fixture[fecha][j + 1] = equipos[visitante];
      }
    }

    return fixture;
  }

  public static void mostrarFixture(Equipo[][] fixture) {
    System.out.println("\n ----> Fixture");

    for (int i = 0; i < fixture.length; i++) {
      System.out.println(" ----> Fecha " + (i + 1) + " <---- ");
      for (int j = 0; j < fixture[i].length; j += 2) {
        System.out.println(fixture[i][j].getNombre() + " vs " + fixture[i][j + 1].getNombre());

      }
      System.out.println("---------------");
    }
  }

  public static void ordenarTabla(Jugador[] jugadores) {
    for (int i = 0; i < jugadores.length; i++) {
      for (int j = 0; j < jugadores.length - 1; j++) {
        if (jugadores[j] != null && jugadores[j + 1] != null) {
          if (jugadores[j].getGoles() < jugadores[j + 1].getGoles()) {
            Jugador aux = jugadores[j];
            jugadores[j] = jugadores[j + 1];
            jugadores[j + 1] = aux;
          }
        }
      }
    }
  }

  public static void mostrarTabla(Jugador[] jugadores) {
    ordenarTabla(jugadores);
    System.out.println("  Tabla de goleadores");
    System.out.println(String.format("%-15s %-15s %10s%n", "Jugador", "Equipo", "Goles"));
    for (int i = 0; i < 10; i++) {
      if (jugadores[i] != null) {
        System.out
            .println(String.format("%-15s %-15s %10s", jugadores[i].getNombre(), jugadores[i].getEquipo(),
                jugadores[i].getGoles()));
      }
    }
  }

  public static void ordenarBubble(Jugador[] jugadores) {
    for (int i = 0; i < jugadores.length; i++) {
      for (int j = 0; j < jugadores.length - 1; j++) {
        if (jugadores[j] != null && jugadores[j + 1] != null) {
          if (jugadores[j].getApellido().compareTo(jugadores[j + 1].getApellido()) > 0) {
            Jugador aux = jugadores[j];
            jugadores[j] = jugadores[j + 1];
            jugadores[j + 1] = aux;
          } else if (jugadores[j].getApellido().compareTo(jugadores[j + 1].getApellido()) == 0) {
            if (jugadores[j].getNombre().compareTo(jugadores[j + 1].getNombre()) > 0) {
              Jugador aux = jugadores[j];
              jugadores[j] = jugadores[j + 1];
              jugadores[j + 1] = aux;
            }
          }
        }
      }
    }
  }

  public static void ordenarSelection(Jugador[] jugadores) {
    for (int i = 0; i < jugadores.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < jugadores.length; j++) {
        if (jugadores[j] != null && jugadores[minIndex] != null) {
          int apellidoComparison = jugadores[j].getApellido().compareTo(jugadores[minIndex].getApellido());
          if (apellidoComparison < 0
              || (apellidoComparison == 0 && jugadores[j].getNombre().compareTo(jugadores[minIndex].getNombre()) < 0)) {
            minIndex = j;
          }
        }
      }
      if (minIndex != i) {
        Jugador aux = jugadores[i];
        jugadores[i] = jugadores[minIndex];
        jugadores[minIndex] = aux;
      }
    }
  }

  public static void mostrarJugadores(Jugador[] jugadores, int opcion) {
    if (opcion == 1) {
      ordenarBubble(jugadores);
    } else if (opcion == 2) {
      ordenarSelection(jugadores);
    }
    System.out.println(String.format("%-15s %-15s %10s%n", "Apellido", "Nombre", "Equipo"));
    for (int i = 0; i < jugadores.length; i++) {
      if (jugadores[i] != null) {
        System.out.println(String.format("%-15s %-15s %10s", jugadores[i].getApellido(), jugadores[i].getNombre(),
            jugadores[i].getEquipo()));
      }
    }
  }

  public static void mostrarFechas(int[][] resultados, Equipo[][] fixture) {
    for (int i = 0; i < fixture.length; i++) {
      if (resultados[i][8] == -1) {
        System.out.println(" ---> Fecha " + (i + 1) + " <----");
        for (int j = 0; j < fixture[i].length; j++) {
          System.out.println("-->" + fixture[i][j].getNombre() + " " + resultados[i][j] + " - " + resultados[i][j + 1]
              + " " + fixture[i][j + 1].getNombre());
          j++;
        }
      } else {
        System.out.println("Fecha " + (i + 1) + " no se ingresaron los resultados");
      }

    }
  }

  public static void ordenarEquipos(Equipo[] equipos) {
    for (int i = 0; i < equipos.length; i++) {
      for (int j = 0; j < equipos.length - 1; j++) {
        if (equipos[j].getPuntos() > equipos[j + 1].getPuntos()) {
          Equipo aux = equipos[j];
          equipos[j] = equipos[j + 1];
          equipos[j + 1] = aux;
        } else if (equipos[j].getPuntos() == equipos[j + 1].getPuntos()) {
          if (equipos[j].getDiferenciaGoles() > equipos[j + 1].getDiferenciaGoles()) {
            Equipo aux = equipos[j];
            equipos[j] = equipos[j + 1];
            equipos[j + 1] = aux;
          }
        }
      }
    }
  }

  public static int edadPromedio(Jugador[] jugadores, int i, int ac) {
    int prom = 0;
    if (jugadores[i] == null || i == jugadores.length) {
      if (jugadores[i] != null) {
        prom = (jugadores[i].getEdad() + ac) / i;
      } else {
        prom = ac / i;
      }
    } else {
      ac += jugadores[i].getEdad();
      prom = edadPromedio(jugadores, i + 1, ac);
    }
    return prom;
  }

  public static void verTablaDePosiciones(Equipo[] equipos) {
    ordenarEquipos(equipos);
    int i = equipos.length - 1;
    System.out.println("         POSICIONES DEL TORNEO");
    System.out.printf("%-20s %4s %3s %3s %3s %3s %3s %3s %4s%n", "Equipo", "Pts", "PJ", "PG", "PE",
        "PP", "GF", "GC", "DF");
    while (i >= 0) {
      System.out.println(
          String.format("%-20s %4d %3d %3d %3d %3d %3d %3d %4d",
              equipos[i].getNombre(),
              equipos[i].getPuntos(),
              equipos[i].getPartidosJugados(),
              equipos[i].getPartidosGanados(),
              equipos[i].getPartidosEmpatados(),
              equipos[i].getPartidosPerdidos(),
              equipos[i].getGolesAFavor(),
              equipos[i].getGolesEnContra(),
              equipos[i].getDiferenciaGoles()));
      i--;
    }
  }

}
