package de.seven.fate.message.resource;

import de.seven.fate.message.enums.MessageType;
import de.seven.fate.message.enums.UserName;
import de.seven.fate.message.facade.MessageFacade;
import de.seven.fate.util.NumberUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private static final Logger logger = Logger.getLogger(MessageResource.class);

    @Inject
    private MessageFacade facade;

    @GET
    public Response getMessages(@UserName String userName) {
        logger.debug("get  messages for user: " + userName);

        return Response.ok(facade.findMessagesByPerson(userName)).build();
    }

    @GET
    @Path("/type/{type}")
    public Response getMessagesByType(@UserName String userName, @PathParam("type") MessageType messageType) {
        logger.debug("get messages for user: " + userName + " adn type: " + messageType);

        return Response.ok(facade.findMessagesByPersonAndType(userName, messageType)).build();
    }

    @POST
    @Path("/{messageIds}")
    public Response markMessage(@UserName String userName, @PathParam("messageIds") String messageIds) {
        logger.debug("delete  messages: " + messageIds);

        return Response.ok(facade.markMessageAsRead(userName, NumberUtil.parseLong(messageIds.split(",")))).build();
    }

    @DELETE
    @Path("/{messageIds}")
    public Response deleteMessage(@UserName String userName, @PathParam("messageIds") String messageIds) {
        logger.debug("delete  messages: " + messageIds);

        return Response.ok(facade.deleteMassage(userName, NumberUtil.parseLong(messageIds.split(",")))).build();
    }

    @DELETE
    @Path("/all")
    public Response deleteAllCurrentUserMessages(@UserName String userName) {
        logger.debug("delete  all messages for current user: " + userName);

        return Response.ok(facade.deleteMassage(userName)).build();
    }
}