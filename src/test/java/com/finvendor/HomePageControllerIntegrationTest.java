package com.finvendor;

import com.finvendor.common.infra.httpclient.service.HttpClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@PropertySource("classpath:/finvendor.properties")
//@ContextConfiguration(locations = "classpath:finvendor-servlet-test.xml")
public class HomePageControllerIntegrationTest extends TestBase {

    @Autowired
    private HttpClientService httpClientService;

    @Test
    public void testGetHomePageSearchHint() {
        Assert.assertNotNull(httpClientService);
    }
}
