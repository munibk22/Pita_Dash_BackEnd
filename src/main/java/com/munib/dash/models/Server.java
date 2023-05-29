//package com.munib.dash.models;
//
//import java.nio.file.Paths;
//
//import static spark.Spark.post;
//import static spark.Spark.port;
//import static spark.Spark.staticFiles;
//
//import com.stripe.Stripe;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//
//public class Server {
//
//  public static void main(String[] args) {
//    port(4242);
//
//    // This is your test secret API key.
//    Stripe.apiKey = "sk_test_51Ir9CYBbKPDTbStSG9JvfFWk4FebZCoMJ90eetf53vsJ0MhOKkslv34Y4r6aWysSHW45jNaRz3wYpSA9VmJv9Y6I00zjsHv8lJ";
//
//    staticFiles.externalLocation(
//        Paths.get("public").toAbsolutePath().toString());
//
//    post("/create-checkout-session", (request, response) -> {
//        String YOUR_DOMAIN = "http://localhost:4242";
//        SessionCreateParams params =
//          SessionCreateParams.builder()
//            .setMode(SessionCreateParams.Mode.PAYMENT)
//            .setSuccessUrl(YOUR_DOMAIN + "?success=true")
//            .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
//            .setAutomaticTax(
//              SessionCreateParams.AutomaticTax.builder()
//                .setEnabled(true)
//                .build())
//            .addLineItem(
//              SessionCreateParams.LineItem.builder()
//                .setQuantity(1L)
//                // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                .setPrice("{{PRICE_ID}}")
//                .build())
//            .build();
//      Session session = Session.create(params);
//
//      response.redirect(session.getUrl(), 303);
//      return "";
//    });
//  }
//}