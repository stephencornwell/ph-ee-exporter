/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Licensed under the Zeebe Community License 1.0. You may not use this file
 * except in compliance with the Zeebe Community License 1.0.
 */
package hu.dpc.rt.kafkastreamer.exporter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.camunda.zeebe.protocol.record.RecordType;
import io.camunda.zeebe.protocol.record.ValueType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KafkaExporterConfigurationTest {

  private KafkaExporterConfiguration configuration;

  @BeforeEach
  void setUp() {
    configuration = new KafkaExporterConfiguration();
  }

  // --- Default value tests for newly enabled types ---

  @Test
  void rejectionShouldBeEnabledByDefault() {
    assertTrue(configuration.index.rejection);
  }

  @Test
  void messageShouldBeEnabledByDefault() {
    assertTrue(configuration.index.message);
  }

  @Test
  void messageSubscriptionShouldBeEnabledByDefault() {
    assertTrue(configuration.index.messageSubscription);
  }

  // --- shouldIndexRecordType tests ---

  @Test
  void shouldIndexCommandRejectionRecordType() {
    assertTrue(configuration.shouldIndexRecordType(RecordType.COMMAND_REJECTION));
  }

  @Test
  void shouldIndexEventRecordType() {
    assertTrue(configuration.shouldIndexRecordType(RecordType.EVENT));
  }

  @Test
  void shouldNotIndexCommandRecordTypeByDefault() {
    assertFalse(configuration.shouldIndexRecordType(RecordType.COMMAND));
  }

  // --- shouldIndexValueType tests for newly enabled types ---

  @Test
  void shouldIndexMessageValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.MESSAGE));
  }

  @Test
  void shouldIndexMessageSubscriptionValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.MESSAGE_SUBSCRIPTION));
  }

  // --- Existing enabled value types still work ---

  @Test
  void shouldIndexDeploymentValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.DEPLOYMENT));
  }

  @Test
  void shouldIndexErrorValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.ERROR));
  }

  @Test
  void shouldIndexIncidentValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.INCIDENT));
  }

  @Test
  void shouldIndexJobValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.JOB));
  }

  @Test
  void shouldIndexVariableValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.VARIABLE));
  }

  @Test
  void shouldIndexVariableDocumentValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.VARIABLE_DOCUMENT));
  }

  @Test
  void shouldIndexProcessInstanceValueType() {
    assertTrue(configuration.shouldIndexValueType(ValueType.PROCESS_INSTANCE));
  }

  // --- Disabled value types remain disabled ---

  @Test
  void shouldNotIndexJobBatchValueType() {
    assertFalse(configuration.shouldIndexValueType(ValueType.JOB_BATCH));
  }
}
