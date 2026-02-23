public class DefaultAddOnPricing implements AddOnPricing {
    @Override
    public Money priceFor(AddOn addOn) {
        if (addOn == AddOn.MESS) return new Money(1000.0);
        if (addOn == AddOn.LAUNDRY) return new Money(500.0);
        if (addOn == AddOn.GYM) return new Money(300.0);
        return new Money(0.0);
    }
}
