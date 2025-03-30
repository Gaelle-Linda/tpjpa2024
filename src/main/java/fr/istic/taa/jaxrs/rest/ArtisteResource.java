package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ArtisteDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Pet;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("artiste") // (localhost:8080/artiste)Route par défaut pour se positionner sur la ressource artiste
@Produces({"application/json", "application/xml"})//Les données peuvent être renvoyées sous formats json ou xml
public class ArtisteResource {//ArtisteRessource est un ensemble de routes(URL pour exécuter une action donnée sur le serveur) liés à la classe Artiste



  @GET //pour demander une ressource Méthode
  @Path("/{ArtisteId}") // (localhost:8080/artiste/identifiant) on se positionne sur l'instance au travers de son identifiant; "/{ArtisteId}" accolardes pour dire que l'identifiant peut varier en fonction de la demande de l'utilisateur
  public Artiste getArtisteById(@PathParam("ArtisteId") Long ArtisteId)  {//pour récupérer la valeur insérer dans la partie dynamique de l'URL :  @Path("/{ArtisteId}/{id}") =>  public Artiste getArtisteById(@PathParam("ArtisteId") Long ArtisteId, @PathParam("id") long id)
      // return artiste
      ArtisteDao artisteDAo = new ArtisteDao();
      Artiste artiste = artisteDAo.findOne(ArtisteId);
      return artiste;
  }

  //On peut vouloir vérifier que la route (localhost:8080/artiste/) vers la ressource est bien accessible via l'URL ou alors parfois pour certains c'est pouvoir afficher la liste des artistes
  @GET
  @Path("/")//
  public Artiste getArtiste(Long artisteId)  {
      return new Artiste();
  }

  //Méthode POST - Ajouter un nouvel artiste
  @POST //Pour soumetre des données aux serveur pour traitement ou pour sauvegarde dans la BD
  @Consumes("application/json") //Consomme des données sous formar json uniquement
  @Path("/")
  public Response addArtiste(
          //@Parameter(description = "...") → Swagger annotation (OpenAPI) pour documenter l'API.
          @Parameter(description = "Artite object that needs to be added to the store", required = true) Artiste artiste) {
    // add artiste, ajouter l'artiste dans la BD
    ArtisteDao artistDao = new ArtisteDao();
    artistDao.save(artiste);

    return Response.ok().entity("SUCCESS").build(); //Retourne une réponse HTTP 200 avec le message "SUCCESS"
  }


}