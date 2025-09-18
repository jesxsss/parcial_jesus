import controlador.EstudianteDAO;
import modelo.Estudiante;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EstudianteDAO dao = new EstudianteDAO();
        int opcion;

        do {
            System.out.println("\n===== MENU ESTUDIANTES =====");
            System.out.println("1. Insertar Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("4. Consultar todos los estudiantes");
            System.out.println("5. Consultar estudiante por email");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                    String estado = sc.nextLine();
                    dao.insertar(new Estudiante(nombre, apellido, correo, edad, estado));
                    break;

                case 2:
                    System.out.print("Correo del estudiante a actualizar: ");
                    correo = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Nuevo apellido: ");
                    apellido = sc.nextLine();
                    System.out.print("Nueva edad: ");
                    edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo estado civil: ");
                    estado = sc.nextLine();
                    dao.actualizar(new Estudiante(nombre, apellido, correo, edad, estado));
                    break;

                case 3:
                    System.out.print("Correo del estudiante a eliminar: ");
                    correo = sc.nextLine();
                    dao.eliminar(correo);
                    break;

                case 4:
                    dao.listarTodos();
                    break;

                case 5:
                    System.out.print("Ingrese correo: ");
                    correo = sc.nextLine();
                    dao.consultarPorCorreo(correo);
                    break;
            }
        } while (opcion != 6);

        System.out.println(" Programa finalizado.");
    }
}
