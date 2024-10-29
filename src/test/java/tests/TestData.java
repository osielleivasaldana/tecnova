package tests;

public class TestData {
    public Credentials credentials;
    public BillingDetails billingDetails;
    public String comments;
    public Product[] products;

    public static class Credentials {
        public String email;
        public String password;
    }

    public static class BillingDetails {
        public String firstName;
        public String lastName;
        public String address;
        public String city;
        public String postcode;
        public String country;
        public String region;
    }

    public static class Product {
        public String name;
        public Options options;

        public static class Options {
            public String color;
        }
    }
}
