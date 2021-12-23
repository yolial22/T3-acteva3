import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

// En el Ejeeeva lo que fara sera que a traves de uns metodes, podrem afegir,
// actualizar, borrar i mostrar tot lo relacionat amb la base de dades de MongoDB.
public class Ejeeva
{
	// Este metode ens permitira fer un ArrayList de tots el llibres que tenim en la llista i guardarlos.
	public static ArrayList<Llibre> Llibres = new ArrayList<Llibre>();
		
	// Este metode ens permitira verificar lo que anem a escriure nosaltres per consola.
	static Scanner teclat = new Scanner(System.in);
	
	// Este metode ens permitira recorrer la llista de llibres de la base de dades de MongoDB, a traves de aquest metode,
	// i si el id que nosotres preguntem per pantalla es el mateix que esta en este metode, ens mostrara tota l'informacio,
	// de eixe llibre.
	public static int recuperarLlibre(int identificaor, MongoCollection coleccio) 
	{
		MongoClient mongoClient = new MongoClient("localhost",27017);
		
		MongoCursor<Document> mongoCursor = coleccio.find().iterator();
		
		while(mongoCursor.hasNext()) 
		{
			JSONObject obj = new JSONObject(mongoCursor.next().toJson());
			
			if(obj.getInt("Id") == identificaor) 
			{
				System.out.println("Id del llibre: " + obj.getInt("Id"));
				System.out.println("Titol del llibre: " + obj.getString("Titol"));
				System.out.println("Autor del llibre: " + obj.getString("Autor"));
				System.out.println("Any de naixement: " + obj.getInt("Any_naixement"));
				System.out.println("Any de publicacio: " + obj.getInt("Any_publicacio"));
				System.out.println("Editorial: " + obj.getString("Editorial"));
				System.out.println("Nombre de pagines: " + obj.getInt("Nombre_pagines"));
			}
		}
		
		mongoClient.close();
		
		return identificaor;
	}
	
	// Este metode ens permitira mostrar el Id i el Titol de tots llibres que hi ha en la base de dades de MongoDB.
	public static void mostrarLlibre(MongoCollection coleccio)
	{
		MongoClient mongoClient = new MongoClient("localhost",27017);
		
		MongoCursor<Document> mongocursor = coleccio.find().iterator();
		
		while(mongocursor.hasNext()) 
		{
			JSONObject obj = new JSONObject(mongocursor.next().toJson());
			System.out.println("Id del llibre: " + obj.getInt("Id"));
			System.out.println("Titol del llibre: " + obj.getString("Titol"));
		}
		
		mongoClient.close();
	}
	
	// Este metode ens permitira crear un nou llibre i de eixe llibre es
	// creara una nova coleccio a la base de dades en MongoDB.
	public static int crearLlibre(Llibre llibre, MongoCollection coleccio) 
	{			
		MongoClient mongoClient = new MongoClient("localhost",27017);
		
		Document doc = new Document();
		doc.append("Id", llibre.getIdentificaor());
		doc.append("Titol", llibre.getTitol());
		doc.append("Autor", llibre.getAutor());
		doc.append("Any_naixement", llibre.getAnynaixement());
		doc.append("Any_publicacio", llibre.getAnypublicacio());
		doc.append("Editorial", llibre.getEditorial());
		doc.append("Nombre_pagines", llibre.getNombrepagines());
		coleccio.insertOne(doc);
		
		mongoClient.close();
		
		return llibre.getIdentificaor();
	}
	
	// Este metode ens permitira a traves de un Id que nosaltres preguntarem, actualizar un llibre i eixe llibre se
	// actualizara la coleccio de la base de dades en MongoDB.
	public static void actualitzarLlibre(int identificaor, MongoCollection coleccio) 
	{
		MongoClient mongoClient = new MongoClient("localhost",27017);
		
		String Titol;
		
		String Autor;
		
		int Any_naixement;
		
		int Any_publicacio;
		
		String Editorial;
		
		int Nombre_pagines;
		
		System.out.print("Cambia el titol: ");
		Titol = teclat.nextLine();
		
		System.out.print("Cambia el autor: ");
		Autor = teclat.nextLine();	
		
		System.out.print("Cambia el any de naixement: ");
		Any_naixement = Integer.parseInt(teclat.nextLine());
		
		System.out.print("Cambia el any de publicacio: ");
		Any_publicacio = Integer.parseInt(teclat.nextLine());
				
		System.out.print("Cambia la editorial: ");
		Editorial = teclat.nextLine();
				
		System.out.print("Cambia el nombre de pagines: ");
		Nombre_pagines = Integer.parseInt(teclat.nextLine());
		
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Titol", Titol)));
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Autor", Autor)));
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Any_naixement", Any_naixement)));
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Any_publicacio", Any_publicacio)));
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Editorial", Editorial)));
		coleccio.updateOne(eq("Id", identificaor), new Document("$set", new Document("Nombre_pagines", Nombre_pagines)));
		
		mongoClient.close();
	}
	
	// Este metode ens permitira a traves de un Id que nosaltres preguntarem, borrar un llibre que estaba
	// en la coleccio de la base de dades en MongoDB.
	public static void borrarLlibre(int identificaor, MongoCollection coleccio) 
	{		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		
		coleccio.deleteOne(eq("Id",identificaor));
		
		mongoClient.close();
	}
	
	// La clase main lo que fara sera a traves del numero que nosaltres pulsem,
	// fara un metode dels que estan anteriorment.
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("Biblioteca");
		MongoCollection coleccio = mongoDatabase.getCollection("Llibres");
				
		System.out.println("Menu de la biblioteca.");
		System.out.println("");
		System.out.println("1. Mostrar tots els titols de la biblioteca.");
		System.out.println("2. Mostrar informacion detallada d'un llibre a partir del seu identificaor.");
		System.out.println("3. Afegir un nou llibre a la biblioteca.");
		System.out.println("4. Actualitzar un llibre a partir del seu identificaor.");
		System.out.println("5. Borrar un llibre a partir del seu identificaor.");
		System.out.println("6. Tancar la biblioteca.");	
		System.out.println("");
				
		System.out.print("Introduix un numero: ");
		
		int numero;
		
		numero = Integer.parseInt(teclat.nextLine());
		
		int Id;
		String Titol;
		String Autor;
		int Any_naixement;
		int Any_publicacio;
		String Editorial;
		int Nombre_pagines;
				
		switch(numero) 
		{
			case 1:
				
			mostrarLlibre(coleccio);
			
			break;
			
			case 2:
			System.out.print("Introduix un identificaor: ");
			
			int id = Integer.parseInt(teclat.nextLine());
			
			recuperarLlibre(id,coleccio);
			
			break;
			
			case 3:
			System.out.print("Introduix un id: ");
			Id = Integer.parseInt(teclat.nextLine());
			
			System.out.print("Introduix un titol: ");
			Titol = teclat.nextLine();
						
			System.out.print("Introduix un autor: ");
			Autor = teclat.nextLine();
						
			System.out.print("Introduix un any de naixement: ");
			Any_naixement = Integer.parseInt(teclat.nextLine());
						
			System.out.print("Introduix un any de publicacio: ");
			Any_publicacio = Integer.parseInt(teclat.nextLine());
						
			System.out.print("Introduix una editorial: ");
			Editorial = teclat.nextLine();
						
			System.out.print("Introduix un nombre de pagines: ");
			Nombre_pagines = Integer.parseInt(teclat.nextLine());
						
			Llibre llibrenou = new Llibre(Id,Titol,Autor,Any_naixement,Any_publicacio,Editorial,Nombre_pagines);
						
			crearLlibre(llibrenou,coleccio);
			
			break;
			
			case 4:
			System.out.print("Introduix un identificaor: ");
			
			int key = Integer.parseInt(teclat.nextLine());
			
			actualitzarLlibre(key,coleccio);
			
			break;
			
			case 5:
			System.out.print("Introduix un identificaor: ");
			
			int identificaor = Integer.parseInt(teclat.nextLine());
			
			borrarLlibre(identificaor,coleccio);
			
			break;
			
			case 6:
				
			System.out.print("Biblioteca tanca.");
			
			break;
			
			default:
				
			System.out.print("El nombre introduit no es correcte.");
			
			break;
		}
		
		mongoClient.close();
	}
}