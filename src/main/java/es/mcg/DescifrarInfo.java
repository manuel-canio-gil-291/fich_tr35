package es.mcg;

import java.io.File;
import java.util.Scanner;
/**
 * Esta clase descifra el enunciado del ejercicio oculto
 * 
 * @author Manuel Canio Gil
 * @version 1.0
 * @since 2022-11-14
 */
public class DescifrarInfo {
    public static void main(String[] args) {
        File file = null;
        Scanner sc = null;
        try 
        {
            file = new File("InformacionCifrada.txt");
            sc = new Scanner(file);
            char primerCaracter = '_';
            int r = 0;
            String lineaDescifrada;
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                for(int i = 0; i < linea.length(); i++)
                {
                    int codigo = linea.codePointAt(i);
                    char caracter = (char)codigo;
                    if(caracter == '+')
                    {
                        primerCaracter = caracter;
                    }
                    else if(caracter == '*')
                    {
                        primerCaracter = caracter;
                    }
                    switch(caracter)
                    {
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                        case '0': {
                            
                            if(primerCaracter == '+')
                            {
                                String chartoString = Character.toString(caracter);
                                int stringtoInt = Integer.parseInt(chartoString);
                                r = r + stringtoInt;
                            }
                            else if(primerCaracter == '*')
                            {
                                String chartoString = Character.toString(caracter);
                                int stringtoInt = Integer.parseInt(chartoString);
                                if(r == 0)
                                {
                                    r = stringtoInt;
                                }
                                else
                                {
                                    r = r*stringtoInt;
                                }
                            }
                        }break;
                        default:
                    }
                }
                lineaDescifrada = descifrado(linea, r);
                System.out.println(lineaDescifrada);
                r = 0;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            if(sc != null)
            {
                sc.close();
            }
        }
    }

    public static String descifrado(String linea, int codigo)
    {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < linea.length(); i++) {
            if (linea.charAt(i) >= 'a' && linea.charAt(i) <= 'z') 
            {
                if ((linea.charAt(i) - codigo) < 'a') 
                {
                    cifrado.append((char) (linea.charAt(i) - codigo + 26));
                } 
                else 
                {
                    cifrado.append((char) (linea.charAt(i) - codigo));
                }
            } else if (linea.charAt(i) >= 'A' && linea.charAt(i) <= 'Z') 
            {
                if ((linea.charAt(i) - codigo) < 'A') 
                {
                    cifrado.append((char) (linea.charAt(i) - codigo + 26));
                } 
                else 
                {
                    cifrado.append((char) (linea.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }
}
