package proyect.springReactive.cuatroRaya.aplication.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
@Slf4j
public class CheckPlayerWin extends java.awt.Component implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    boolean fin;
    int win = 0;
    int table[][] = new int[6][7];

    int ganar (int table[][]) {
        this.table = table;
    /*
     *	x fila
     *	y columna
     */
    fin = false;
    int ganar1 = 0;
    int ganar2 = 0;
        // Quien gana en --horizontal
        for (int j = 0; j < 6; j++) {
          for (int i = 0; i < 7; i++) {
              if (table[j][i] != 0) {
                if (table[j][i] == 1){
                    ganar1++;
                    ganar2 = 0;
                }
                else{
                    ganar2++;
                    ganar1 = 0;
                }
                if (ganar1 == 4) {
                    System.out.println("Conecta 4 horizontal: -Gana Player 1");
                    VolverEmpezarH();
                    fin = true;
                    win = 1;
                }
                if (ganar2 == 4) {
                    System.out.println("Conecta 4 horizontal: -Gana Player 2");
                    VolverEmpezarH();
                    win = 2;
                }
            } else {
                ganar1 = 0;
                ganar2 = 0;
            }
          }
          ganar1 = 0;
          ganar2 = 0;
        }
//        Quien gana en vertical
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 6; i++) {
                //metodprueba(table, i, j, ganar1, ganar2, win)
                if (table[i][j] != 0) {
                    if (table[i][j] == 1){
                        ganar1++;
                        ganar2 = 0;
                    }
                    else{
                        ganar2++;
                        ganar1 = 0;
                    }
                    if (ganar1 == 4) {
                        System.out.println("Conecta 4 vertical: -Gana Player 1");
                        VolverEmpezarV();
                        fin = true;
                        win = 1;
                    }
                    if (ganar2 == 4) {
                        System.out.println("Conecta 4 vertical: -Gana Player 2");
                        VolverEmpezarV();
                        win = 2;
                    }
                } else {
                    ganar1 = 0;
                    ganar2 = 0;
                }
            }
        }
        ganar1 = 0;
        ganar2 = 0;

        return win;
    }

    void VolverEmpezarV()
    {
//        for(int i=0;i<7;i++)
//        {
//            for(int j=0;j<6;j++)
//            {
//                table[j][i] = 0;
//            }
//        }
    }
    void VolverEmpezarH()
    {
//        for(int i=0;i<6;i++)
//        {
//            for(int j=0;j<7;j++)
//            {
//                table[i][j] = 0;
//            }
//        }
    }
}
