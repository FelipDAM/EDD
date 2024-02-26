package buscamines;

import Teclat.*;
import java.util.Random;

/**
 *
 * @author Felip
 */
public class BuscaminesLloretFelip {

    static final char BANDERETA = 'P';
    static final char TAPAT = 'X';
    static final char BUIT = '_';

    public static void main(String[] args) {
        boolean repetir = true;
        while (repetir == true) {
            boolean mostramines = false;
            char[][] taulervisible = creaTaulerVisible();

            final int MINA = Teclat.lligInt("Quantitat de mines del tauler", 2, (taulervisible.length - 1) * (taulervisible[0].length - 1) / 2);
            boolean[][] taulermines = creaTaulerMines(taulervisible, MINA);
            mostraTauler(taulervisible, taulermines, mostramines);

            //En el meu teclat tinc la funció modificada,per a que no aparega l'opció 0
            while (!mostramines && qDestapades(taulervisible) < ((taulervisible.length - 1) * (taulervisible[0].length - 1)) - MINA) {
                int Panel = Teclat.lligOpcio("Selecciona", "Picar una posició", "Posar una bandereta");
                int fila = Teclat.lligInt("Dis-me la fila del tauler", 1, taulervisible.length - 1);
                int columna = Teclat.lligInt("Dis-me la columna del tauler", 1, taulervisible[0].length - 1);

                switch (Panel) {
                    case 1:
                        if (pica(taulervisible, taulermines, fila, columna)) {
                            mostramines = true;
                        }
                     
                        break;

                    case 2:
                        if (destapada(taulervisible, fila, columna)) {
                              if (taulervisible[fila][columna] == BANDERETA) {
                                taulervisible[fila][columna] = TAPAT;
                            }
                            }else{
                        
                                taulervisible[fila][columna] = BANDERETA;
                            
                        }
                        break;
                }
                mostraTauler(taulervisible, taulermines, mostramines);
            }
            System.out.println();
            if (mostramines == false) {
                System.out.println("    " + "!!HAS GUANYAT!!");

            } else {
                System.out.println("    " + "!!HAS PERDUT!!");
            }

            System.out.println();
            repetir = Teclat.lligBoolean("Vols repetir la partida?");
            if (repetir) {
            } else {
                System.out.println("AUUUUU");
            }
        }
    }
    
    //CREA TAULER VISIBLE
    public static char[][] creaTaulerVisible() {
        final int FIL = Teclat.lligInt("Dis-me la cantitat de files del tauler", 5, 10);
        final int COL = Teclat.lligInt("Dis-me la cantitat de columnes del tauler", 5, 10);
        char taulervisible[][] = new char[FIL + 1][COL + 1];
        for (int fila = 1; fila < FIL + 1; fila++) {
            for (int columna = 1; columna < COL + 1; columna++) {
                taulervisible[fila][columna] = TAPAT;

            }

        }
        return taulervisible;
    }
    
    //CREA TAULER MINES
    public static boolean[][] creaTaulerMines(char taulervisible[][], int MINA) {
        boolean taulermines[][] = new boolean[taulervisible.length + 1][taulervisible[0].length + 1];

        Random random = new Random();

        int contadormines = 0;
        while (contadormines < MINA) {
            int fila = random.nextInt(taulervisible.length - 1) + 1;
            int columna = random.nextInt(taulervisible[0].length - 1) + 1;

            if (!taulermines[fila][columna]) {
                taulermines[fila][columna] = true;
                contadormines++;
            }
        }

        return taulermines;
    }

    //POSICIÓ MINADA?
    public static boolean minada(boolean taulermines[][], int fila, int columna) {
        return taulermines[fila][columna] == true;
    }
    
    //QUANTITAT DE MINES ADJACENTS
    public static int qma(boolean[][] taulermines, int fila, int columna) {
        int contadormines = 0;
        if (taulermines[fila - 1][columna - 1] == true) {
            contadormines++;
        }
        if (taulermines[fila - 1][columna] == true) {
            contadormines++;
        }
        if (taulermines[fila - 1][columna + 1] == true) {
            contadormines++;
        }
        if (taulermines[fila][columna - 1] == true) {
            contadormines++;
        }
        if (taulermines[fila][columna + 1] == true) {
            contadormines++;
        }
        if (taulermines[fila + 1][columna - 1] == true) {
            contadormines++;
        }
        if (taulermines[fila + 1][columna] == true) {
            contadormines++;
        }
        if (taulermines[fila + 1][columna + 1] == true) {
            contadormines++;
        }
        return contadormines;
    }

    //POSICIÓ DESTAPADA?
    public static boolean destapada(char[][] taulervisible, int fila, int columna) {
        if (taulervisible[fila][columna] != TAPAT) {
            return true;
        } else {
            return false;
        }

    }
    
    //QUANTITAT DE CASELLES DESTAPADES
    public static int qDestapades(char taulervisible[][]) {
        int contadordestapades = 0;
        for (int fil = 1; fil < taulervisible.length; fil++) {
            for (int col = 1; col < taulervisible[0].length; col++) {
                if (taulervisible[fil][col] != TAPAT && taulervisible[fil][col] != BANDERETA) {
                    contadordestapades++;
                }

            }
        }
        return contadordestapades;
    }

    //MOSTRA EL TAULER
    public static void mostraTauler(char taulervisible[][], boolean taulermines[][], boolean mostramines) {
        for (int i = 1; i < taulervisible.length; i++) {
            if (i == 1) {
                System.out.print("   ");
            }
            System.out.print(" " + i);
        }

        System.out.println();
        for (int fil = 1; fil < taulervisible.length; fil++) {
            if (fil >= 10) {
                System.out.print((fil) + " " + '|');
            } else {
                System.out.print(" " + (fil) + " " + '|');
            }

            for (int col = 1; col < taulervisible[0].length; col++) {
                if (mostramines == true) {
                    for (int fila = 1; fila < taulermines.length; fila++) {
                        for (int columna = 1; columna < taulermines[0].length; columna++) {
                            if (taulermines[fila][columna] == true) {
                                taulervisible[fila][columna] = '*';

                            }

                        }
                    }
                }
                System.out.print(taulervisible[fil][col]);

                if (col == taulervisible[0].length - 1) {
                    System.out.print('|' + " " + (fil));
                } else {
                    System.out.print('|');
                }
            }
            System.out.println();
        }
        for (int i = 1; i < taulervisible.length; i++) {
            if (i == 1) {
                System.out.print("   ");
            }
            System.out.print(" " + i);
        }
        System.out.println();

    }

    //INCORRECTA TAULER VISIBLE
    public static boolean incorrectav(char taulervisible[][], int fila, int columna) {
        if (fila < 1 || columna < 1 || fila > taulervisible.length - 1 || columna > taulervisible[0].length - 1) {
            return true;
        } else {
            return false;
        }

    }

    //INCORRECTA TAULER MINES
    public static boolean incorrectam(boolean taulermines[][], int fila, int columna) {
        if (fila < 1 || columna < 1 || fila > taulermines.length - 1 || columna > taulermines[0].length - 1) {
            return true;
        } else {
            return false;
        }

    }

    //PICAR UNA POSICIÓ
    public static boolean pica(char taulervisible[][], boolean taulermines[][], int fila, int columna) {
        if (taulermines[fila][columna] == true) {
            return true;
        } else {
            destapa(taulervisible, taulermines, fila, columna);
            return false;
        }

    }
    //DESTAPAR RECURSIVAMENT
    public static void destapa(char taulervisible[][], boolean taulermines[][], int fila, int columna) {
        if (incorrectav(taulervisible, fila, columna) || incorrectam(taulermines, fila, columna) || destapada(taulervisible, fila, columna)) {
            return;
        }
        if (qma(taulermines, fila, columna) > 0) {
            taulervisible[fila][columna] = Integer.toString(qma(taulermines, fila, columna)).charAt(0);
            return;
        }
        taulervisible[fila][columna] = BUIT;

        destapa(taulervisible, taulermines, fila - 1, columna - 1);
        destapa(taulervisible, taulermines, fila - 1, columna);
        destapa(taulervisible, taulermines, fila - 1, columna + 1);
        destapa(taulervisible, taulermines, fila, columna - 1);
        destapa(taulervisible, taulermines, fila, columna + 1);
        destapa(taulervisible, taulermines, fila + 1, columna - 1);
        destapa(taulervisible, taulermines, fila + 1, columna);
        destapa(taulervisible, taulermines, fila + 1, columna + 1);
    }
}
