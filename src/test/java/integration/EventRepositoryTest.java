package integration;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

public class EventRepositoryTest extends BaseRepositoryTest {

    @Test
    public void shouldReturnNull() {
        Integer idByName = eventRepository.findIdByName("");
        Assert.assertNull(idByName);
    }

    @Test
    public void idsShouldBeEquals() {
        String name = "Name";
        Integer expectedId = eventRepository.create(name);
        Integer actualId = eventRepository.findIdByName(name);
        Assert.assertNotNull(expectedId);
        Assert.assertNotNull(actualId);
        Assert.assertEquals(expectedId, actualId);
    }

    @Test(expected = DuplicateKeyException.class)
    public void shouldThrowException() {
        String name = "Name";
        eventRepository.create(name);
        eventRepository.create(name);
    }
}
