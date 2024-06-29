
import java.io.File;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.file.Files;

import TDA.Equipo;
import TDA.Jugador;

public class Cargar {

  private final static JFileChooser choose = new JFileChooser();
  private final static FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");

  static {
    choose.addChoosableFileFilter(filter);
    choose.setCurrentDirectory(new File(System.getProperty("user.dir")));
  }

  public static void cargarDatos(Jugador[] jugadores, Equipo[] equipos) {
    boolean equiposCargados = false, jugadoresCargados = false;
    while (!equiposCargados) {
      try {
        System.out.println("Seleccionar archivo de equipos: ");
        int returnVal = choose.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = choose.getSelectedFile();
          System.out.println("Seleccionaste : " + file.getName());
          if (!file.getName().endsWith(".txt")
              || !file.getName().equalsIgnoreCase("Equipos.txt")) {
            throw new IOException();
          } else {
            int cantidadEquipos = Files.readAllLines(file.toPath()).size();
            for (int i = 0; i < cantidadEquipos; i++) {
              String[] data = Files.readAllLines(file.toPath()).get(i).split(";");
              if (data != null) {
                equipos[i] = new Equipo(data[0], data[1].charAt(0));
              }
            }
            equiposCargados = true;
          }
        } else {
          System.out.println("No se selecciono ningun archivo");
        }
      } catch (IOException e) {
        System.out.println("Error al cargar el archivo");
      }
    }

    while (!jugadoresCargados) {
      try {
        System.out.println("Seleccionar archivo de Jugadores: ");
        int returnVal = choose.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = choose.getSelectedFile();
          System.out.println("Seleccionaste : " + file.getName());
          if (!file.getName().endsWith(".txt")
              || !file.getName().equalsIgnoreCase("Jugadores.txt")) {
            throw new IOException();
          } else {

            int cantidadJugadores = Files.readAllLines(file.toPath()).size();
            for (int i = 0; i < cantidadJugadores; i++) {
              String[] data = Files.readAllLines(file.toPath()).get(i).split(";");
              if (data != null) {
                jugadores[i] = new Jugador(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                    Integer.parseInt(data[4]), data[5]);
                Metodos.cargarJugador(equipos, jugadores[i]);
              }
            }
            jugadoresCargados = true;
          }
        } else {
          System.out.println("No se selecciono ningun archivo");
        }
      } catch (IOException e) {
        System.out.println("Error al cargar el archivo");
      }
    }

  }

  // ! LO BORRO!
  /*
   * public static void cargarJugadores(Jugador[] jugadores, Equipo[] equipos) {
   * try {
   * System.out.println("Seleccionar archivo de Jugadores: ");
   * int returnVal = choose.showOpenDialog(null);
   * if (returnVal == JFileChooser.APPROVE_OPTION) {
   * File file = choose.getSelectedFile();
   * System.out.println("Seleccionaste : " + choose.getSelectedFile().getName());
   * if (!choose.getSelectedFile().getName().endsWith(".txt")) {
   * System.out.println("El archivo seleccionado no es un archivo de texto");
   * } else {
   * 
   * int cantidadJugadores = Files.readAllLines(file.toPath()).size();
   * for (int i = 0; i < cantidadJugadores; i++) {
   * String[] data = Files.readAllLines(file.toPath()).get(i).split(";");
   * if (data != null) {
   * jugadores[i] = new Jugador(data[0], data[1], Integer.parseInt(data[2]),
   * Integer.parseInt(data[3]),
   * Integer.parseInt(data[4]), data[5]);
   * Metodos.cargarJugador(equipos, jugadores[i]);
   * }
   * }
   * }
   * } else {
   * System.out.println("No se selecciono ningun archivo");
   * }
   * } catch (IOException e) {
   * System.out.println("Error al cargar el archivo");
   * }
   * }
   * 
   * public static void cargarEquipos(Equipo[] equipo) {
   * try {
   * System.out.println("Seleccionar archivo de equipos: ");
   * int returnVal = choose.showOpenDialog(null);
   * if (returnVal == JFileChooser.APPROVE_OPTION) {
   * File file = choose.getSelectedFile();
   * System.out.println("Seleccionaste : " + choose.getSelectedFile().getName());
   * if (!choose.getSelectedFile().getName().endsWith(".txt")) {
   * System.out.println("El archivo seleccionado no es un archivo de texto");
   * } else {
   * int cantidadEquipos = Files.readAllLines(file.toPath()).size();
   * for (int i = 0; i < cantidadEquipos; i++) {
   * String[] data = Files.readAllLines(file.toPath()).get(i).split(";");
   * if (data != null) {
   * equipo[i] = new Equipo(data[0], data[1].charAt(0));
   * }
   * }
   * }
   * } else {
   * System.out.println("No se selecciono ningun archivo");
   * }
   * } catch (IOException e) {
   * System.out.println("Error al cargar el archivo");
   * }
   * }
   */
}
