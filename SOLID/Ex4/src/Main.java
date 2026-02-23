import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
            BookingRepository repo = new FakeBookingRepo();
            RoomPricing roomPricing = new DefaultRoomPricing();
            AddOnPricing addOnPricing = new DefaultAddOnPricing();

            HostelFeeCalculator calc = new HostelFeeCalculator(repo, roomPricing, addOnPricing);
            BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
            calc.process(req);
    }
}
