
import TDA.Equipo;
import TDA.Jugador;

public class TpFinal {

    public static void main(String[] args) {
        // Arreglos y matrices necesarios
        Equipo[] equipos = new Equipo[8];
        Jugador[] jugadores = new Jugador[equipos.length * 15];
        int[][] resultados = new int[7][9];
        Equipo[][] fixture;
        // Cargar datos de los equipos y jugadores
        System.out.println(" -> Cargar datos de los equipos y jugadores:");
        Cargar.cargarEquipos(equipos);
        Cargar.cargarJugadores(jugadores, equipos);

        // ! Generar fixture
        // Una ves cargados los equipos y jugadores, generamos el fixture
        fixture = Metodos.generarFixture(equipos);

        Metodos.cargarMenu(fixture, resultados, jugadores, equipos);
    }

}