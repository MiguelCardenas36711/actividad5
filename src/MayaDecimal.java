import java.util.*;

public class MayaDecimal {

    //=========================================
    // Convierte una línea maya a valor entero
    //=========================================
    public static int valorLinea(String linea) {
        if (linea.equals("0") || linea.isEmpty()) return 0;
        int valor = 0;
        for (char c : linea.toCharArray()) {
            if (c == '.') valor += 1;
            else if (c == '_') valor += 5;
        }
        return valor;
    }

    //===================================================
    // Convierte número maya (lista de líneas) a decimal
    //===================================================
    public static int mayaADecimal(List<String> lineas) {
        int total = 0;
        int potencia = lineas.size() - 1;
        for (String linea : lineas) {
            int valor = valorLinea(linea);
            total += valor * Math.pow(20, potencia);
            potencia--;
        }
        return total;
    }

    //============================================
    // Convierte valor entero (0–19) a línea maya
    //============================================
    public static String decimalALinea(int valor) {
        if (valor == 0) return "0";
        int barras = valor / 5;
        int puntos = valor % 5;
        return "_".repeat(barras) + ".".repeat(puntos);
    }

    //==================================================
    // Convierte número decimal a maya (lista de líneas)
    //==================================================
    public static List<String> decimalAMaya(int numero) {
        List<String> maya = new ArrayList<>();
        if (numero == 0) {
            maya.add("0");
            return maya;
        }
        while (numero > 0) {
            int resto = numero % 20;
            maya.add(0, decimalALinea(resto)); // <== Insertar al inicio (orden de arriba hacia abajo)
            numero /= 20;
        }
        return maya;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Traductor Maya <-> Decimal ===");
        System.out.println("1. Maya => Decimal");
        System.out.println("2. Decimal => Maya");
        System.out.print("Seleccione una opción (1 o 2): ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // <== limpiar salto de línea

        if (opcion == 1) {
            // Maya => Decimal:
            List<String> numeroMaya = new ArrayList<>();
            System.out.println("\nIngrese las líneas del número maya (de arriba hacia abajo):");
            System.out.println("Use '.' para puntos, '_' para barras, '0' para concha (cero).");
            System.out.println("Presione Enter en una línea vacía para terminar.");

            while (true) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) break;
                numeroMaya.add(linea);
            }

            int resultado = mayaADecimal(numeroMaya);
            System.out.println("Valor decimal: " + resultado);

        } else if (opcion == 2) {
            // Decimal => Maya:
            System.out.print("\nIngrese un número decimal: ");
            int numero = scanner.nextInt();

            List<String> resultado = decimalAMaya(numero);
            System.out.println("Representación maya (de arriba hacia abajo):");
            for (String linea : resultado) {
                System.out.println(linea);
            }

        } else {
            System.out.println("Opción no válida.");
        }
    }
}
