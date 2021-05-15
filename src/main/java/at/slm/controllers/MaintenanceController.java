package at.slm.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/api/maintenanceMode")
public class MaintenanceController {
    public static String message = "-";

    @Path("/frontend")
    @GET
    @Produces( MediaType.TEXT_HTML )
    public String getPage()
    {
        try {
            java.nio.file.Path path = Paths.get("file:///C:/Users/eboerse/Documents/MaintenanceMonitor/frontend/index.html");
            return Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Frontend index.html nicht gefunden";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStatus(){
        System.out.println("Status requested. Current message is: "+message);

        Response.ResponseBuilder rb = Response.ok(message);
        return rb.header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("/{message}")
    public String setStatus(@PathParam("message") String newMessage){
        if(newMessage == null || newMessage.trim().equals(""))
            return "Invalid Operation";

        System.out.println("Status change requested. New message is" + newMessage);
        message = newMessage;
        return "New message is: "+newMessage;
    }

    @GET
    @Path("/{message}")
    public String setStatusGet(@PathParam("message") String newMessage){
        if(newMessage == null || newMessage.trim().equals(""))
            return "Invalid Operation";

        System.out.println("Status change requested. New message is" + newMessage);
        message = newMessage;
        return "New message is: "+newMessage;
    }
}
