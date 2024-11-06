package Principal.controlador;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uagrm.ficct.ejemplo2.ArbolAVL;
import com.uagrm.ficct.ejemplo2.ArbolB;
import com.uagrm.ficct.ejemplo2.ArbolBinario;
import com.uagrm.ficct.ejemplo2.ArbolMV;
import com.uagrm.ficct.ejemplo2.excepciones.OrdenInvalidoException;

import testArbol4.iArbol;

//@RestController
@Controller

@RequestMapping("/api/arbol")
public class ArbolController {
	//private ArbolBinario<String, String> arbol; // Asumiendo que tu árbol binario es genérico
	private iArbol<String, String> arbol;



    public ArbolController() {
        // this.arbol = new ArbolBinario<>(); // Inicializa tu árbol
     }


    @PostMapping("/seleccionar")
    public String seleccionarArbol(@RequestParam String tipoArbol, Model model) throws OrdenInvalidoException {
        // Cambia la implementación según la elección del usuario
        String vista;

        switch (tipoArbol) {
            case "avl":
                this.arbol = new ArbolAVL<>(); // Usa la implementación AVL
                vista = "vista2/index";
                break;

            case "binario":
                this.arbol = new ArbolBinario<>(); // Usa la implementación de árbol binario
                vista = "vista1/index";
                break;

            case "arbolB":
                this.arbol = new ArbolB<>(4); // Usa la implementación de árbol B
                vista = "vista4/index";
                break;
            default:
                this.arbol = new ArbolMV<>(4); // Por defecto usa la implementación ArbolMVias
                vista = "vista3/index";
                break;
        }

        return vista;
    }



    @GetMapping("/vista1")
    public String vista1(Model model) {
        // Puedes agregar lógica específica para vista1 aquí
        return "vista1/index"; // Nombre de la plantilla
    }

    @GetMapping("/vista2")
    public String vista2(Model model) {
        // Puedes agregar lógica específica para vista2 aquí
        return "vista2/index"; // Nombre de la plantilla
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tipoArbol", this.arbol instanceof ArbolAVL ? "AVL" : "Binario");
        return "index";  // nombre de la plantilla sin la extensión .html
    }

    @GetMapping("/mostrar")
    public String mostrar() {
    	return "vista1/index";
    }



    @PostMapping("/buscar")
    @ResponseBody // Indica que el resultado es el cuerpo de la respuesta
    public String buscarPorPrecio(@RequestParam String enter) {
    String resultado = arbol.buscar(enter); // Método para buscar en el árbol

    if (resultado != null) {
        return resultado; // Devuelve solo el resultado
    } else {
        return "error chico"; // Devuelve un mensaje de error
    }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<String>> listarNombres() {
    List<String> nombresList = arbol.recorridoEnInOrden(); // Obtener nombres del árbol
    return ResponseEntity.ok(nombresList); // Devolver la lista como respuesta JSON
    }


     @PostMapping("/listado")
     public ResponseEntity<Void> listado() {
         arbol.insertar("Arroz", "Precio : 15 bs -> Descripcion : abarrotes");
         arbol.insertar("Coca Cola", "Precio : 20 bs -> Descripcion : bebidas");
         arbol.insertar("Harina", "Precio : 16 bs -> Descripcion : abarrotes");
         arbol.insertar("Pan Molido", "Precio : 8 bs -> Descripcion : para hacer milaneza");
         arbol.insertar("Jabon", "Precio : 5 bs -> Descripcion : Limpieza");
         arbol.insertar("Fanta", "Precio : 25 bs -> Descripcion : bebidas");
         arbol.insertar("Azucar", "Precio : 10 bs -> Descripcion : abarrotes");
         arbol.insertar("Aceite", "Precio : 13 bs -> Descripcion : abarrote");
         arbol.insertar("Mermelada de frutilla", "Precio : 18 bs -> Descripcion : abarrotes");
         arbol.insertar("Shampoo", "Precio : 25 bs -> Descripcion : limpieza");
         arbol.insertar("Ace OMO", "Precio : 8 bs -> Descripcion : Limpieza");
         arbol.insertar("Pollo", "Precio : 16 bs -> Descripcion : Carnes/ embutidos");
         arbol.insertar("Carne", "Precio : 32 bs -> Descripcion : Carnes/ embutidos");
         arbol.insertar("Higado", "Precio : 24 bs -> Descripcion : Carnes/ embutidos");
         arbol.insertar("Corazon", "Precio : 30 bs -> Descripcion : Carnes/ embutidos");


         return ResponseEntity.ok().build(); // Retorna una respuesta vacía 200 OK
     }

     @PostMapping("/insertar")
     public ResponseEntity<Void> insertarProducto(@RequestParam String producto,@RequestParam String descripcion) {
        arbol.insertar(producto,descripcion);
        return ResponseEntity.ok().build();
     }

     //@ResponseBody // Indica que el resultado es el cuerpo de la respuesta
     @PostMapping("/eliminar")
     @ResponseBody
     public String eliminarProducto(@RequestParam String productoEliminar) {
     String resultado = arbol.eliminar(productoEliminar); // Método para buscar en el árbol

     if (resultado != null) {
         return resultado; // Devuelve solo el resultado
     } else {
         return "No existe el producto."; // Devuelve un mensaje de error
     }
     }


    @Controller
    public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

        @RequestMapping("/error")
        public String handleError() {
            // Puedes devolver una vista específica o un mensaje
            return "error"; // Esto puede ser una vista de error en tu carpeta de plantillas
        }

        public String getErrorPath() {
            return "/error";
        }
    }

    //---------------


}

 /*@GetMapping("/listar")
    public String listarNombres(Model model) {
        List<String> nombresList = arbol.recorridoEnInOrden(); // Método para listar los nombres en el árbol

    	model.addAttribute("nombresList",nombresList);

        return "index";
    }*/
/*@PostMapping("/listado")
     public String listado() {
     	arbol.insertar("Arroz", "Precio : 15bs -> Descripcion : abarrotes");
	 	arbol.insertar("Coca Cola", "Precio : 20bs -> Descripcion : bebidas");
	 	arbol.insertar("Harina", "Precio : 16bs -> Descripcion : abarrotes");
	 	arbol.insertar("Pan Molido", "Precio : 8bs -> Descripcion : para hacer milaneza");
	 	arbol.insertar("agua santa", "Precio : 10bs -> Descripcion : bebidas");
        // List<String> nombresList = arbol.recorridoPorNiveles(); // Método para listar los nombres en el árbol
         return "index";
     }*/
/*  @PostMapping("/buscar")
    public String buscarPorPrecio(@RequestParam  String enter, Model model) {
        String resultado = arbol.buscar(enter); // Método para buscar en el árbol ResponseEntity<String>

        if (resultado != null) {

          model.addAttribute("respuesta", resultado);
        } else {

           model.addAttribute("respuesta", "error chico");
        }
        return "redirect:/api/arbol/listar";

    }*/
/*
@GetMapping("/reco")
public ResponseEntity<List<Integer>> recorrido() {
   // List<Integer> nombresList = arbol.recorridoEnInOrden(); // Método para listar los nombres en el árbol
    List<Integer> nombresList=new ArrayList<>();
    return ResponseEntity.ok(nombresList);
}
@GetMapping("/recoo")
public ResponseEntity<List<Integer>> recorridoo() {
    //List<Integer> nombresList = arbol.recorridoEnPreOrden(); // Método para listar los nombres en el árbol
    List<Integer> nombresList=new ArrayList<>();
    return ResponseEntity.ok(nombresList);
}




@GetMapping("/reco")
public ResponseEntity<List<Integer>> recorrido() {
   // List<Integer> nombresList = arbol.recorridoEnInOrden(); // Método para listar los nombres en el árbol
    List<Integer> nombresList=new ArrayList<>();
    return ResponseEntity.ok(nombresList);
}
@GetMapping("/recoo")
public ResponseEntity<List<Integer>> recorridoo() {
    //List<Integer> nombresList = arbol.recorridoEnPreOrden(); // Método para listar los nombres en el árbol
    List<Integer> nombresList=new ArrayList<>();
    return ResponseEntity.ok(nombresList);
}
/*   @GetMapping("/listado")
public ResponseEntity<List<Integer>> listado() {
    arbol.insertar(40, "henry");
    arbol.insertar(36, "diego");
    arbol.insertar(69, "sandra");
    arbol.insertar(50, "kasandra");

    arbol.insertar(34, "emily");
    arbol.insertar(38, "maggui");
    arbol.insertar(45, "leo");
    arbol.insertar(55, "santi");
    arbol.insertar(53, "harry");
    arbol.insertar(48, "carlos");

    arbol.insertar(42, "piter");
    arbol.insertar(100, "esthefania");
    arbol.insertar(96, "belen");
    arbol.insertar(76, "fabiana");
    arbol.insertar(78, "alizon");
    List<Integer> nombresList = arbol.recorridoPorNiveles(); // Método para listar los nombres en el árbol
    return ResponseEntity.ok(nombresList);
}*/
// @GetMapping("/listado")
// public ResponseEntity<List<String>> listado() {
// 	arbol.insertar("Arroz", "Precio : 15bs -> Descripcion : abarrotes");
// 	arbol.insertar("Coca Cola", "Precio : 20bs -> Descripcion : bebidas");
// 	arbol.insertar("Harina", "Precio : 16bs -> Descripcion : abarrotes");
// 	arbol.insertar("Pan Molido", "Precio : 8bs -> Descripcion : para hacer milaneza");
// 	arbol.insertar("agua santa", "Precio : 10bs -> Descripcion : bebidas");
//     List<String> nombresList = arbol.recorridoPorNiveles(); // Método para listar los nombres en el árbol
//     return ResponseEntity.ok(nombresList);
// }
/*   @GetMapping("/listado")
public ResponseEntity<List<Integer>> listado() {
    arbol.insertar(40, "henry");
    arbol.insertar(36, "diego");
    arbol.insertar(69, "sandra");
    arbol.insertar(50, "kasandra");

    arbol.insertar(34, "emily");
    arbol.insertar(38, "maggui");
    arbol.insertar(45, "leo");
    arbol.insertar(55, "santi");
    arbol.insertar(53, "harry");
    arbol.insertar(48, "carlos");

    arbol.insertar(42, "piter");
    arbol.insertar(100, "esthefania");
    arbol.insertar(96, "belen");
    arbol.insertar(76, "fabiana");
    arbol.insertar(78, "alizon");
    List<Integer> nombresList = arbol.recorridoPorNiveles(); // Método para listar los nombres en el árbol
    return ResponseEntity.ok(nombresList);
}*/
// @GetMapping("/listado")
// public ResponseEntity<List<String>> listado() {
// 	arbol.insertar("Arroz", "Precio : 15bs -> Descripcion : abarrotes");
// 	arbol.insertar("Coca Cola", "Precio : 20bs -> Descripcion : bebidas");
// 	arbol.insertar("Harina", "Precio : 16bs -> Descripcion : abarrotes");
// 	arbol.insertar("Pan Molido", "Precio : 8bs -> Descripcion : para hacer milaneza");
// 	arbol.insertar("agua santa", "Precio : 10bs -> Descripcion : bebidas");
//     List<String> nombresList = arbol.recorridoPorNiveles(); // Método para listar los nombres en el árbol
//     return ResponseEntity.ok(nombresList);
// }

/*
 * private ArbolBinario<Producto> arbol; // Asumiendo que tu árbol binario es genérico

    public ArbolController() {
        this.arbol = new ArbolBinario<>(); // Inicializa tu árbol
    }

    @PostMapping("/insertar")
    public ResponseEntity<String> insertarProducto(@RequestBody Producto producto) {
        arbol.insertar(producto); // Método para insertar en el árbol
        return ResponseEntity.ok("Producto insertado");
    }

    @GetMapping("/buscar/{precio}")
    public ResponseEntity<Producto> buscarPorPrecio(@PathVariable double precio) {
        Producto resultado = arbol.buscarPorPrecio(precio); // Método para buscar en el árbol
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra
        }
    }*/



/*public class Producto {
    private String nombre;
    private double precio;

    // Constructor, getters y setters
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}*/


/*Configuraste un proyecto Spring Boot.
Creaste un controlador REST con métodos para insertar y buscar productos.
Definiste la clase Producto y aseguraste que tu árbol binario tenga los métodos necesarios.*/



