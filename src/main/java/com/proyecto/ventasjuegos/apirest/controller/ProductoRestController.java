package com.proyecto.ventasjuegos.apirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.proyecto.ventasjuegos.apirest.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.proyecto.ventasjuegos.apirest.entity.Producto;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class ProductoRestController {
    @Autowired
    private ProductoServiceImpl productoService;
//================MOSTRAR PRODUCTOS =================================
    @GetMapping({"/productos", "/"})
    public List<Producto> index() {
        return productoService.finAll();
    }
//=================MOSTRAR PRODUCTOS POR ID ==========================
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
       // Optional<Producto> producto = null;
    	Producto producto = null;
        Map<String, Object> response = new HashMap<>();
        try {
            //producto = productoService.findById(id);
        	producto = productoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar en base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (producto == null) {
            response.put("mensaje", "El producto con ID: " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    
 
//===============CREAR PRODUCTOS =====================================
    @PostMapping("/productos")
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        Producto productoNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            productoNew = productoService.save(producto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El producto se ha creado con exito");
        response.put("producto", productoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


//============ACTUALIZAR PRODUCTOS==========================================
@PutMapping("/productos/{id}")
public ResponseEntity<?> update(@RequestBody Producto producto,@PathVariable Long id){
	
	Producto productoUpdate = productoService.findById(id);
	Map<String,Object>  response = new HashMap<>();
	if(productoUpdate == null) {
        response.put("mensaje","No existe el registro con id:"+id);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
    }
	try {
		
		productoUpdate.setDescripcion(producto.getDescripcion());
		productoUpdate.setCodigo_producto(producto.getCodigo_producto());
		productoUpdate.setNombre(producto.getNombre());
		productoUpdate.setPrecio(producto.getPrecio());
		productoUpdate.setCantidad(producto.getCantidad());
		productoUpdate.setTipo(producto.getTipo());
		
		
		if (producto.getImagen() != null) {
        	String nombreFotoAnterior = producto.getImagen();
            //verificamos que el cliente tenga registrado una imagen
            if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
                //preparamos la ruta a la imagen guardada
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                //verificamos que el archivo existe y se pueda leer
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    //borramos la imagen
                    archivoFotoAnterior.delete();
                }
            }
            producto.setImagen(producto.getImagen());
		}
			productoService.save(productoUpdate);
		
		
		
	}catch (DataAccessException e){
		response.put("mensaje","Error con base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	response.put("mensaje","El producto ha sido actualizado con éxito");
    response.put("producto", productoUpdate);
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

}


//=================PARA SUBIR IMAGENES DE PRODUCTOS ========================


//como vamos a subir un arhivo es crear un archivo nuevo por eso usamos esta anotacion
	@PostMapping("/productos/uploads")
	//para archivos se maneja una anotacion especial llamada @RequestParam
	//multipartfile es el manejador de archivos en java
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,@RequestParam("id") long id){
		Map<String,Object>  response = new HashMap<>();
		Producto producto = productoService.findById(id); //busco al cliente con el id
		//pregunto si esta vacio
		if(!archivo.isEmpty()) {
			//guardamos el nombre del archivo en esta variable
			//debido a que no nos permite grabar archivos con el mismo nombre le vamos agregar
			//una clase especial que genera id random para las imagenes
			//String nombreArchivo = archivo.getOriginalFilename();
			//se remplaza por este codigo a continuacion:
			
			String nombreArchivo =  UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
			//se guarda el archivo en esta ruta
			 //guardamos la ruta completa uploads/nombredelaimagen lo guardamos en
        //una variable de tipo path que es de java.io
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			try {
				//copiamos el archivo fisico a la ruta que definimos en Path
				Files.copy(archivo.getInputStream(), rutaArchivo);
			}catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			//asi como esta las imagenes se guardan , si se ejecuta de nuevo guarda por lo que no
			//es optimo por lo que vamos hacer unos cambios
			
			//codigo para eliminar imagenes viejas que se desea reemplazar antes de guardar
			//la nueva
			String nombreFotoAnterior = producto.getImagen();
			            //verificamos que el cliente tenga registrado una imagen
			            if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
			                //preparamos la ruta a la imagen guardada
			                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
			                File archivoFotoAnterior = rutaFotoAnterior.toFile();
			                //verificamos que el archivo existe y se pueda leer
			                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
			                    //borramos la imagen
			                    archivoFotoAnterior.delete();
			                }
			            }
			
			 //guardamos el nombre de la imagen
				producto.setImagen(nombreArchivo);
	            //registramos en base de datos
	            productoService.save(producto);
	            response.put("cliente", producto);
	            response.put("mensaje","Imagen subida correctamente :"+nombreArchivo);
			}
		
		    //en el postman para enviar en el key se coloca archivo y el id que fueron los 
			//que definimos al principio de este codigo se coloca en  form-data  la que tiene 
			//archivo se coloca tipo File y en value se sube la foto y en id se coloca uno existente
		    //para agregar imagenes mas pesadas se agregaron lineas de codigo en el properties
		    //en el postman se observan errores si envias archivos con el mismo nombre
			//por lo que podemos borrar los archivos que se colocan en uploads
			
		
		
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}



//=============subir imagen de producto==============================

@GetMapping("/Uploads/img/{nombreImagen:.+}")
public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen) {
    Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
    Resource recurso = null;
    try {
        //codigo para acceder al archivo por url
        recurso = new UrlResource(rutaArchivo.toUri());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    if (!recurso.exists() && !recurso.isReadable()) {
        throw new RuntimeException("no se puede cargar a la imagen: " + nombreImagen);
    }

    HttpHeaders cabecera = new HttpHeaders();
    cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
    return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
}

//================PARA ELIMINAR PRODUCTOS ========================
@DeleteMapping("/productos/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
   Producto productoBorrado = productoService.findById(id);
    Map<String,Object>  response = new HashMap<>();

    if(productoBorrado == null) {
        response.put("mensaje","No existe el registro con id:"+id);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
    }

    try {
    	if(productoBorrado.getImagen()!=null) {
            String nombreFotoAnterior = productoBorrado.getImagen();
            //verificamos que el cliente tenga registrado una imagen
            if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
                //preparamos la ruta a la imagen guardada
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                //verificamos que el archivo existe y se pueda leer
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    //borramos la imagen
                    archivoFotoAnterior.delete();
                }
            }

        }

        productoService.delete(id);

    } catch (DataAccessException e) {
        response.put("mensaje", "Error al realizar en base de datos");
        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje","El vehiculo ha sido eliminado con éxito");
    response.put("producto", productoBorrado);
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
}
}
  /*  @PutMapping("/productos/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
        Producto productoUpdate = productoService.update(producto, id);
        Map<String, Object> response = new HashMap<>();
        if (productoUpdate == null) {
            response.put("mensaje", "no existe el registro con id: " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            productoUpdate.setNombre(producto.getNombre());
            productoUpdate.setCodigo_producto(producto.getCodigo_producto());
            //productoUpdate.setPrecio(producto.getPrecio());
            productoUpdate.setStock(producto.getStock());
            if (producto.getImagen() != null) {
                String nombreFotoAnterior = producto.getImagen();
                //verificamos que el cliente tenga registrado una imagen
                if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                    //preparamos la ruta a la imagen guardada
                    Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                    File archivoFotoAnterior = rutaFotoAnterior.toFile();
                    //verificamos que el archivo existe y se pueda leer
                    if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                        //borramos la imagen
                        archivoFotoAnterior.delete();
                    }
                }
                productoUpdate.setImagen(producto.getImagen());
            }
            productoService.save(productoUpdate);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error con la base de datos");
            response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El producto se actualizo correctamente");
        response.put("producto", productoUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
*/
/*
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Producto> productoBorrado = productoService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (productoBorrado == null) {
            response.put("mensaje", "No existe registro con id : " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            if (productoBorrado.get().getImagen() != null) {
                String nombreFotoAnterior = productoBorrado.get().getImagen();
                //verificamos que el cliente tenga registrado una imagen
                if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                    //preparamos la ruta a la imagen guardada
                    Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                    File archivoFotoAnterior = rutaFotoAnterior.toFile();
                    //verificamos que el archivo existe y se pueda leer
                    if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                        //borramos la imagen
                        archivoFotoAnterior.delete();
                    }
                }
                productoService.delete(id);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "error con la base de datos");
            response.put("producto", productoBorrado);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        response.put("mensaje", "El producto ha sido eliminado con éxito");
        response.put("producto", productoBorrado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
*/
    //como vamos a subir un arhivo es crear un archivo nuevo por eso usamos esta anotacion
  /*  @PostMapping("/productos/uploads")
    //para archivos se maneja una anotacion especial llamada @RequestParam
    //multipartfile es el manejador de archivos en java
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Producto> producto = productoService.findById(id); //busco al cliente con el id
        //pregunto si esta vacio
        if (!archivo.isEmpty()) {
            //guardamos el nombre del archivo en esta variable
            //debido a que no nos permite grabar archivos con el mismo nombre le vamos agregar
            //una clase especial que genera id random para las imagenes
            //String nombreArchivo = archivo.getOriginalFilename();
            //se remplaza por este codigo a continuacion:

            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            //se guarda el archivo en esta ruta
            //guardamos la ruta completa uploads/nombredelaimagen lo guardamos en
            //una variable de tipo path que es de java.io
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            try {
                //copiamos el archivo fisico a la ruta que definimos en Path
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //asi como esta las imagenes se guardan , si se ejecuta de nuevo guarda por lo que no
            //es optimo por lo que vamos hacer unos cambios

            //codigo para eliminar imagenes viejas que se desea reemplazar antes de guardar
            //la nueva
            String nombreFotoAnterior = producto.get().getImagen();
            //verificamos que el cliente tenga registrado una imagen
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                //preparamos la ruta a la imagen guardada
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                //verificamos que el archivo existe y se pueda leer
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    //borramos la imagen
                    archivoFotoAnterior.delete();
                }
            }

            //guardamos el nombre de la imagen
            producto.get().setImagen(nombreArchivo);
            //registramos en base de datos
            productoService.save(producto.get());
            response.put("cliente", producto);
            response.put("mensaje", "Imagen subida correctamente :" + nombreArchivo);
        }

        //en el postman para enviar en el key se coloca archivo y el id que fueron los
        //que definimos al principio de este codigo se coloca en  form-data  la que tiene
        //archivo se coloca tipo File y en value se sube la foto y en id se coloca uno existente
        //para agregar imagenes mas pesadas se agregaron lineas de codigo en el properties
        //en el postman se observan errores si envias archivos con el mismo nombre
        //por lo que podemos borrar los archivos que se colocan en uploads


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
*/
