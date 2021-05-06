package ut.com.rapid7.ias.bamboo;

import com.rapid7.ias.bamboo.impl.InsightAppSecHelper;
import com.rapid7.ias.bamboo.util.UtilityLogger;
import com.rapid7.ias.client.ApiClient;
import com.rapid7.ias.client.ApiResponse;
import com.rapid7.ias.client.api.SearchApi;
import com.rapid7.ias.client.model.PageScanConfig;
import com.rapid7.ias.client.model.RequiredIdResource;
import com.rapid7.ias.client.model.ResourceScanConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InsightAppSecHelperUnitTest {

    @Mock
    UtilityLogger logger;

    @InjectMocks
    private InsightAppSecHelper insightAppSecHelper;

    @Test
    public void testGetScanConfiguration() throws Exception {
        //Call mockCall = mock(Call.class);
        ApiClient apiClient = mock(ApiClient.class);
        ApiResponse response = mock(ApiResponse.class);
        SearchApi searchApi = mock(SearchApi.class);

        insightAppSecHelper.searchApi = searchApi;

        //when(searchApi.performSearchCall(any(SearchRequest.class), anyInt(), anyInt(), any(), any(), any())).thenReturn(mockCall);
        when(searchApi.getApiClient()).thenReturn(apiClient);
        when(apiClient.execute(any(), any())).thenReturn(response);
        when(response.getData()).thenReturn(getPageScanConfig(1));

        ResourceScanConfig result = insightAppSecHelper.getScanConfiguration("my-scan-config", UUID.randomUUID());

        assertNotNull(result);
        assertNotNull(result.getApp());
    }

    @Test
    public void testGetScanConfigurationError() throws Exception {
        //Call mockCall = mock(Call.class);
        ApiClient apiClient = mock(ApiClient.class);
        ApiResponse response = mock(ApiResponse.class);
        SearchApi searchApi = mock(SearchApi.class);

        insightAppSecHelper.searchApi = searchApi;

        //when(searchApi.performSearchCall(any(SearchRequest.class), anyInt(), anyInt(), any(), any(), any())).thenReturn(mockCall);
        when(searchApi.getApiClient()).thenReturn(apiClient);
        when(apiClient.execute(any(), any())).thenReturn(response);
        when(response.getData()).thenReturn(getPageScanConfig(0));

        ResourceScanConfig result = insightAppSecHelper.getScanConfiguration("my-scan-config", UUID.randomUUID());
        assertNull(result);

        when(response.getData()).thenReturn(getPageScanConfig(5));

        result = insightAppSecHelper.getScanConfiguration("my-scan-config", UUID.randomUUID());
        assertNull(result);
    }

    // TEST HELPERS

    public PageScanConfig getPageScanConfig(int size) {
        List<ResourceScanConfig> data = new ArrayList<>(size);

        for(int i = 0; i < size; i++) {
            data.add(getResourceScanConfig());
        }

        PageScanConfig pageScanConfig = new PageScanConfig();
        pageScanConfig.setData(data);

        return pageScanConfig;
    }

    public ResourceScanConfig getResourceScanConfig(){
        UUID id = UUID.randomUUID();
        RequiredIdResource app = new RequiredIdResource();
        app.id(id);

        ResourceScanConfig resourceScanConfig = new ResourceScanConfig();
        resourceScanConfig.setApp(app);

        return resourceScanConfig;
    }
}
