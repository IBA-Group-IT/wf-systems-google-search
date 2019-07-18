package com.ibagroup.wf.intelia.systems.google.search;

import com.ibagroup.wf.intelia.core.config.ConfigurationManager;
import com.ibagroup.wf.intelia.core.robots.RobotProtocol;
import com.ibagroup.wf.intelia.systems.google.search.clients.GoogleSearchClient;
import com.ibagroup.wf.intelia.systems.google.search.pages.HomePage;
import com.ibagroup.wf.intelia.systems.google.search.pages.ResultPage;

public interface GoogleSearchRobot extends RobotProtocol {
	
    default ResultPage initRobot(String searchString) {
    	GoogleSearchClient client = new GoogleSearchClient(getCfg());

        HomePage homePage = client.getHomePage();
        storeCurrentMetadata();

        ResultPage resultPage = homePage.search(searchString);
        setResultPage(resultPage);
        storeCurrentMetadata();

        return resultPage;
    }

    ConfigurationManager getCfg();

    ResultPage getResultPage();
    void setResultPage(ResultPage resultPage);

}