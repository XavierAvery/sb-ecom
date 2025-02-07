package com.ecommerce.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long paymentId; // Internal payment id
    private String paymentMethod;
    private String pgPaymentId; // Payment gateway payment id; Can be name like StripePaymentID, RazorpayPaymentID, etc.
    private String pgStatus;
    private String pgResponseMessage;
    private String pgName;
}
