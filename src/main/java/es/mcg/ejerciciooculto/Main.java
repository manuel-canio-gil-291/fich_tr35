package es.mcg.ejerciciooculto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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

    public static void guardarLista(Scanner sc)
    {
        int op;
        System.out.println("Elige si desea guardar una lista de datos de persona normal o persona universitaria");
        System.out.println("\t1.- Persona normal\n\t2.- Persona universitaria\n");
        System.out.print("Tu opcion: ");
        op = sc.nextInt();
        if(op == 1) guardarListaPersona();
        else if(op == 2) guardarListaUniversidad();
        else System.out.println("Opcion no valida");
    }

    public static void guardarListaPersona() 
    {
        File file = null;
        File file2 = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        Persona persona = null;
        List<Persona> personas = null;
        try 
        {
            file = new File("DatosPrimitivosPersona.dat");
            file2 = new File("ListaDatosPersona.obj");
            if(!file2.exists())
            {
                file2.createNewFile();
            }
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(file2);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            personas = new ArrayList<Persona>();
            String nombre, linea;
            int codigo, edad;
            while(fileInputStream.read() != -1)
            {
                codigo = dataInputStream.readInt();
                nombre = dataInputStream.readUTF();
                edad = dataInputStream.readInt();
                linea = dataInputStream.readUTF();
                if(linea.equals("---------"))
                {
                    persona = new Persona(codigo, nombre, edad);
                    personas.add(persona);
                }
            }
            objectOutputStream.writeObject(personas);
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(objectOutputStream != null)
            {
                try 
                {
                    objectOutputStream.close();    
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
            if(dataInputStream != null)
            {
                try 
                {
                    dataInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileInputStream != null)
            {
                try 
                {
                    fileInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void guardarListaUniversidad() 
    {
        File file = null;
        File file2 = null;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        PersonaUniversidad personaUniversidad = null;
        List<PersonaUniversidad> personasUniversidad = null;
        try 
        {
            file = new File("DatosPrimitivosUniversidad.dat");
            file2 = new File("ListaDatosUniversidad.obj");
            if(!file2.exists())
            {
                file2.createNewFile();
            }
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);
            fileOutputStream = new FileOutputStream(file2);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            personasUniversidad = new ArrayList<PersonaUniversidad>();
            String nombre, correo, linea;
            int codigo, edad, universidadId;
            while(fileInputStream.read() != -1)
            {
                codigo = dataInputStream.readInt();
                nombre = dataInputStream.readUTF();
                edad = dataInputStream.readInt();
                universidadId = dataInputStream.readInt();
                correo = dataInputStream.readUTF();
                linea = dataInputStream.readUTF();
                if(linea.equals("---------"))
                {
                    personaUniversidad = new PersonaUniversidad(codigo, nombre, edad, universidadId, correo);
                    personasUniversidad.add(personaUniversidad);
                }
            }
            objectOutputStream.writeObject(personasUniversidad);
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(objectOutputStream != null)
            {
                try 
                {
                    objectOutputStream.close();    
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
            if(dataInputStream != null)
            {
                try 
                {
                    dataInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileInputStream != null)
            {
                try 
                {
                    fileInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static void buscarDatos(Scanner sc)
    {
        File file = null;
        File file2 = null;
        List<Persona> personas = null;
        List<PersonaUniversidad> personasUniversidad = null;
        FileInputStream fileInputStream = null, fileInputStream2 = null;
        ObjectInputStream objectInputStream = null, objectInputStream2 = null;
        try 
        {
            file = new File("ListaDatosPersona.obj");
            file2 = new File("ListaDatosUniversidad.obj");
            fileInputStream = new FileInputStream(file);
            fileInputStream2 = new FileInputStream(file2);
            objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream2 = new ObjectInputStream(fileInputStream2);
            personas = (List<Persona>) objectInputStream.readObject();
            personasUniversidad = (List<PersonaUniversidad>) objectInputStream2.readObject();
            int codigo, edad, universidadId;
            String nombre, correo;
            int op;
            boolean salir = false, encontrado = false;
            do
            {
                System.out.println("Digame que campo desea buscar:");
                System.out.println("\t1.- Codigo\n\t2.- Nombre\n\t3.- Edad\n\t4.- Universidad ID\n\t5.- Correo\n\t6.- Salir\n");
                System.out.print("Tu opcion: ");
                op = sc.nextInt();
                switch (op) {
                    case 1: 
                    {
                        System.out.print("Introduce el codigo que desea buscar: ");
                        codigo = sc.nextInt();
                        for(int i = 0; i < personas.size(); i++)
                        {
                            if(personas.get(i).getCodigo() == codigo)
                            {
                                encontrado = true;
                                System.out.println("Codigo encontrado");
                                System.out.println(personas.get(i));
                            }
                        }
                        for(int j = 0; j < personasUniversidad.size(); j++)
                        {
                            if(personasUniversidad.get(j).getCodigo() == codigo)
                            {
                                encontrado = true;
                                System.out.println("Codigo encontrado");
                                System.out.println(personasUniversidad.get(j));
                            }
                        }
                        if(!encontrado)
                        {
                            System.err.println("ERROR. El codigo "+codigo+" no se ha encontrado");
                        }
                        encontrado = false;
                    }
                    break;
                    case 2:
                    {
                        System.out.print("Escriba el nombre que desea buscar: ");
                        nombre = sc.nextLine();
                        for(int i = 0; i < personas.size(); i++)
                        {
                            if(personas.get(i).getNombre().equals(nombre))
                            {
                                encontrado = true;
                                System.out.println("Nombre encontrado");
                                System.out.println(personas.get(i));
                            }
                        }
                        for(int j = 0; j < personasUniversidad.size(); j++)
                        {
                            if(personasUniversidad.get(j).getNombre().equals(nombre))
                            {
                                encontrado = true;
                                System.out.println("Nombre encontrado");
                                System.out.println(personasUniversidad.get(j));
                            }
                        }
                        if(!encontrado)
                        {
                            System.err.println("ERROR. El nombre "+nombre+" no se ha encontrado");
                        }
                        encontrado = false;
                    }
                    break;
                    case 3:
                    {
                        System.out.print("Introduce la edad que desea buscar: ");
                        edad = sc.nextInt();
                        for(int i = 0; i < personas.size(); i++)
                        {
                            if(personas.get(i).getEdad() == edad)
                            {
                                encontrado = true;
                                System.out.println("Edad encontrada");
                                System.out.println(personas.get(i));
                            }
                        }
                        for(int j = 0; j < personasUniversidad.size(); j++)
                        {
                            if(personasUniversidad.get(j).getEdad() == edad)
                            {
                                encontrado = true;
                                System.out.println("Edad encontrada");
                                System.out.println(personasUniversidad.get(j));
                            }
                        }
                        if(!encontrado)
                        {
                            System.err.println("ERROR. Edad "+edad+" no encontrada");
                        }
                        encontrado = false;
                    }
                    break;
                    case 4:
                    {
                        System.out.print("Indica el ID de la universidad que desea buscar: ");
                        universidadId = sc.nextInt();
                        for(int j = 0; j < personasUniversidad.size(); j++)
                        {
                            if(personasUniversidad.get(j).getUniversidadId() == universidadId)
                            {
                                encontrado = true;
                                System.out.println("ID universidad encontrada");
                                System.out.println(personasUniversidad.get(j));
                            }
                        }
                        if(!encontrado)
                        {
                            System.err.println("ERROR. Universidad ID "+universidadId+" no encontrada");
                        }
                        encontrado = false;
                    }
                    break;
                    case 5:
                    {
                        System.out.print("Indica el correo que desea buscar (formato: alguien@example.com): ");
                        correo = sc.nextLine();
                        {
                            for(int j = 0; j < personasUniversidad.size(); j++)
                            {
                                encontrado = true;
                                System.out.println("Correo encontrado");
                                System.out.println(personasUniversidad.get(j));
                            }
                        }
                        if(!encontrado)
                        {
                            System.err.println("ERROR. No se ha encontrado el correo "+correo);
                        }
                        encontrado = false;
                    }
                    break;
                    case 6:
                    {
                        System.out.println("Volviendo al menu principal");
                        salir = true;
                    }
                    break;
                    default: System.err.println("ERROR. Opcion no valida");
                }
            }while(!salir);
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        catch (ClassNotFoundException notFoundException)
        {
            notFoundException.printStackTrace();
        }
        finally
        {
            if(objectInputStream2 != null)
            {
                try 
                {
                    objectInputStream2.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(objectInputStream != null)
            {
                try 
                {
                    objectInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileInputStream2 != null)
            {
                try 
                {
                    fileInputStream2.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
            if(fileInputStream != null)
            {
                try 
                {
                    fileInputStream.close();    
                } 
                catch (IOException ioException) 
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public static class Persona 
    {
        private int codigo, edad;
        private String nombre;

        
        public Persona(int codigo, String nombre, int edad) 
        {
            this.codigo = codigo;
            this.edad = edad;
            this.nombre = nombre;
        }


        public int getCodigo() 
        {
            return codigo;
        }


        public void setCodigo(int codigo) 
        {
            this.codigo = codigo;
        }


        public int getEdad() 
        {
            return edad;
        }


        public void setEdad(int edad) 
        {
            this.edad = edad;
        }


        public String getNombre() 
        {
            return nombre;
        }


        public void setNombre(String nombre) 
        {
            this.nombre = nombre;
        }


        @Override
        public String toString() 
        {
            return "Codigo: " + codigo + "\nNombre: " + nombre + "\nEdad:" + edad + "";
        }
    }

    public static class PersonaUniversidad extends Persona 
    {
        private int universidadId;
        private String correo;

        public PersonaUniversidad(int codigo, String nombre, int edad, int universidadId, String correo) 
        {
            super(codigo, nombre, edad);
            this.universidadId = universidadId;
            this.correo = correo;
        }

        public int getUniversidadId() 
        {
            return universidadId;
        }

        public void setUniversidadId(int universidadId) 
        {
            this.universidadId = universidadId;
        }

        public String getCorreo() 
        {
            return correo;
        }

        public void setCorreo(String correo) 
        {
            this.correo = correo;
        }

        @Override
        public String toString() 
        {
            return "Codigo: "+getCodigo()+"\nNombre: "+getNombre()+"\nEdad: "+getEdad()+"\nUniversidadId: " + universidadId + "\nCorreo: " + correo + "";
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
        do{
            System.out.println("Seleccione una opcion:");
            System.out.println("\t1.- Guardar datos de la persona\n\t2.- Leer datos y guardar en tipo primitivo"+
                "\n\t3.- Leer datos de tipo primitivo y guardar en un objeto\n\t4.- Buscar datos de la persona\n\t5.- Salir\n");
            System.out.print("Tu opcion: ");
            op = sc.nextInt();
            
            switch (op) {
                case 1: introducirDatos(sc); break;
                case 2: guardarDatosPrimitivos(sc); break;
                case 3: guardarLista(sc); break;
                case 4: buscarDatos(sc); break;
                case 5: System.out.println("Ha finalizado el programa"); sc.close(); salir = true; break;
                default: System.out.println("Opcion no valida");
            }
        }while(!salir);
    }
}
