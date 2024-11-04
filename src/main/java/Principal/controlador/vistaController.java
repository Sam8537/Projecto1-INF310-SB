package Principal.controlador;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uagrm.ficct.ejemplo2.ArbolBinario;

import testArbol4.iArbol;

@Controller

@RequestMapping("/api/arbol")
public class vistaController {


		private iArbol<String, String> arbol;

	    public vistaController() {
	        this.arbol = new ArbolBinario<>();
	    }


	  /*  @PostMapping("/insertar")
	    public ResponseEntity<String> insertarProducto(@RequestBody nombres N) {
	      //  arbol.insertar(N.getId(),N.getNombre()); // Método para insertar en el árbol
	        return ResponseEntity.ok("nombre insertado");
	    }*/

	  /*  @GetMapping("/vista1")
	    public String mostrar() {

	            return "vista1.index";


	    }*/
}
