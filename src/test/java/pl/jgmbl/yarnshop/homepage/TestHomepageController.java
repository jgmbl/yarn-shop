package pl.jgmbl.yarnshop.homepage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestHomepageController {
    @Mock
    private HomepageController homepageController;

    @Test
    public void testDisplayHomepage() {
        Mockito.when(HomepageController.displayHomepage()).thenReturn("/");

        String homepage = homepageController.displayHomepage();

        Assertions.assertEquals("/", homepage);
    }
}
