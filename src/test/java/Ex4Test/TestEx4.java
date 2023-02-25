package Ex4Test;

import ex4.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestEx4 {

    @ParameterizedTest
    @CsvSource({"1,hola", "2,adios", "3,holita","3,holita"})
    void PUT_En_Vacia(String clave, String valor) {
        HashTable hashtable = new HashTable();
        hashtable.put(clave,valor);
        int numero = clave.hashCode()%16;
        System.out.println(hashtable.toString());
        String bucket = "\n bucket["+numero+"] = ["+clave+", "+valor+"]";
        Assertions.assertEquals(bucket,hashtable.toString());
        int expected = 1;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }
    @ParameterizedTest
    @CsvSource({"1,hola,2,adios", "3,casa,5,viva"})
    void PUT_En_Lleno(String clave, String valor, String clave2, String valor2) {
        HashTable hashtable = new HashTable();
        hashtable.put(clave,valor);
        hashtable.put(clave2,valor2);
        int numero1 = clave.hashCode()%16;
        int numero2 = clave2.hashCode()%16;
        System.out.println(hashtable.toString());
        String bucket = "\n bucket["+numero1+"] = ["+clave+", "+valor+"]\n bucket["+numero2+"] = ["+clave2+", "+valor2+"]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 2;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }

    @org.junit.jupiter.api.Test
    void PUT_Con_Una_Colision() {
        HashTable hashtable = new HashTable();
        System.out.println(hashtable.getCollisionsForKey("1",3));
        hashtable.put("1","Elemento 1");
        hashtable.put("12","Elemento 2");
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, Elemento 1] -> [12, Elemento 2]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 2;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }

    @org.junit.jupiter.api.Test
    void PUT_Con_Dos_Colision() {
        HashTable hashtable = new HashTable();
        System.out.println(hashtable.getCollisionsForKey("1",3));
        hashtable.put("1",true);
        hashtable.put("12",false);
        hashtable.put("23",true);
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, true] -> [12, false] -> [23, true]";
        Assertions.assertEquals(bucket,hashtable.toString());
        int expected = 3;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
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
    void Actualizar_Elemento_Con_Colision() {
        HashTable hashtable = new HashTable();
        hashtable.put("1",true);
        hashtable.put("12",true);
        hashtable.put("12",false);
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, true] -> [12, false]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 2;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }

    @org.junit.jupiter.api.Test
    void Actualizar_Elemento_Con_Dos_Colisiones() {
        HashTable hashtable = new HashTable();
        hashtable.put("1","Elemento 1");
        hashtable.put("12","Elemento 1.1");
        hashtable.put("23","Elemento 1.2");
        hashtable.put("23","Elemento 2");
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, Elemento 1] -> [12, Elemento 1.1] -> [23, Elemento 2]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 3;
        Assertions.assertEquals(expected,hashtable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashtable.size());
    }

    @org.junit.jupiter.api.Test
    void Actualizar_Elemento_Con_Tres_Colisiones() {
        HashTable hashtable = new HashTable();
        hashtable.put("1","Elemento 1");
        hashtable.put("12","Elemento 1.1");
        hashtable.put("23",true);
        hashtable.put("34",false);
        hashtable.put("34",true);
        System.out.println(hashtable.toString());
        String bucket = "\n bucket[1] = [1, Elemento 1] -> [12, Elemento 1.1] -> [23, true] -> [34, true]";
        Assertions.assertEquals(bucket,hashtable.toString());

        int expected = 4;
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
    void Get_Con_Colision_1a_posicion_mismo_bucket(){
        HashTable hashtable = new HashTable();
        hashtable.put("1", 5);
        hashtable.put("1", 7.5f);
        System.out.println(hashtable.toString());
        String bucket = "7.5";
        Assertions.assertEquals(bucket,hashtable.get("1").toString());
    }
    @org.junit.jupiter.api.Test
    void Get_Con_Colision_2a_posicion_mismo_bucket(){
        HashTable hashtable = new HashTable();
        hashtable.put("1", "Elemento 1");
        hashtable.put("12", "Elemento 2");
        hashtable.put("23", "Elemento 3");
        hashtable.put("23", "Elemento 4");
        System.out.println(hashtable.toString());
        String bucket = "Elemento 4";
        Assertions.assertEquals(bucket,hashtable.get("23").toString());
    }
    @org.junit.jupiter.api.Test
    void Get_Con_Colision_3a_posicion_mismo_bucket(){
        HashTable hashtable = new HashTable();
        hashtable.put("1", "Elemento 1");
        hashtable.put("12", "Elemento 2");
        hashtable.put("23", "Elemento 3");
        hashtable.put("34", "Elemento 4");
        hashtable.put("34", "Elemento 5");
        System.out.println(hashtable.toString());
        String bucket = "Elemento 5";
        Assertions.assertEquals(bucket,hashtable.get("34").toString());
    }

    @org.junit.jupiter.api.Test
    void Get_Elemento_No_Existente(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola"); //Añadimos uno para hacer pruebas
        Assertions.assertNull(hashtable.get("2"));
    }
    @org.junit.jupiter.api.Test
    void Get_Elemento_No_Existente_En_Posicion_Ocupada_Sin_Colisionar(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola");
        Assertions.assertNull(hashtable.get("12"));
    }
    @org.junit.jupiter.api.Test
    void Get_Elemento_No_Existente_En_Posicion_Ocupada_Con_3_Elementos_Colisionando(){
        HashTable hashtable = new HashTable();
        hashtable.put("1","hola");
        hashtable.put("12","adios");
        hashtable.put("23","goodbye");
        hashtable.put("34","alo");
        Assertions.assertNull(hashtable.get("45"));
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
    void Drop_Elemento_Con_Colision_1a_Posicion(){
        HashTable hashTable = new HashTable();
        hashTable.put("1",5);
        hashTable.put("12",7.5f);
        hashTable.put("23",true);
        hashTable.put("34",false);
        hashTable.drop("1");
        String bucket = "\n bucket[1] = [12, 7.5] -> [23, true] -> [34, false]";
        Assertions.assertEquals(bucket,hashTable.toString());

        int expected = 3;
        Assertions.assertEquals(expected,hashTable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashTable.size());
    }
    @org.junit.jupiter.api.Test
    void Drop_Elemento_Con_Colision_2a_Posicion(){
        HashTable hashTable = new HashTable();
        hashTable.put("1",true);
        hashTable.put("12","adios");
        hashTable.put("23",5);
        hashTable.put("34",5.5f);
        hashTable.drop("12");
        String bucket = "\n bucket[1] = [1, true] -> [23, 5] -> [34, 5.5]";
        Assertions.assertEquals(bucket,hashTable.toString());

        int expected = 3;
        Assertions.assertEquals(expected,hashTable.count());
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize,hashTable.size());
    }
    @org.junit.jupiter.api.Test
    void Drop_Elemento_Con_Colision_3a_Posicion(){
        HashTable hashTable = new HashTable();
        hashTable.put("1","hola");
        hashTable.put("12",false);
        hashTable.put("23",5);
        hashTable.put("34",5.5);
        hashTable.drop("23");
        String bucket = "\n bucket[1] = [1, hola] -> [12, false] -> [34, 5.5]";
        Assertions.assertEquals(bucket,hashTable.toString());

        int expected = 3;
        Assertions.assertEquals(expected,hashTable.count());
        int expectedSize = 16;
        System.out.println(hashTable.count());
        System.out.println(hashTable.size());
        Assertions.assertEquals(expectedSize,hashTable.size());
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
        hashtable.put("1",true);
        hashtable.drop("12");
        String expected = "\n bucket[1] = [1, true]";
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
