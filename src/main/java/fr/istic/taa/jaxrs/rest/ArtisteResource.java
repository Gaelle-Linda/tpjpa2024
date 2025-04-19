package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ArtisteDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Pet;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("artiste") // (localhost:8080/artiste)Route par défaut pour se positionner sur la ressource artiste
@Produces({"application/json", "application/xml"})//Les données peuvent être renvoyées sous formats json ou xml
public class ArtisteResource {//ArtisteRessource est un ensemble de routes(URL pour exécuter une action donnée sur le serveur) liés à la classe Artiste



  @GET //pour demander une ressource Méthode: retourne un artiste avec un ID donné
  @Path("/{ArtisteId}") // (localhost:8080/artiste/identifiant) on se positionne sur l'instance au travers de son identifiant; "/{ArtisteId}" accolardes pour dire que l'identifiant peut varier en fonction de la demande de l'utilisateur
  public Artiste getArtisteById(@PathParam("ArtisteId") Long ArtisteId)  {//pour récupérer la valeur insérer dans la partie dynamique de l'URL :  @Path("/{ArtisteId}/{id}") =>  public Artiste getArtisteById(@PathParam("ArtisteId") Long ArtisteId, @PathParam("id") long id)
      // return artiste
      ArtisteDao artisteDAo = new ArtisteDao();
      Artiste artiste = artisteDAo.findOne(ArtisteId);
      return artiste;
  }

  //On peut vouloir vérifier que la route (localhost:8080/artiste/) vers la ressource est bien accessible via l'URL ou alors parfois pour certains c'est pouvoir afficher la liste des artistes
  //Le code ci-dessous marche mais comme il a la meme route @Path("/") que celui que la fonction listArtiste qui affiche la liste des artiste, On le met en commentaire sinon le serveur considerera la première fonction par défaut
  /*@GET
  @Path("/")//
  public Artiste getArtiste()  {
      return new Artiste();
  }
  */

  //Méthode POST - Ajouter un nouvel artiste
  @POST //Pour soumetre des données aux serveur pour traitement ou pour sauvegarde dans la BD
  @Consumes("application/json") //Consomme des données sous format json uniquement
  @Path("/")
  public Response addArtiste(
          //@Parameter(description = "...") → Swagger annotation (OpenAPI) pour documenter l'API.
          @Parameter(description = "Artite object that needs to be added to the store", required = true) Artiste artiste) {
    // add artiste, ajouter l'artiste dans la BD
    ArtisteDao artistDao = new ArtisteDao();
    artistDao.save(artiste);

    return Response.ok().entity("SUCCESS").build(); //Retourne une réponse HTTP 200 avec le message "SUCCESS"
  }

  // git commit -m "Impléméntation des méthodes UPDATE et DELETE àpartir d'un id

  @GET //pour demander une ressource Méthode: On veut retourner la liste des artistes
  @Path("/") // (localhost:8080/artiste/) on se positionne sur l'instance sans précision d'un identifiant particulier identifiant;
  public List<Artiste> listeArtistes()  {//Il nya pas de valeur à récupérer ici la valeur
    // return la liste des artistes
    ArtisteDao artisteDAo = new ArtisteDao();
    return artisteDAo.findAll();
  }


  //Méthode UPDATE - Modifie les attributs d'un artiste s'il existe, il faut le paramètres ID dans la partie 
  //dynamique de l'URL  et Les nouvelles valeurs (nomArtistique, genreMusical) sont dans le corps de la requête (body), en format JSON
  //la fonction updateArtisteByID recupère l'id de l'artiste et un nouvel artiste au format JSON
  @PUT //Pour soumetre des données aux serveur pour traitement ou pour sauvegarde dans la BD
  @Consumes("application/json") //Consomme des données sous format json uniquement
  @Path("/{ArtisteId}") // (localhost:8080/artiste/identifiant) on se positionne sur l'instance au travers de son identifiant; "/{ArtisteId}" accolardes pour dire que l'identifiant peut varier en fonction de la demande de l'utilisateur
  public Response updateArtisteByID(@PathParam("artisteId") Long artisteId, Artiste updatedArtiste) {
    // Étape 1 : Vérifier si l’artiste existe dans la base de données
    ArtisteDao artisteDao = new ArtisteDao(); // ✅ Créer une instance de la DAO
    Artiste existingArtiste = artisteDao.findOne(artisteId);
    if (existingArtiste == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Artiste non trouvé").build();
    }

    // Étape 2 : Mettre à jour les champs seulement si des nouvelles valeurs sont fournies
    if (updatedArtiste.getNomArtistique() != null) {
      existingArtiste.setNomArtistique(updatedArtiste.getNomArtistique());
    }
    if (updatedArtiste.getGenreMusical() != null) {
      existingArtiste.setGenreMusical(updatedArtiste.getGenreMusical());
    }
    // Étape 3 : Sauvegarder les modifications en base de données
    //ArtisteDao artisteDao = new ArtisteDao(); //  Créer une instance de la DAO
    artisteDao.update(existingArtiste); // Appeler update() sur cette instance
    //Une fois les modifications effectuées, on appelle la méthode update() du DAO (ArtisteDao). Cette méthode va mettre à jour l’artiste dans la base de données via JPA.

    // Étape 4 : Retourner une réponse confirmant la mise à jour
    return Response.ok("Artiste mis à jour avec succès").build(); //On renvoie une réponse 200 OK avec un message de succès.
  }
    //Exemple d'utilisation
    /*
    PUT http://localhost:8080/artiste/1
  Content-Type: application/json
  {
      "nomArtistique": "Chanteur Star",
      "genreMusical": "Pop"
  }
     */
  //La méthode deleteArtiste() permet de supprimer un artiste de la BD connaissant son ID
  /*@DELETE
  @Path("/{artisteId}")
  public Response deleteArtiste(@PathParam("artisteId") Long artisteId) {
    ArtisteDao artisteDao = new ArtisteDao(); // Instancier la DAO

    // Vérifier si l’artiste existe
    Artiste existingArtiste = artisteDao.findOne(artisteId);
    if (existingArtiste == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Artiste non trouvé").build();
    }
    //Si l'artiste n'est pas trouvé, on retourne 404 Not Found
    // Supprimer l'artiste
    artisteDao.delete(existingArtiste); //Comme delete() est une méthode d'instance, on l'appelle sur un objet ArtisteDao et non directement sur la classe.

    return Response.ok("Artiste supprimé avec succès").build();//Si la suppression réussit, on renvoie 200 OK avec le message "Artiste supprimé avec succès"
  }
  */
  //Comme ArtisteDao possède une méthode deleteById(Long id), on va plutot l'utiliser
  @DELETE
  @Path("/{artisteId}")
  public Response deleteArtiste(@PathParam("artisteId") Long artisteId) {
    ArtisteDao artisteDao = new ArtisteDao();

    // Vérifier si l'artiste existe
    if (artisteDao.findOne(artisteId) == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Artiste non trouvé").build();
    }

    // Supprimer l'artiste directement par son ID
    artisteDao.deleteById(artisteId);

    return Response.ok("Artiste supprimé avec succès").build();
  }


}


