package Ex5Test;

import ex5.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestEx5 {

    @ParameterizedTest
    @CsvSource({"1,hola"})
    void PUT_En_Vacia(String clave, Object valor) {
        HashTable hashtable = new HashTable();
        hashtable.put(clave,valor);
        hashtable.put("12","holaaa");
        int numero = clave.hashCode()%16;
        System.out.println(hashtable.toString());
        Assertions.assertEquals("\n bucket[1] = [12, holaaa]\n" +" bucket[17] = ["+clave+", "+valor+"]",hashtable.toString());
        int expectedSize = 32;
        System.out.println(hashtable.size());
        Assertions.assertEquals(expectedSize,hashtable.size());
    }
    @ParameterizedTest
    @CsvSource({"1,M05"})
    void PUT_En_Lleno(String clave, Object valor) {
        HashTable hashtable = new HashTable();
        hashtable.put(clave,valor);
        hashtable.put("12","Pruebas");
        hashtable.put("23","Unitarias");
        int numero1 = clave.hashCode()%16;

        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [23, Unitarias]\n" + " bucket[33] = [12, Pruebas]\n" + " bucket[49] = [1, M05]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expectedSize = 64;
        System.out.println(hashtable.size());
        Assertions.assertEquals(expectedSize,hashtable.size());
    }


    @org.junit.jupiter.api.Test
    void Actualizar_Elemento_Sin_Colision() {
        HashTable hashtable = new HashTable();
        hashtable.put("1","Elemento 1");
        hashtable.put("1","Elemento 1.1");
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, Elemento 1.1]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 1;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }

    @org.junit.jupiter.api.Test
    void Get_Sin_Colision_Tabla_Vacia(){
        HashTable hashtable = new HashTable();
        hashtable.put("1", "Elemento 1");
        System.out.println(hashtable.toString());
        String bucket = "Elemento 1";
        Assertions.assertEquals(bucket,hashtable.get("1").toString());
    }
    @org.junit.jupiter.api.Test
    void Get_Elemento_No_Existente(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola"); //Añadimos uno para hacer pruebas
        Assertions.assertNull(hashtable.get("2"));
    }
    @org.junit.jupiter.api.Test
    void Drop_Elemento_Sin_Colision(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola");
        hashtable.put("2","adios");
        hashtable.drop("1");
        String bucket = "\n bucket[2] = [2, adios]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 1;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }
    @org.junit.jupiter.api.Test
    void Drop_Elemento_No_Existe(){
        HashTable hashtable = new HashTable();
        Assertions.assertEquals("",hashtable.toString());
    }
//Eliminar un elements que no existeix, tot i que la seva posició està ocupada per un altre que no col·lisiona.
    @org.junit.jupiter.api.Test
    void Drop_Elemento_No_Existe_Posicion_Ocupada_Por_Otra_No_Colisiona(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola");
        hashtable.drop("12");
        String expected = "\n bucket[1] = [1, hola]";
        Assertions.assertEquals(expected,hashtable.toString());
    }

    @org.junit.jupiter.api.Test
    void Drop_Elemento_No_Existe_Posicion_Ocupada_Por_3_Elementos_Colisionants(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola");
        hashtable.put("12","adios");
        hashtable.put("23","chao");
        hashtable.drop("34");
        String expected = "\n bucket[1] = [1, hola] -> [12, adios] -> [23, chao]";
        Assertions.assertEquals(expected,hashtable.toString());
    }
}
