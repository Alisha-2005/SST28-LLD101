
public class HostelFeeCalculator {
    private final BookingRepository repo;
    private final RoomPricing roomPricing;
    private final AddOnPricing addOnPricing;

    public HostelFeeCalculator(BookingRepository repo, RoomPricing roomPricing, AddOnPricing addOnPricing) {
        this.repo = repo;
        this.roomPricing = roomPricing;
        this.addOnPricing = addOnPricing;
    }

    // Calculator now delegates pricing to injected components; printing/persistence kept outside pricing logic.
    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new java.util.Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money base = roomPricing.priceFor(req.roomType);
        Money add = new Money(0.0);
        for (AddOn a : req.addOns) {
            add = add.plus(addOnPricing.priceFor(a));
        }
        return base.plus(add);
    }
}
