package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PaymentDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payment.confirmed")
    public void receivePaymentConfirmation(PaymentDto payment) {
        String message = """
                Dados do pagamento recebido:
                ID: %d
                order id: %d
                Amount: %s
                Card Holder Name: %s
                Card Number: %s
                Expiration: %s
                Security Code: %s
                Status: %s
                """.formatted(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getCardHolderName(),
                payment.getCardNumber(),
                payment.getExpiration(),
                payment.getSecurityCode(),
                payment.getStatus());

        System.out.println(message);
    }
}
