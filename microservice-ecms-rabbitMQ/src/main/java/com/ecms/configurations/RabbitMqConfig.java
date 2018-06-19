package com.ecms.configurations;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a RabbitMQ configuration class for creating multiple beans defination
 * 
 * @author nagpalh
 *
 */
@Configuration
public class RabbitMqConfig {

	@Value("${mail.rabbitmq.queue}")
	String queueName;

	@Value("${mail.rabbitmq.exchange}")
	String exchange;

	@Value("${mail.rabbitmq.routingkey}")
	private String routingkey;

	/**
	 * For Creating RabbitMq Queue
	 * @return Queue
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	/**
	 * Data Exchanges on which data is produced
	 * @return	DirectExchange
	 */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	/**
	 * This function bind the "Exchanges" with the "Queues" based on routing key used.
	 * @param queue 
	 * @param exchange
	 * @return Binding
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	/**
	 * function is used to convert Messages into JSON format
	 * @return MessageConverter
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * creating AmpqTemplate object for producing message on Queue (through "RabbitMqExchanges")
	 * @param connectionFactory
	 * @return AmqpTemplate
	 */
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}