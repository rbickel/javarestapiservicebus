package guru.springframework.services;

import guru.springframework.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;

import com.google.gson.Gson;

@Service
public class BusServiceImpl  {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void addProductToQueue(Product value) {
        Gson gson = new Gson();  
        String message = gson.toJson(value);
        String endpoint = System.getenv("SERVICEBUSCONNECTION");
        ConnectionStringBuilder cs = new ConnectionStringBuilder(endpoint, "queue1");
        try {
            QueueClient sendClient = new QueueClient(cs, ReceiveMode.PEEKLOCK);
            sendClient.send(new Message(message));
        
            sendClient.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            //TODO: handle exception
        }
    }
}
