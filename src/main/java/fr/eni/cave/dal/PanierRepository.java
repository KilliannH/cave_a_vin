package fr.eni.cave.dal;

import fr.eni.cave.bo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.eni.cave.bo.client.Panier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    // Création d'une requête paramétrée avec JPQL
    @Query("SELECT p FROM Panier p WHERE p.client = :client AND p.numCommande is null")
    List<Panier> findPaniersWithJPQL(@Param("client") Client client);
    // Rechercher la liste des paniers non commandés d'un client
    List<Panier> findByNumCommandeNullAndClient(@Param("client") Client client);

    // Création d'une requête native - SQL
    @Query(value = "SELECT p.* FROM CAV_SHOPPING_CART p WHERE p.CLIENT_ID = :idClient AND p.ORDER_NUMBER IS NOT NULL", nativeQuery = true)
    List<Panier> findCommandesWithSQL(@Param("idClient")String idClient);
    // Rechercher la liste des commandes d'un client
    List<Panier> findByNumCommandeNotNullAndClient(@Param("client") Client client);
}