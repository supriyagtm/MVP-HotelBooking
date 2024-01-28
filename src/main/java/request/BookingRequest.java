package request;

/**
 * Request class for Booking. A separate request class is created in order to
 * have additional validations at entry point if required as per the business
 * logic. This helps restrict the incoming of bad request at the entry point
 * itself and helps restricting the entry of some bad data into our database, an
 * additional layer of validations at entry point of the api
 * 
 * @author supriyasharma
 */
public class BookingRequest {
    public String hotelName;
    public String guestName;
    public String roomType;
    public String checkInDate;
    public String checkOutDate;
    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }
    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    /**
     * @return the guestName
     */
    public String getGuestName() {
        return guestName;
    }
    /**
     * @param guestName the guestName to set
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }
    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    /**
     * @return the checkInDate
     */
    public String getCheckInDate() {
        return checkInDate;
    }
    /**
     * @param checkInDate the checkInDate to set
     */
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    /**
     * @return the checkOutDate
     */
    public String getCheckOutDate() {
        return checkOutDate;
    }
    /**
     * @param checkOutDate the checkOutDate to set
     */
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
}
