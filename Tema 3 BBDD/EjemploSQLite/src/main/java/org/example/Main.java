package org.example;


import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:misrutas.db");
            System.out.println("Conexion OK con misrutas.db");
            consultarRutas(conexion);
            //insertarRuta(conexion);
            consultarRutasCirculares(conexion);
            consultarPuntosRuta(conexion);
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void consultarPuntosRuta(Connection conexion) {
        try {
            //mediante conexion, crear un objeto Statement para enviar
            // instrucciones SQL sin parámetros
            Statement st = conexion.createStatement();
            // crear texto de instruccion SQL y enviar
            //Devuelve un conjunto de resultados
            String txtSQL = "SELECT descripcion, tipo FROM waypoints inner join rutas on waypoints.ruta= rutas.numruta where rutas.tituloruta = 'Ruta del Cares'";
            ResultSet result = st.executeQuery(txtSQL);
            // . . . . Procesar result
            //Recorrer todas las filas de result, desde la primera
// avanzando de una en una hacia la siguiente
            System.out.println("Puntos de la Ruta del Cares");
            while (result.next()) {
                // Saca el valor de la columna nombregrupo
                String nom = result.getString("descripcion");
                String tipo = result.getString("tipo");
                System.out.println(nom + " Tipo de parada --> " + tipo);
            }
            st.close();
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consultarRutasCirculares(Connection conexion) {
        try {
            //mediante conexion, crear un objeto Statement para enviar
            // instrucciones SQL sin parámetros
            Statement st = conexion.createStatement();
            // crear texto de instruccion SQL y enviar
            //Devuelve un conjunto de resultados
            String txtSQL = "SELECT tituloruta, inicio FROM rutas WHERE circular";
            ResultSet result = st.executeQuery(txtSQL);
            // . . . . Procesar result
            //Recorrer todas las filas de result, desde la primera
// avanzando de una en una hacia la siguiente
            System.out.println("Rutas circulares");
            while (result.next()) {
                // Saca el valor de la columna nombregrupo
                String nom = result.getString("tituloruta");
                System.out.println("TÍTULO RUTA CIRCULAR: " + nom);
            }
            st.close();
            result.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consultarRutas(Connection conexion) {
        try {
            //mediante conexion, crear un objeto Statement para enviar
            // instrucciones SQL sin parámetros
            Statement st = conexion.createStatement();
            // crear texto de instruccion SQL y enviar
            //Devuelve un conjunto de resultados
            String txtSQL = "SELECT tituloruta, inicio FROM rutas";
            ResultSet result = st.executeQuery(txtSQL);
            // . . . . Procesar result
            //Recorrer todas las filas de result, desde la primera
// avanzando de una en una hacia la siguiente
            while (result.next()) {
                // Saca el valor de la columna nombregrupo
                String nom = result.getString("tituloruta");
// Saca el valor de la columna segunda de la consulta (localidad)
                String inicio = result.getString(2);
                System.out.println("TÍTULO RUTA: " + nom + ",  INICIO: " + inicio);
            }
            st.close();
            result.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertarRuta(Connection conexion) {
//. . . . Recoger datos en titulo,inicio, final, fecha
//Crear texto de consulta con parámetros sustituibles
        String textInsert = "INSERT INTO rutas (tituloruta,inicio,final,circular,fecha) VALUES (?,?,?,?,?);";
        try {
            //Construir un PreparedStatement para sustituir valores
            // en consulta parametrizada
            PreparedStatement prepSt = conexion.prepareStatement(textInsert);
            // . . . . Aquí se sustituyen valores por las ? y se envía para
            // su ejecución la instrucción con los parámetros ya sustituidos
// Sustituye la ? primera por el contenido de título de ruta
            String tit = "Los Puentes de Ucieda";
            String ini = "Parque de Ucieda";
            String fin = "Pista de Ucieda";
            Boolean circular = true;
            String fecha = "2022-09-15";
            prepSt.setString(1, tit);
// Sustituye la ? segunda por el contenido de inicio
            prepSt.setString(2, ini);
            prepSt.setString(3, fin);
            prepSt.setBoolean(4, circular);
            prepSt.setString(5, fecha);
//Ejecutar la instrucción y obtener cuantas filas han sido afectadas;
            int n = prepSt.executeUpdate();
            System.out.println("Se han insertado " + n + " rutas");
            prepSt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}