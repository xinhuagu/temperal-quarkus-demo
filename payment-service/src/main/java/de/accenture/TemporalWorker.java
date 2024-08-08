package de.accenture;

import de.berlin.accenture.OrderWorkflow;
import de.berlin.accenture.OrderWorkflowImpl;
import de.berlin.accenture.activity.PaymentAcitivity;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.WorkerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class TemporalWorker {

  WorkerFactory factory;

  @Inject
  WorkflowClient workflowClient;

  @Inject
  PaymentAcitivity paymentAcitivity;

  void onStart(@Observes StartupEvent ev) {

    factory= WorkerFactory.newInstance(workflowClient);
    var worker = factory.newWorker(OrderWorkflow.PAYMENT_TASK_QUEUE);

    worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);

    worker.registerActivitiesImplementations(paymentAcitivity);
    factory.start();
    log.info("Temporal worker started.");
  }

  void onStop(@Observes ShutdownEvent ev) {
    if ( factory != null) {
      factory.shutdown();
    }
    log.info("Temporal worker stopped.");
  }
}