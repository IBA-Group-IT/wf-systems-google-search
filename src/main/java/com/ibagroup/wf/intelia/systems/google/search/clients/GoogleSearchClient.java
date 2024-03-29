package com.ibagroup.wf.intelia.systems.google.search.clients;

import java.util.concurrent.TimeUnit;

import com.ibagroup.wf.intelia.core.clients.RobotDriverWrapper;
import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.systems.google.search.pages.HomePage;

public class GoogleSearchClient extends RobotDriverWrapper {

	public GoogleSearchClient(ConfigurationManager cmn) {
		super(cmn);
		initDriver();
	}

    private void initDriver() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS).pageLoadTimeout(90, TimeUnit.SECONDS);
        getDriver().manage().deleteAllCookies();
    }

    public HomePage getHomePage() {
        getDriver().get(getCfg().getConfigItem("google_search_site_url"));
        return new HomePage(getCfg());
    }

}
