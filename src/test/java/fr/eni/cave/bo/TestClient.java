package fr.eni.cave.bo;

import fr.eni.cave.bo.client.Client;
import fr.eni.cave.dal.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@DataJpaTest
public class TestClient {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    ClientRepository repository;

    @Test
    public void test_save() {
        final Client client = Client.builder()
                .pseudo("bobeponge@email.fr")
                .password("carré")
                .nom("Eponge")
                .prenom("Bob")
                .build();

        final Client clientDB = repository.save(client);
        log.info(clientDB.toString());

        assertThat(clientDB).isNotNull();
        assertThat(clientDB).isEqualTo(client);
    }

    @Test
    public void test_delete() {
        final Client client = Client.builder()
                .pseudo("bobeponge@email.fr")
                .password("carré")
                .nom("Eponge")
                .prenom("Bob")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        log.info(client.toString());

        repository.delete(client);

        Client clientDB = entityManager.find(Client.class, client.getPseudo());
        assertNull(clientDB);
    }
}
