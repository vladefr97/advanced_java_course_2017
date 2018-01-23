package telegram.bot;

import java.util.List;

public interface JSONGetter {

    public String getJSONString(String query, String queryOptions);//

    public String getJSONString(String fullRequest);
}
