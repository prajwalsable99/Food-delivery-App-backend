package com.prajwal.FoodApp.payment;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
   final String PAYMENT_KEY="rzp_test_IzuRePIB4PnanC";
     final String PAYMENT_SECRET_KEY="xcO8dSsDbXtBTlWF3JkkY93j";

     final String CURRENCY="INR";
        public TransactionDetails createTransaction(Long amount) throws Exception {




                JSONObject jsonObject=new JSONObject();

                jsonObject.put("amount",(amount*100));
                jsonObject.put("currency",CURRENCY);


                RazorpayClient razorpayClient =new RazorpayClient(PAYMENT_KEY,PAYMENT_SECRET_KEY);
                com.razorpay.Order paymentOrder=razorpayClient.orders.create(jsonObject);
//                System.out.println(paymentOrder);

                TransactionDetails transactionDetails=new TransactionDetails();
                transactionDetails.setPayKey(PAYMENT_KEY);
                transactionDetails.setPayOrderId(paymentOrder.get("id"));
                transactionDetails.setPayCurrency(paymentOrder.get("currency"));


            int amountInPaise = paymentOrder.toJson().getInt("amount");
            Long amountInRupees = amountInPaise / 100L; // Convert back to Rupees
            transactionDetails.setPayAmount(amountInRupees);

            return transactionDetails;





        }

}
