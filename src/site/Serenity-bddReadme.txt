1- how to create new project:   mvn archetype:generate -Dfilter=serenity
2- how to run the test      :   mvn clean verify -Dwebdriver.driver=chrome -Dwebdriver.chrome.driver=./chromedriver.exe
3- test report              :   ./target/site/serenity/index.html