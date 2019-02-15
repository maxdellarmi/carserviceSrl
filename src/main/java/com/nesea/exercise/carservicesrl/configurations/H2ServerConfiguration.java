package com.nesea.exercise.carservicesrl.configurations;
//CONNESSIONE URL PER APPLICAZIONE "jdbc:h2:mem:testdb"
//CONNESSIONE DA FUORI:"jdbc:h2:tcp:localhost:9092/mem:testdb"

/*NEL POM
    <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <scope>compile</scope>
     </dependency>
 */

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
/*** SOLO PER TESTARE **** @Profile("dev") */
public class H2ServerConfiguration {

    //TCP port for remote connections, default 9092 DA FUORI DAI CLIENT
   @Value("${h2.tcp.port:9092}")
   private String h2TcpPort;

   // Web port, default 8082
   @Value("${h2.web.port:8082}")
   private String h2WebPort;


   /**
    * TCP connection to connect with SQL clients to the embedded h2 database.
    *
    * Connect to "jdbc:h2:tcp:localhost:9092/mem:testdb", username "sa", password empty.
    */
   @Bean
   /************QUESTA URL PER CONNETTERSI DA FUORI ******/
   public Server h2TcpServer() throws SQLException {
       System.out.println("INFO: ************ Connect from remoteclient to \"jdbc:h2:tcp:localhost:9092/mem:carservice\", username \"sa\", password empty.  ************");
       return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort).start();
    }

        /**
         * Web console for the embedded h2 database.
         *
         * Go to http:localhost:8082 and connect to the database "jdbc:h2:mem:testdb", username "sa", password empty.
         */
        @Bean
        /*****CONNESSIONE APPLICAZIONE: "jdbc:h2:mem:testdb" QUESTA DEVE ESSERE L'URL DELL'APPLICAZIONE!!!!!****/
        /****PER LA CONSOLE: http:localhost:8082  ***/
        @ConditionalOnExpression("${h2.web.enabled:true}")
        public Server h2WebServer() throws SQLException {
            System.out.println("INFO:  ************ Connect for console to  http:localhost:8082 , username \"sa\", password empty and use this jdbc url connection:\"jdbc:h2:mem:carservice\"  ************");
            return Server.createWebServer("-web", "-webAllowOthers", "-webPort", h2WebPort).start();
        }
    }
