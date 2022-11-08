package es.mcg.ejerciciooculto;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.mcg.utils.Utils;

public class Main {
    public static void introducirDatos(Scanner sc) 
    {
        int op;
        System.out.println("Elige si desea introducir persona normal o persona universitaria");
        System.out.println("\t1.- Persona normal\n\t2.- Persona universitaria\n");
        System.out.print("Tu opcion: ");
        op = sc.nextInt();
        if(op == 1) introducirDatos(sc);
        else if(op == 2) introducirDatosUniversidad(sc);
        else System.out.println("Opcion no valida");
    }

    public static void introducirDatosPersona(Scanner sc)
    {
        File file = null;
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        int codigo, edad;
        String nombre;
        char c;
        boolean continuar = true;
        try 
        {
            file = new File("DatosPersona.txt");
            fileWriter = new FileWriter(file);
            printWriter = new PrintWriter(fileWriter);
            do{
                System.out.print("Codigo de la persona: ");
                codigo = sc.nextInt();
                System.out.print("Nombre de la persona: ");
                nombre = sc.nextLine();
                System.out.print("Edad de la persona: ");
                edad = sc.nextInt();
                printWriter.println(codigo);
                printWriter.println(nombre);
                printWriter.println(edad);
                printWriter.println("---------");
                System.out.print("Guardar mas datos? (s/n) ");
                c = sc.nextLine().charAt(0);
                if(c == 's')
                {
                    continuar = true;
                }
                else if (c == 'n')
                {
                    continuar = false;
                    printWriter.flush();
                }
            }while(continuar);
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(printWriter != null)
            {
                printWriter.close();
            }
            if(fileWriter != null)
            {
                try 
                {
                    fileWriter.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void introducirDatosUniversidad(Scanner sc)
    {
        File file = null;
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        int codigo, edad, universidadId;
        String nombre, correo;
        Pattern pattern = null;
        Matcher matcher = null;
        char c;
        boolean continuar = true;
        try 
        {
            file = new File("DatosPersonaUniversidad.txt");
            fileWriter = new FileWriter(file);
            printWriter = new PrintWriter(fileWriter);
            pattern = Pattern.compile(Utils.REGEX);
            do{
                System.out.print("Codigo de la persona: ");
                codigo = sc.nextInt();
                System.out.print("Nombre de la persona: ");
                nombre = sc.nextLine();
                System.out.print("Edad de la persona: ");
                edad = sc.nextInt();
                System.out.print("Correo de la persona: ");
                correo = sc.nextLine();
                universidadId = (int)Math.floor(Math.random()*10000);
                matcher = pattern.matcher(correo);
                printWriter.println(codigo);
                printWriter.println(nombre);
                printWriter.println(edad);
                printWriter.println(universidadId);
                if(!matcher.matches())
                {
                    System.err.println("ERROR. El correo no es valido");
                }
                else
                {
                    printWriter.println(correo);
                }
                printWriter.println("---------");
                System.out.print("Guardar mas datos? (s/n) ");
                c = sc.nextLine().charAt(0);
                if(c == 's')
                {
                    continuar = true;
                }
                else if (c == 'n')
                {
                    continuar = false;
                    printWriter.flush();
                }
            }while(continuar);
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(printWriter != null)
            {
                printWriter.close();
            }
            if(fileWriter != null)
            {
                try 
                {
                    fileWriter.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void guardarDatosPrimitivos(Scanner sc)
    {
        int op;
        System.out.println("Elige si desea guardar datos de persona normal o persona universitaria");
        System.out.println("\t1.- Persona normal\n\t2.- Persona universitaria\n");
        System.out.print("Tu opcion: ");
        op = sc.nextInt();
        if(op == 1) guardarDatosPrimitivosPersona();
        else if(op == 2) guardarDatosPrimitivosUniversidad();
        else System.out.println("Opcion no valida");
    }

    public static void guardarDatosPrimitivosPersona()
    {
        File file = null;
        File file2 = null;
        Scanner scan = null;
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try 
        {
            file = new File("DatosPersona.txt");
            file2 = new File("DatosPrimitivosPersona.dat");
            if(!file2.exists())
            {
                file2.createNewFile();
            }
            scan = new Scanner(file);
            fileOutputStream = new FileOutputStream(file2);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            int codigo, edad;
            String nombre, linea;
            while(scan.hasNextLine())
            {
                codigo = scan.nextInt();
                nombre = scan.nextLine();
                edad = scan.nextInt();
                linea = scan.nextLine();
                if(linea.equals("---------"))
                {
                    dataOutputStream.writeInt(codigo);
                    dataOutputStream.writeUTF(nombre);
                    dataOutputStream.writeInt(edad);
                    dataOutputStream.writeUTF(linea);
                }
            }
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(scan != null)
            {
                scan.close();
            }
            if(dataOutputStream != null)
            {
                try 
                {
                    dataOutputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileOutputStream != null)
            {
                try 
                {
                    fileOutputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void guardarDatosPrimitivosUniversidad()
    {
        File file = null;
        File file2 = null;
        Scanner scan = null;
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try 
        {
            file = new File("DatosPersonaUniversidad.txt");
            file2 = new File("DatosPrimitivosUniversidad.dat");
            if(!file2.exists())
            {
                file2.createNewFile();
            }
            scan = new Scanner(file);
            fileOutputStream = new FileOutputStream(file2);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            int codigo, edad, universidadId;
            String nombre, correo, linea;
            while(scan.hasNextLine())
            {
                codigo = scan.nextInt();
                nombre = scan.nextLine();
                edad = scan.nextInt();
                universidadId = scan.nextInt();
                correo = scan.nextLine();
                linea = scan.nextLine();
                if(linea.equals("---------"))
                {
                    dataOutputStream.writeInt(codigo);
                    dataOutputStream.writeUTF(nombre);
                    dataOutputStream.writeInt(edad);
                    dataOutputStream.writeInt(universidadId);
                    dataOutputStream.writeUTF(correo);
                    dataOutputStream.writeUTF(linea);
                }
            }
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(scan != null)
            {
                scan.close();
            }
            if(dataOutputStream != null)
            {
                try 
                {
                    dataOutputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileOutputStream != null)
            {
                try 
                {
                    fileOutputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        boolean salir = false;
        System.out.println("******************************");
        System.out.println("* Programa datos persona 1.0 *");
        System.out.println("******************************\n");
        System.out.println("Seleccione una opcion:");
        do{
            System.out.println("\t1.- Guardar datos de la persona\n\t2.- Leer datos y guardar en tipo primitivo"+
                "\n\t3.- Leer datos de tipo primitivo y guardar en un objeto\n\t4.- Buscar datos de la persona\n\t5.- Salir\n");
            System.out.print("Tu opcion: ");
            op = sc.nextInt();
            
            switch (op) {
                case 1: introducirDatos(sc); break;
                case 2: guardarDatosPrimitivos(sc); break;
                case 3: break;
                case 4: break;
                case 5: System.out.println("Ha finalizado el programa"); sc.close(); salir = true; break;
                default: System.out.println("Opcion no valida");
            }
        }while(!salir);
    }
}
