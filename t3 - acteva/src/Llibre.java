// El objecte Llibre se utilizara per a poder fer tots el metodes necesaris, per ha poder
// treballar amb la base de dades.

public class Llibre 
{
	// El atributs que utilitzarem per als metodes.
	int Id;
	
	String Titol;
	
	String Autor;
	
	int Any_naixement;
	
	int Any_publicacio;
	
	String Editorial;
	
	int Nombre_pagines;
	
	// El constructor que utilizarem per als metodes.
	public Llibre(int Id, String Titol, String Autor, int Any_naixement, int Any_publicacio, String Editorial, int Nombre_pagines) 
	{
		this.Id = Id;
		this.Titol = Titol;
		this.Autor = Autor;
		this.Any_naixement = Any_naixement;
		this.Any_publicacio = Any_publicacio;
		this.Editorial = Editorial;
		this.Nombre_pagines = Nombre_pagines;
	}
	
	// Altre constructor per si ens fa falta.
	public Llibre() 
	{
		super();
	}

	// A�o permiteix agafar el Id.
	public int getIdentificaor() 
	{
		return Id;
	}

	// A�o permiteix cambiar el Id.
	public void setIdentificaor(int Id) 
	{
		this.Id = Id;
	}

	// A�o permiteix agafar el Titol.
	public String getTitol() 
	{
		return Titol;
	}

	// A�o permiteix cambiar el Titol.
	public void setTitol(String Titol) 
	{
		Titol = Titol;
	}

	// A�o permiteix agafar el Autor.
	public String getAutor() 
	{
		return Autor;
	}

	// A�o permiteix cambiar el Autor.
	public void setAutor(String Autor) 
	{
		Autor = Autor;
	}

	// A�o permiteix agafar el Any de naixement.
	public int getAnynaixement() 
	{
		return Any_naixement;
	}

	// A�o permiteix cambiar el Any de naixement.
	public void setAnynaixement(int Any_naixement) 
	{
		Any_naixement = Any_naixement;
	}

	// A�o permiteix agafar el Any de publicacio.
	public int getAnypublicacio() 
	{
		return Any_publicacio;
	}
	
	// A�o permiteix cambiar el Any de publicacio.
	public void setAnypublicacio(int Any_publicacio) 
	{
		Any_publicacio = Any_publicacio;
	}

	// A�o permiteix agafar la Editorial.
	public String getEditorial() 
	{
		return Editorial;
	}

	// A�o permiteix cambiar la Editorial.
	public void setEditorial(String Editorial) 
	{
		Editorial = Editorial;
	}

	// A�o permiteix agafar el Numero de pagines.
	public int getNombrepagines() 
	{
		return Nombre_pagines;
	}

	// A�o permiteix cambiar el Numero de pagines.
	public void setNombrepagines(int Nombre_pagines) 
	{
		Nombre_pagines = Nombre_pagines;
	}
}