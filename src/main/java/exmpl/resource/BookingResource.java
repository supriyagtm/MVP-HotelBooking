package exmpl.resource;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import entity.Bookings;
import exmpl.dao.BookingDAO;
import request.BookingRequest;

/**
 * This is the resource class where the CRUD operations endpoint and logic is
 * mentioned (We can have a separate Business logic layer as well created which
 * is not used with this implementation).
 * 
 * @Path annotation help us specify the URI path template to which the resource
 *       and methods will respond for this application, essential for RESTful
 *       web services in java.
 * @author supriyasharma
 */
@Path("/bookings")
public class BookingResource {

    
    /**
     * Endpoint to create a new booking.
     *
     * @param request BookingRequest object containing booking details in JSON format.
     * @return HTTP response indicating the success of the operation.
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBooking(BookingRequest request) {
        try {
        BookingDAO dao = new BookingDAO();
        String hotelName = request.getHotelName();
        String guestName = request.getGuestName();
        String roomType = request.getRoomType();
        String checkInDate = request.getCheckInDate();
        String checkOutDate = request.getCheckOutDate();
        
        Bookings newBooking = new Bookings(hotelName, guestName, roomType, checkInDate, checkOutDate);
        System.out.println(newBooking.toString());
        
        dao.save(newBooking);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(Response.Status.CREATED);
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    /**
     * Endpoint to retrieve booking details by booking ID.
     *
     * @param id Booking ID path parameter.
     * @return JSON response containing the details of the requested booking.
     * @throws IOException if there is an issue converting the entity to JSON.
     */
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooking(@PathParam("id") Long id) throws IOException {
        BookingDAO dao = new BookingDAO();
        Bookings booking = dao.get(id);
        System.out.println(booking);
        String bookingRes = convertEntityToJson(booking);
        return Response.ok(bookingRes, MediaType.APPLICATION_JSON).build();
    }
    
    /**
     * Endpoint to update an existing booking by booking ID.
     *
     * @param id      Booking ID path parameter.
     * @param request BookingRequest object containing updated booking details in JSON format.
     * @return HTTP response indicating the success of the update operation.
     * @throws IOException if there is an issue converting the entity to JSON.
     */
    @PUT
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBooking(@PathParam("id") Long id, BookingRequest request) throws IOException {
        BookingDAO dao = new BookingDAO();
        // fetch the existing booking
        Bookings booking = dao.get(id);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            booking.setHotelName(request.getHotelName() != null ? request.getHotelName() : booking.getHotelName());
            booking.setGuestName(request.getGuestName() != null ? request.getGuestName() : booking.getGuestName());
            booking.setRoomType(request.getRoomType() != null ? request.getRoomType() : booking.getRoomType());
            booking.setCheckInDate(request.getCheckInDate() != null ? request.getCheckInDate() : booking.getCheckInDate());
            booking.setCheckOutDate(request.getCheckOutDate() != null ? request.getCheckOutDate()
                    : booking.getCheckOutDate());
            
            System.out.println(booking.toString());
            dao.update(booking);
        }
        return Response.status(Response.Status.OK).build();
    }
    
    
    /**
     * Endpoint to cancel an existing booking by booking ID.
     *
     * @param id Booking ID path parameter.
     * @return HTTP response indicating the success of the cancellation operation.
     * @throws IOException if there is an issue converting the entity to JSON.
     */
    @DELETE
    @Path("/id/{id}")
    public Response cancelBooking(@PathParam("id") Long id) throws IOException {
        BookingDAO dao = new BookingDAO();
        // fetch the existing booking
        Bookings booking = dao.get(id);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            dao.delete(id);
        }
        return Response.status(Response.Status.OK).build();
    }
    
    /**
     * Helper method to convert an entity to JSON
     * @param entity
     * @return
     * @throws IOException
     */
    private String convertEntityToJson(Object entity) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(entity);
    }
    
    
    
}