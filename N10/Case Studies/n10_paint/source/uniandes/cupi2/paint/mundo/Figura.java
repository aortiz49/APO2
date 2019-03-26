/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Figura.java,v 1.12 2007/04/13 04:29:55 carl-veg Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_paint
 * Autor: Mario Sánchez - 27/09/2005
 * Autor: Jorge Villalobos - 26/09/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.paint.mundo;

import java.awt.*;
import java.io.*;

/**
 * Esta es una clase abstracta que representa una Figura. <br>
 * <b>inv:</b> <br>
 * colorLinea!=null <br>
 * tipoLinea!=null <br>
 * fuente!=null <br>
 * x1>=0 <br>
 * x2>=0 <br>
 * y1>=0 <br>
 * y2>=0
 */
public abstract class Figura implements IFigura {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El color del borde de la figura
     */
    protected Color colorLinea;

    /**
     * Indica las propiedades del borde de la figura
     */
    protected BasicStroke tipoLinea;

    /**
     * La coordenada x del primer punto
     */
    protected int x1;

    /**
     * La coordenada y del primer punto
     */
    protected int y1;

    /**
     * La coordenada x del segundo punto
     */
    protected int x2;

    /**
     * La coordenada y del segundo punto
     */
    protected int y2;

    /**
     * El texto que va a ser mostrado en la figura
     */
    private String texto;

    /**
     * El tipo de letra del texto mostrado
     */
    private Font tipoLetra;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la figura
     * @param x1f La coordenada x del primer punto - x1f>=0
     * @param y1f La coordenada y del primer punto - y1f>=0
     * @param x2f La coordenada x del segundo punto - x2f>=0
     * @param y2f La coordenada y del segundo punto - y2f>=0
     * @param colorLineaF El color de la línea de la figura - colorLineaF != null
     * @param tipoLineaF El tipo de línea usado para pintar la figura - tipoLineaF != null
     */
    public Figura(int x1f, int y1f, int x2f, int y2f, Color colorLineaF, BasicStroke tipoLineaF) {
        x1 = x1f;
        x2 = x2f;
        y1 = y1f;
        y2 = y2f;
        colorLinea = colorLineaF;
        tipoLinea = tipoLineaF;
        texto = "";
        tipoLetra = new Font("Arial", Font.PLAIN, 12);
        verificarInvariante();
    }

    /**
     * Construye una figura a partir de los datos contenidos en un archivo
     * @param br Es el flujo que sirve para leer el archivo
     * @throws IOException Se lanza esta excepción si hay problemas leyendo el archivo
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato del archivo no es
     * el esperado
     */
    public Figura(BufferedReader br) throws IOException, FormatoInvalidoException {
        String lineaPuntos = br.readLine();
        inicializarPuntos(lineaPuntos);

        String lineaColorLinea = br.readLine();
        inicializarColores(lineaColorLinea);

        String lineaFuente = br.readLine();
        inicializarFuente(lineaFuente);

        texto = br.readLine();

        String lineaTipoLinea = br.readLine();
        inicializarTipoLinea(lineaTipoLinea);
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Métodos Abstractos
    // -----------------------------------------------------------------

    /**
     * Dibuja la figura sobre la superficie que se recibe como parámetro
     * @param g La superficie donde se debe pintar
     * @param seleccionada Indica si la figura que se va a dibujar es la seleccionada
     */
    public abstract void dibujar(Graphics2D g, boolean seleccionada);

    /**
     * Sirve para saber si un punto está dentro de una figura o no.
     * @param x Es la coordenada x del punto
     * @param y Es la coordenada y del punto
     * @return Retorna true si el punto está dentro de la figura. Retorna false en caso contrario.
     */
    public abstract boolean estaDentro(int x, int y);

    /**
     * Retorna la cadena de caracteres que va a identificar este tipo de figuras en la persistencia
     * @return Retorna la cadena con el tipo de la figura
     */
    public abstract String darTipoFigura();

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Guarda la figura en un archivo que recibe como parámetro
     * @param out Es el flujo donde se va a guardar la figura
     */
    public void guardar(PrintWriter out) {
        out.println(x1 + ";" + y1 + ";" + x2 + ";" + y2);
        out.println(colorLinea.getRed() + ";" + colorLinea.getGreen() + ";" + colorLinea.getBlue());
        out.println(tipoLetra.getFamily() + ";" + tipoLetra.getStyle() + ";" + tipoLetra.getSize());
        out.println(texto);

        String strDash = "";
        float[] dashArray = tipoLinea.getDashArray();
        for (int i = 0; i < dashArray.length; i++) {
            float f = dashArray[i];
            strDash = strDash + f + " ";
        }
        out.println(tipoLinea.getLineWidth() + ";" + tipoLinea.getEndCap() + ";" + tipoLinea
                .getLineJoin() + ";" + tipoLinea.getMiterLimit() + ";" + strDash.trim() + ";"
                            + tipoLinea.getDashPhase());
    }

    /**
     * Sirve para pintar el texto de la figura. Este método ha sido construido para que sea
     * utilizado desde las subclases
     * @param g La superficie donde se debe pintar
     */
    protected void dibujarTexto(Graphics2D g) {
        int centroX = Math.abs(x2 + x1) / 2;
        int centroY = Math.abs(y2 + y1) / 2;

        g.setFont(tipoLetra);
        g.setColor(Color.BLACK);

        FontMetrics metrics = g.getFontMetrics();
        int ancho = metrics.stringWidth(texto);

        g.drawString(texto, centroX - ancho / 2, centroY);
    }

    /**
     * Retorna el texto de la figura
     * @return texto El texto de la figura
     */
    public String darTexto() {
        return texto;
    }

    /**
     * Cambia el texto de la figura
     * @param txt El nuevo texto
     */
    public void cambiarTexto(String txt) {
        texto = txt;
        verificarInvariante();
    }

    /**
     * Retorna la fuente actual del texto
     * @return fuente La fuente del texto
     */
    public Font darTipoLetra() {
        return tipoLetra;
    }

    /**
     * Cambia la fuente del texto
     * @param fuenteTexto La nueva fuente del texto
     */
    public void cambiarTipoLetra(Font fuenteTexto) {
        tipoLetra = fuenteTexto;
        verificarInvariante();
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Inicializa los cuatro puntos que definen la figura, los cuales fueron leídos del archivo<br>
     * <b>post:</b> x1,y1,x2,y2 tienen nuevos valores
     * @param lineaPuntos La línea con la información de los dos puntos
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es
     * el esperado
     */
    private void inicializarPuntos(String lineaPuntos) throws FormatoInvalidoException {
        String[] strValores = lineaPuntos.split(";");
        if (strValores.length != 4)
            throw new FormatoInvalidoException(lineaPuntos);
        try {
            x1 = Integer.parseInt(strValores[0]);
            y1 = Integer.parseInt(strValores[1]);
            x2 = Integer.parseInt(strValores[2]);
            y2 = Integer.parseInt(strValores[3]);
        } catch (NumberFormatException nfe) {
            throw new FormatoInvalidoException(lineaPuntos);
        }
    }

    /**
     * Inicializa el color de la línea y el del fondo a partir de la información que estaba
     * contenida en el archivo<br>
     * <b>post:</b> colorLinea y ColorFondo tienen nuevos colores
     * @param lineaColorLinea La línea de texto que indica el color de las líneas de la figura
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es
     * el esperado
     */
    private void inicializarColores(String lineaColorLinea) throws FormatoInvalidoException {
        String[] strValoresLinea = lineaColorLinea.split(";");
        if (strValoresLinea.length != 3)
            throw new FormatoInvalidoException(lineaColorLinea);

        try {
            int r1 = Integer.parseInt(strValoresLinea[0]);
            int g1 = Integer.parseInt(strValoresLinea[1]);
            int b1 = Integer.parseInt(strValoresLinea[2]);
            colorLinea = new Color(r1, g1, b1);
        } catch (NumberFormatException nfe) {
            throw new FormatoInvalidoException(lineaColorLinea);
        }
    }

    /**
     * Inicializa la fuente usada para mostrar el texto de la figura<br>
     * <b>post:</b> fuente tiene una nueva fuente de texto
     * @param lineaFuente La línea de texto que describe la fuente usada en el texto de la figura
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es
     * el esperado
     */
    private void inicializarFuente(String lineaFuente) throws FormatoInvalidoException {
        String[] strValoresFuente = lineaFuente.split(";");
        if (strValoresFuente.length != 3)
            throw new FormatoInvalidoException(lineaFuente);

        try {
            String familia = strValoresFuente[0];
            int estilo = Integer.parseInt(strValoresFuente[1]);
            int tamano = Integer.parseInt(strValoresFuente[2]);
            tipoLetra = new Font(familia, estilo, tamano);
        } catch (NumberFormatException nfe) {
            throw new FormatoInvalidoException(lineaFuente);
        }
    }

    /**
     * Inicializa el tipo de línea usado para pintar la figuras<br>
     * <b>post:</b> tipoLinea tiene una nueva configuración de línea
     * @param lineaTipoLinea La línea de texto que describe el tipo de líneas de la figura
     * @throws FormatoInvalidoException Se lanza esta excepción si el formato de la línea no es
     * el esperado
     */
    private void inicializarTipoLinea(String lineaTipoLinea) throws FormatoInvalidoException {
        String[] strValoresTipo = lineaTipoLinea.split(";");
        if (strValoresTipo.length != 6)
            throw new FormatoInvalidoException(lineaTipoLinea);

        try {
            float ancho = Float.parseFloat(strValoresTipo[0]);
            int cap = Integer.parseInt(strValoresTipo[1]);
            int join = Integer.parseInt(strValoresTipo[2]);
            float miterLimit = Float.parseFloat(strValoresTipo[3]);
            float dashPhase = Float.parseFloat(strValoresTipo[5]);

            String[] valoresLineas = strValoresTipo[4].split(" ");
            float[] dash = new float[valoresLineas.length];
            for (int i = 0; i < valoresLineas.length; i++) {
                dash[i] = Float.parseFloat(valoresLineas[i]);
            }
            tipoLinea = new BasicStroke(ancho, cap, join, miterLimit, dash, dashPhase);
        } catch (NumberFormatException nfe) {
            throw new FormatoInvalidoException(lineaTipoLinea);
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase. <br>
     * <b>inv:</b> <br>
     * colorLinea!=null <br>
     * tipoLinea!=null <br>
     * fuente!=null <br>
     * x1>=0 <br>
     * x2>=0 <br>
     * y1>=0 <br>
     * y2>=0
     */
    protected void verificarInvariante() {
        assert colorLinea != null : "Atributo inválido";
        assert tipoLinea != null : " Atributo inválido";
        assert tipoLetra != null : " Atributo inválido";
        assert x1 >= 0 : "Coordenada x1 inválida";
        assert x2 >= 0 : "Coordenada x2 inválida";
        assert y1 >= 0 : "Coordenada y1 inválida";
        assert y2 >= 0 : "Coordenada y2 inválida";
    }
}