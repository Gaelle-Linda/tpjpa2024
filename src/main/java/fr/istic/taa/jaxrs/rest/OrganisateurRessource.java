package fr.istic.taa.jaxrs.rest;
import fr.istic.taa.jaxrs.dao.ArtisteDao;
import fr.istic.taa.jaxrs.dao.OrganisateurDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.dto.ArtisteDto;
import fr.istic.taa.jaxrs.dto.OrganisateurDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;
@Path("organisateur") // (localhost:8080/artiste)Route par défaut pour se positionner sur la ressource organisateur
@Produces({"application/json", "application/xml"})
public class OrganisateurRessource {


@GET
@Path("/{OrganisateurId}") // (localhost:8080/artiste/identifiant)
public Organisateur getOrganisateurById(@PathParam("OrganisateurfId") Long OrganisateurId)  {//pour récupérer la valeur insérer dans la partie dynamique de l'URL :  @Path("/{OrganisateurId}/{id}") =>  public Artiste getArtisteById(@PathParam("ArtisteId") Long ArtisteId, @PathParam("id") long id)
      // return organisateur
      OrganisateurDao organisateurDAo = new OrganisateurDao();
      Organisateur organisateur = organisateurDAo.findOne(OrganisateurId);
      return organisateur;
  }

 //Méthode POST - Ajouter un nouvel organisateur
 @POST //Pour soumetre des données aux serveur pour traitement ou pour sauvegarde dans la BD
 @Consumes("application/json") //Consomme des données sous format json uniquement
 @Path("/")
 public Response addOrganisateur(
         //@Parameter(description = "...") → Swagger annotation (OpenAPI) pour documenter l'API.
         @Parameter(description = "Organisateur object that needs to be added to the store", required = true) Organisateur organisateur) {
   // add organisateur, ajouter l'organisateur dans la BD
   OrganisateurDao organisateurDao = new OrganisateurDao();
   organisateurDao.save(organisateur);

   return Response.ok().entity("SUCCESS").build(); //Retourne une réponse HTTP 200 avec le message "SUCCESS"
 }

 @GET //pour demander une ressource Méthode: On veut retourner la liste des organisateurs
 @Path("/") // (localhost:8080/artiste/) on se positionne sur l'instance sans précision d'un identifiant particulier identifiant;
 public List<Organisateur> listeOrganisateur()  {//Il nya pas de valeur à récupérer ici la valeur
   // return la liste des artistes
   OrganisateurDao organisateurDAo = new OrganisateurDao();
   return organisateurDAo.findAll();
 }

@GET //pour demander une ressource Méthode: On veut retourner la liste des artistes
@Path("/dto") // (localhost:8080/artiste/) on se positionne sur l'instance sans précision d'un identifiant particulier identifiant;
public List<OrganisateurDto> listeOrganisateurDto()  {//Il nya pas de valeur à récupérer ici la valeur
    // return la liste des organisateur
    OrganisateurDao organisateurDAo = new OrganisateurDao();
    //return organisateurDAo.findAll();
    List<Organisateur> listeOrganisateurs = organisateurDAo.findAll(); //On récupère la liste des organisateurs dans la base de données
    return listeOrganisateurs.stream().map(Organisateur::toDto).collect(Collectors.toList()); //On renvoie la liste des organisateurs au format JSON ou XML selon le type de contenu demandé par le client
}



}
