package de.accenture;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class TemporalClientProducer {

  @Produces
  @ApplicationScoped
  public WorkflowClient workflowClient() {
    WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(
        WorkflowServiceStubsOptions.newBuilder().setTarget("localhost:7233").build());
    return WorkflowClient.newInstance(service);
  }
}
